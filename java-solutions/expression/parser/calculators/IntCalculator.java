package expression.parser.calculators;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class IntCalculator implements Calculator<Integer> {
    private final boolean isChecked;

    public IntCalculator(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public Integer add(Integer a, Integer b)  {
        if (a > 0) {
            if (b > Integer.MAX_VALUE - a) {
                overflow(a, b, "+");
            }
        } else {
            if (b < Integer.MIN_VALUE - a) {
                overflow(a, b, "+");
            }
        }
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b)  {
        if (a >= 0) {
            //a-b>intMax
            //a-intMax>b
            if (a - Integer.MAX_VALUE > b) {
                overflow(a, b, "-");
            }
        } else {
            //a-b<intMin
            //a-intMin<b
            if (a - Integer.MIN_VALUE < b) {
                overflow(a, b, "-");
            }
        }
        return a - b;
    }

    @Override
    public Integer divide(Integer a, Integer b){
        if (a == Integer.MIN_VALUE && b == -1) {
            overflow(a, b, "/");
        }
        if (isChecked && b == 0) {
            throw new DBZException(a);
        }
        return a / b;
    }

    @Override
    public Integer multiply(Integer a, Integer b)  {
        throwIfMultiplyOverflow(a, b);
        return a * b;
    }

    @Override
    public Integer negate(Integer a)  {
        if (a == Integer.MIN_VALUE) {
            overflow(a, "-");
        }
        return -a;
    }

    @Override
    public Integer parse(String s) {
        return Integer.parseInt(s);
    }

    @Override
    public Integer count(Integer a) {
        return Integer.bitCount(a);
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return Integer.min(a, b);
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return Integer.max(a, b);
    }

    void overflow(int a, int b, String symbol)  {
        if (isChecked) {
            throw new OverflowException(a + " " + symbol + " " + b);
        }
    }

    protected void overflow(int a, String symbol)  {
        if (isChecked) {
            throw new OverflowException(symbol + " " + a);
        }
    }

    protected boolean hasMultiplyOverflow(int a, int b) {
        int r = a * b;
        return ((b != 0) && (r / b != a)) || (a == Integer.MIN_VALUE && b == -1);
    }

    protected void throwIfMultiplyOverflow(int a, int b) {
        if (isChecked && hasMultiplyOverflow(a, b)) {
            overflow(a, b, "*");
        }
    }

    //    @Override
//    public Integer pow(Integer a, Integer b) {
//        if (isChecked && (a == 0 && b == 0 || b < 0)) {
//            throw new PowException(a, b);
//        }
//
//        int n = b, ans = 1, x = a;
//        while (n > 0) {
//            if (n % 2 == 1) {
//                throwIfMultiplyOverflow(ans, x, "**");
//                ans *= x;
//                n--;
//            }
//            if (n != 0) {
//                throwIfMultiplyOverflow(x, x, "**");
//            }
//            x *= x;
//            n = n / 2;
//        }
//        return ans;
//    }
//
//    @Override
//    public Integer pow2(Integer a) {
//        if (a > 31) {
//            overflow(a, "pow2");
//        }
//        if (isChecked && a < 0) {
//            throw new PowException(2, a);
//        }
//        return 1 << a;
//    }
//    @Override
//    public Integer log(Integer a, Integer b) {
//        if (isChecked && (a <= 0 || b <= 0 || b == 1)) {
//            throw new LogException(a, b);
//        }
//        return calculateLog(a, b);
//    }
//
//    @Override
//    public Integer log2(Integer a) {
//        if (isChecked && a <= 0) {
//            throw new LogException(a, 2);
//        }
//        return calculateLog(a, 2);
//    }
//    protected static int calculateLog(int a, int b) {
//        int log = 0;
//        while (a >= b) {
//            log++;
//            a /= b;
//        }
//        return log;
//    }
}
