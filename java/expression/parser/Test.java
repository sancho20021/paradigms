package expression.parser;

import expression.expressions.TripleExpression;
import expression.exceptions.Parser;
import expression.parser.calculators.ByteCalculator;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        Parser<Byte> parser = new ExpressionParser<>(new ByteCalculator());
        String str = scn.nextLine();
        TripleExpression<Byte> expr = parser.parse(str);
        System.out.println("Byte result of " + expr.toMiniString() + " is: " + expr.evaluate((byte) -11, (byte) -16, (byte) 0));


    }
}
