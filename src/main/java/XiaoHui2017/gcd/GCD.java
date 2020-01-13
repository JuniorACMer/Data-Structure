package XiaoHui2017.gcd;

/**
 * @author Spark
 */
public class GCD {
    public static void main(String[] args) {
        System.out.println("最大公约数：" + gcd1(1000, 525));
        System.out.println("最大公约数：" + gcd2(1000, 525));
//        System.out.println("最大公约数：" + gcd3(1000, 525));
    }

    /**
     * 辗转相除法
     *
     * @param a data
     * @param b data
     * @return gcd
     */
    public static int gcd1(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return (gcd1(b, a % b));
        }
    }

    /**
     * 更相减损术
     * @param a data1
     * @param b data2
     * @return gcd
     */
    public static int gcd2(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            return gcd2(a - b, b);
        } else {
            return gcd2(b - a, a);
        }
    }

    /**
     * 两者结合
     * @param a data
     * @param b data
     * @return gcd
     */
    public static int gcd3(int a,int b){
        return 0;
    }
}

