package expression.generic;

import expression.expressions.CommonExpression;
import expression.parser.ExpressionParser;
import expression.parser.calculators.*;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Calculator<?>> CALCULATORS = Map.of(
            "d", new DoubleCalculator(),
            "bi", new BigIntegerCalculator(),
            "i", new IntCalculator(true),
            "f", new FloatCalculator(),
            "b", new ByteCalculator(),
            "u", new IntCalculator(false)

    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2){
        return getTable(CALCULATORS.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T extends Number> Number[][][] getTable(Calculator<T> calculator, String expr, int x1, int x2,
                                                int y1, int y2, int z1, int z2){
        int dx = x2 - x1, dy = y2 - y1, dz = z2 - z1;
        Number[][][] table = new Number[dx+1][dy+1][dz+1];
        CommonExpression<T> commonExpression = new ExpressionParser<T>(calculator).parse(expr);
        for (int x = 0; x <= dx; x++) {
            for (int y = 0; y <= dy; y++) {
                for (int z = 0; z <= dz; z++) {
                    try {
                        if(x1+x==2147483634 && y1+y == 2147483643 && z1 + z ==2147483635 && expr.equals("10")){
                            System.out.println("sex");
                        }
                        table[x][y][z] = commonExpression.evaluate(
                                calculator.parse(String.valueOf(x1 + x)),
                                calculator.parse(String.valueOf(y1 + y)),
                                calculator.parse(String.valueOf(z1 + z))
                        );
                    } catch (RuntimeException e) {
                        table[x][y][z] = null;
                    }
                }
            }
        }
        return table;
    }
}
