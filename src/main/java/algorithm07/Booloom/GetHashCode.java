package algorithm07.Booloom;

/**
 * @author Spark
 */
public class GetHashCode {
    public static void main(String[] args) {
        String data = "www.baidu.com";
        int length = data.length();

        int hashCode = data.hashCode();
        System.out.println("对应的HashCode：" + hashCode);
        int index = hashCode & (length - 1);
        System.out.println("对应的Index：" + index);

        String data1 = "www.inspur.com";
        int length1 = data.length();

        int hashCode1 = data1.hashCode();
        System.out.println("对应的HashCode：" + hashCode1);
        int index1 = hashCode1 & (length1 - 1);
        System.out.println("对应的Index：" + index1);

        String data2 = "sasdfasdfasdfasdfasdf";
        int length2 = data2.length();

        int hashCode2 = data.hashCode();
        System.out.println("对应的HashCode：" + hashCode2);
        int index2 = hashCode2 & (length2 - 1);
        System.out.println("对应的Index：" + index2);

        System.out.println("Integer类型的长度;" + Integer.SIZE);
        System.out.println("Long类型的长度;" + Long.SIZE);
        System.out.println("Float类型的长度;" + Float.SIZE);
        System.out.println("Double类型的长度;" + Double.SIZE);
        System.out.println("Short类型的长度;" + Short.SIZE);
        System.out.println("Char类型的长度;"+Character.SIZE);
        System.out.println("Byte类型的长度;"+Byte.SIZE);


    }
}
