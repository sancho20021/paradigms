package expression.parser;

import expression.exceptions.Parser;
import expression.expressions.*;
import expression.parser.calculators.Calculator;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ExpressionParser<T extends Number> extends BaseParser implements Parser<T> {
    private final static List<String> VARS = List.of("x", "y", "z");
    private final static Map<String, Integer> OP_PRIORITIES = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2,
            "**", 3,
            "//", 3,
            "min", 0,
            "max", 0
    );
    private final static List<String> UNARY_OPS = List.of("log2", "pow2", "count");
    private final static int START_LEVEL = 0;
    private final static int LAST_LEVEL = 4;
    private final Calculator<T> calculator;

    public ExpressionParser(Calculator<T> calculator) {
        this.calculator = calculator;
    }

    @Override
    public CommonExpression<T> parse(String expression) {
        setSource(new StringSource(expression));
        nextChar();
        CommonExpression<T> expr = parseLevel(START_LEVEL);
        if (ch != '\0') {
            throw error("Expected end of file, found: " + ch);
        }
        return expr;
    }

    private CommonExpression<T> parseLevel(final int level) {
        if (level == LAST_LEVEL) {
            return parseLexem();
        }
        CommonExpression<T> expression = parseLevel(level + 1);
        skipWhitespaces();
        String nowOperator;
        while ((nowOperator = takeOperator(level)) != null) {
            expression = createExpression(nowOperator, expression, parseLevel(level + 1));
            skipWhitespaces();

        }
        return expression;
    }

    private String takeOperator(int level) {
        for (String anyOp : getSetOfOperators(level)) {
            if (test(anyOp)) {
                return anyOp;
            }
        }
        return null;
    }

    private CommonExpression<T> parseLexem() {
        skipWhitespaces();
        if (test('(')) {
            CommonExpression<T> expression = parseLevel(START_LEVEL);
            expect(')');
            nextChar();
            return expression;
        } else if (test('-')) {
            if (between('0', '9')) {
                return getConst(true);
            } else {
                return new Negate<T>(parseLexem(), calculator);
            }
        } else if (between('0', '9')) {
            return getConst(false);
        } else {
            StringBuilder str = new StringBuilder();
            while (between('a', 'z') || between('0', '9')) {
                str.append(ch);
                nextChar();
            }
            String lexem = str.toString();
            if (!lexem.isEmpty()) {
                if (UNARY_OPS.contains(lexem)) {
                    return createExpression(lexem, parseLexem());
                } else {
                    if (VARS.contains(lexem)) {
                        return new Variable<T>(lexem);
                    } else {
                        throw error("Invalid argument or operator \"" + lexem + "\"");
                    }
                }
            } else {
                throw error("Expected argument, found " + (ch != '\0' ? ("'" + ch + "'") : "end of file"));
            }
        }
    }

    private CommonExpression<T> createExpression(String unaryOp, CommonExpression<T> expression) {
        switch (unaryOp) {
//            case "log2":
//                return new Log2<T>(expression, calculator);
//            case "pow2":
//                return new Pow2<T>(expression, calculator);
            case "count":
                return new Count<T>(expression, calculator);
            default:
                throw error("Unrecognized operator: " + unaryOp);
        }
    }

    private CommonExpression<T> createExpression(String binaryOp, CommonExpression<T> a, CommonExpression<T> b) {
        switch (binaryOp) {
            case "+":
                return new Add<T>(a, b, calculator);
            case "-":
                return new Subtract<T>(a, b, calculator);
            case "*":
                return new Multiply<T>(a, b, calculator);
            case "/":
                return new Divide<T>(a, b, calculator);
//            case "**":
//                return new Pow<T>(a, b, calculator);
//            case "//":
//                return new Log<T>(a, b, calculator);
            case "max":
                return new Max<T>(a, b, calculator);
            case "min":
                return new Min<T>(a, b, calculator);
            default:
                throw error("Unrecognized operator: " + binaryOp);
        }
    }

    private Const<T> getConst(boolean isNegative) {
        StringBuilder str = new StringBuilder();
        if (isNegative) {
            str.append("-");
        }
        while (between('0', '9')) {
            str.append(ch);
            nextChar();
        }
        try {
            return new Const<T>(calculator.parse(str.toString()));
        } catch (NumberFormatException e) {
            throw error("Invalid number");
        }
    }

    private void skipWhitespaces() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }

    private Set<String> getSetOfOperators(int level) {
        Set<String> ops = new HashSet<>();
        for (Map.Entry<String, Integer> entry : OP_PRIORITIES.entrySet()) {
            if (entry.getValue().equals(level)) {
                ops.add(entry.getKey());
            }
        }
        return ops;
    }
}
