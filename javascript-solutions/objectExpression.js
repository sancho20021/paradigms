"use strict";

function createExpression(constructor, evaluate, toString, diff, prefix, postfix) {
    constructor.prototype.evaluate = evaluate;
    constructor.prototype.toString = toString;
    constructor.prototype.diff = diff;
    constructor.prototype.prefix = prefix;
    constructor.prototype.postfix = postfix;
    return constructor;
}

const varToIndex = {
    "x": 0,
    "y": 1,
    "z": 2
};


const Variable = createExpression(
    function (name) {
        this.name = name;
        this.index = varToIndex[name];
    },
    function (...vars) {
        return vars[this.index];
    },
    function () {
        return this.name;
    },
    function (name) {
        return this.name.localeCompare(name) === 0 ? Const.ONE : Const.ZERO;
    },
    function () {
        return this.name
    },
    function () {
        return this.name
    }
);

const Const = createExpression(
    function (value) {
        this.value = value;
    },
    function () {
        return Number(this.value);
    },
    function () {
        return this.value.toString();
    },
    function () {
        return Const.ZERO;
    },
    function () {
        return this.value.toString();
    },
    function () {
        return this.value.toString();
    }
);

Const.ZERO = new Const(0);
Const.ONE = new Const(1);
Const.TWO = new Const(2);

const AbstractOperation = createExpression(
    function (...args) {
        this.args = args;
    },
    function (...vars) {
        return this.f(...this.args.map(x => x.evaluate(...vars)));
    },
    function () {
        return this.args.join(" ") + " " + this.symbol;
    },
    function (name) {
        return this.df(name, ...this.args);
    },
    function () {
        return "(" + this.symbol + " " + this.args.map(x => x.prefix()).join(" ") + ")"
    },
    function () {
        return "(" + this.args.map(x => x.postfix()).join(" ") + " " + this.symbol + ")"
    }
);

function createOperation(f, symbol, df) {
    let constructor = function (...args) {
        AbstractOperation.call(this, ...args);
    };
    constructor.prototype = Object.create(AbstractOperation.prototype);
    constructor.prototype.f = f;
    constructor.prototype.symbol = symbol;
    constructor.prototype.df = df;
    return constructor;
}

const Add = createOperation(
    (a, b) => a + b,
    "+",
    (name, a, b) => new Add(a.diff(name), b.diff(name))
);
const Subtract = createOperation(
    (a, b) => a - b,
    "-", (name, a, b) => new Subtract(a.diff(name), b.diff(name))
);
const Multiply = createOperation(
    (a, b) => a * b, "*",
    (name, a, b) => new Add(
        new Multiply(a.diff(name), b),
        new Multiply(a, b.diff(name))
    )
);
const Divide = createOperation(
    (a, b) => a / b, "/",
    (name, a, b) => new Divide(
        new Subtract(new Multiply(a.diff(name), b), new Multiply(a, b.diff(name))),
        new Multiply(b, b)
    )
);
const Negate = createOperation(
    (a) => -a,
    "negate",
    (name, a) => new Negate(a.diff(name))
);
const Gauss = createOperation(
    (a, b, c, x) => a * Math.exp(-(x - b) * (x - b) / (2 * c * c)),
    "gauss",
    function (name, a, b, c, x) {
        return new Add(
            new Multiply(
                a.diff(name),
                new Gauss(Const.ONE, b, c, x)
            ),
            new Multiply(
                a,
                new Multiply(
                    new Gauss(Const.ONE, b, c, x),
                    Negate.prototype.df(
                        name,
                        new Divide(
                            new Multiply(new Subtract(x, b), new Subtract(x, b)),
                            new Multiply(Const.TWO, new Multiply(c, c))
                        )
                    ))))
    });
const Mean = createOperation(
    (...args) => (args.reduce((s, x) => s + x, 0)) / args.length,
    "mean",
    (name, ...args) => Divide.prototype.df(
        name,
        args.reduce((s, x) => new Add(s, x), Const.ZERO),
        new Const(args.length)
    )
);
const Var = createOperation(
    (...args) => {
        const mean = Mean.prototype.f(...args);
        return Mean.prototype.f(...args.map(el => (el - mean) * (el - mean)));
    },
    "var",
    (name, ...args) => {
        const mean = new Mean(...args);
        return Mean.prototype.df(
            name,
            ...args.map(
                el => {
                    const sub = new Subtract(el, mean);
                    return new Multiply(sub, sub);
                })
        );
    });

const tokenToOp = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide,
    "negate": Negate,
    "gauss": Gauss,
    "mean": Mean,
    "var": Var
};

