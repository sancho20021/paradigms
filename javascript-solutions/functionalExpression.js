"use strict";

const operator = (f) => (...args) => (...vars) => f(...args.map(x => x(...vars)));

const add = operator((a, b) => a + b);
const subtract = operator((a, b) => a - b);
const multiply = operator((a, b) => a * b);
const divide = operator((a, b) => a / b);

const negate = operator((a) => -a);
const abs = operator((a) => Math.abs(a));

const iff = operator((t, a, b) => t >= 0 ? a : b);

const tokenToOp = {
    "+": [add, 2],
    "-": [subtract, 2],
    "*": [multiply, 2],
    "/": [divide, 2],
    "abs": [abs, 1],
    "iff": [iff, 3],
    "negate": [negate, 1]
};

const varToVariable = {
    "x": (...varValues) => varValues[0],
    "y": (...varValues) => varValues[1],
    "z": (...varValues) => varValues[2]
};

const variable = name => varToVariable[name];

const cnst = value => () => Number(value);

const one = cnst(1);
const two = cnst(2);

const tokenToCnst = {
    "one": one,
    "two": two
};

function parse(expression) {
    const tokens = expression.trim().split(/ +/);
    let stack = [];
    for (let token of tokens) {
        if (token in tokenToOp) {
            const op = tokenToOp[token];
            stack.push(op[0](...stack.splice(-op[1])));
        } else if (token in varToVariable) {
            // :NOTE: Дубли переменных
            // Update: Вроде пофиксил
            stack.push(variable(token));
        } else if (token in tokenToCnst) {
            stack.push(tokenToCnst[token]);
        } else {
            stack.push(cnst(token));
        }
    }
    return stack.pop();
}

// let expr = add(
//     subtract(
//         multiply(
//             variable("x"),
//             variable("x")
//         ),
//         multiply(
//             cnst(2),
//             variable("x")
//         )
//     ),
//     cnst(1)
// );
// for (let i = 0; i <= 10; i++) {
//     console.log(i + ": " + expr(i));
// }
