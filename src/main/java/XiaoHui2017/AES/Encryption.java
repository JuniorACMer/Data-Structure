package XiaoHui2017.AES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @author Spark
 */
public class Encryption {
    public static void main(String[] args) throws Exception {
        String key = "123456";
        //创建AES的key生产者
        KeyGenerator kgen = KeyGenerator.getInstance("XiaoHui2017/AES");
        //利用用户密码作为随机数初始化
        kgen.init(128, new SecureRandom(key.getBytes()));
        //根据用户密码，生成一个密钥
        SecretKey secretKey = kgen.generateKey();
        //返回基本编码格式的密钥
        byte[] enCodeFormat = secretKey.getEncoded();
        //打印128位密钥
        System.out.println("密钥：" + Arrays.toString(enCodeFormat));
        //创建加密器
        Cipher cipher = Cipher.getInstance("XiaoHui2017/AES/CBC/PKCS5Padding");
        //初始化加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //需要加密的明文
        String content = "欢迎来到王者荣耀，我的账号是：11111122222，密码是：88888899999";
        byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
        System.out.println("明文长度：" + byteContent.length);
        //加密后的结果
        byte[] result = cipher.doFinal(byteContent);
        System.out.println("密文长度;"+result.length);
        //打印密文
        System.out.println("密文：" + Arrays.toString(result));
    }
}
