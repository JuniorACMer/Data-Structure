package algorithm07;

import org.junit.Test;

import java.io.*;

/**
 * @author Spark
 */
public class TransientTest {
        private static final long serialVersionUID = 233858934995755239L;
        private String name1;
        private transient String name2;

        public TransientTest(String name1,String name2){
            this.name1 = name1;
            this.name2 = name2;
        }
        @Override
        public String toString(){
            return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
        }

}
class TestTransient implements Serializable {
    public static void main(String[] args) {
        testTransient();
    }
    public static void testTransient(){
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest test = new TransientTest(name1, name2);
        System.out.println("序列化前："+test.toString());
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        String filePath = "I:/data/TransientTest.obj";
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            try {
                outStream.writeObject(test);
            } catch (IOException e) {
                e.printStackTrace();
            }

            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest readObject = (TransientTest)inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