function parse(expression) {
    const tokens = expression.trim().split(/ +/);
    let stack = [];
    for (let token of tokens) {
        if (token in tokenToOp) {
            const op = tokenToOp[token];
            stack.push(new op(...stack.splice(-op.prototype.f.length)));
        } else if (token in varToIndex) {
            stack.push(new Variable(token));
        } else {
            stack.push(new Const(token));
        }
    }
    return stack.pop();
}

function ParserError(message) {
    this.message = message;
}

ParserError.prototype = Object.create(Error.prototype);
ParserError.prototype.name = "ParserError";
ParserError.prototype.constructor = ParserError;

function createParserError(name, makeMessage) {
    function MyError(...args) {
        ParserError.call(this, makeMessage(...args));
    }

    MyError.prototype = Object.create(ParserError.prototype);
    MyError.prototype.name = name;
    MyError.prototype.constructor = ParserError;
    return MyError;
}

const MissingBracketError = createParserError("MissingBracketError",
    (pos, foundToken) => "Expected ')' at pos " + pos + " but found " + foundToken);
const UnrecognizedOperationError = createParserError("UnrecognizedOperationError",
    (pos, foundToken) => "Expected operator, found '" + foundToken + "' at pos " + pos);
const UnexpectedArgsNumberError = createParserError("UnexpectedArgsNumberError",
    (pos, operand, actualNumber, expectedNumber) => "Invalid arguments number of " + operand + " at pos "
        + pos + ". Expected: " + expectedNumber + ", but found: " + actualNumber);
const UnexpectedTokenError = createParserError("UnexpectedTokenError",
    (pos, foundToken) => "Unexpected token '" + foundToken + "' at pos " + pos);

function BaseParser(givenString, delimiters = []) {
    const _string = givenString;
    let _pos = 0;
    this.getPos = function () {
        return _pos
    };
    this.nextChar = function () {
        return _string[_pos++];
    };
    this.getChar = () => _string[_pos];
    this.getSourceLength = () => givenString.length;
    this.isDelimiter = (ch) => delimiters.includes(ch);
    this.incPos = (n) => _pos += n;

}

BaseParser.prototype.hasNext = function () {
    return this.getPos() < this.getSourceLength();
};
BaseParser.prototype.skipWhitespaces = function () {
    while (this.hasNext() && this.getChar().trim() === "") {
        this.nextChar();
    }
};
BaseParser.prototype.nextToken = function () {
    this.skipWhitespaces();
    let token = "";
    if (this.hasNext() && this.isDelimiter(this.getChar())) {
        token = this.nextChar();
    } else {
        while (this.hasNext() && !this.isDelimiter(this.getChar())) {
            token += this.nextChar();
        }
    }
    return token;
};

BaseParser.prototype.viewToken = function () {
    let token = this.nextToken();
    this.incPos(-token.length);
    return token;
};


function parseExpression(string, mode) {
    let readyString = string.trim();
    if (readyString === "") {
        throw new ParserError("Empty input");
    }
    const parser = new BaseParser(readyString, ["(", ")", " "]);
    let expr = parse();
    if (parser.hasNext()) {
        throw new ParserError("Expected end of file at pos " + parser.getPos() +", found: " + parser.getChar());
    }
    return expr;

    function parse() {
        let token = parser.nextToken();
        if (token === '(') {
            let operation = parseOperation();
            let token = parser.nextToken();
            if (token !== ')') {
                throw new MissingBracketError(parser.getPos(), token !== "" ? token : "end of file");
            }
            return operation;
        } else if (token in varToIndex) {
            return new Variable(token);
        } else if (!isNaN(+token)) {
            return new Const(token);
        } else {
            throw new UnexpectedTokenError(parser.getPos(), token);
        }
    }

    function parseOperation() {
        let opArgs, curOperator;
        if (mode === "prefix") {
            curOperator = parseOperator();
            opArgs = parseArgs();
        } else {
            opArgs = parseArgs();
            curOperator = parseOperator();
        }
        const argsLen = curOperator.prototype.f.length;
        if (argsLen !== 0 && opArgs.length !== argsLen) {
            throw new UnexpectedArgsNumberError(parser.getPos(), curOperator.prototype.symbol,
                opArgs.length, argsLen);
        }
        return new curOperator(...opArgs);
    }

    function parseOperator() {
        let op = parser.nextToken();
        if (!(op in tokenToOp)) {
            throw new UnrecognizedOperationError(parser.getPos(), op);
        }
        return tokenToOp[op];
    }

    function parseArgs() {
        let args = [];
        while (parser.hasNext() && notEnd()) {
            args.push(parse());
        }
        return args;
    }

    function notEnd() {
        let curToken = parser.viewToken();
        return !(curToken in tokenToOp) && curToken !== ')';
    }
}

function parsePrefix(string) {
    return parseExpression(string, "prefix");
}

function parsePostfix(string) {
    return parseExpression(string, "postfix")
}