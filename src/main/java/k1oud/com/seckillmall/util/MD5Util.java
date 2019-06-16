package k1oud.com.seckillmall.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    /**
        第一层md5，把用户在终端输入的明文密码通过md5，转换为表单密码
     */
    public static String inputPassToFormPass(String inputPass){
        String str = ""+ salt.charAt(0)+salt.charAt(1)+inputPass+salt.charAt(3)+salt.charAt(4);
        return md5(str);
    }

    /**
     * 第二层md5，也就是将经过一次md5转化的表单密码，再使用一次md5计算，直接保存在DB中
     * 因为salt可以保存到Db中，所以此次保存不使用固定的salt，而是用随机的salt并保存在db中
     */
    public static String formPassToDbPass(String formPass,String dbSalt){
        String str = ""+ dbSalt.charAt(0)+dbSalt.charAt(1)+formPass+dbSalt.charAt(3)+dbSalt.charAt(4);
        return str;
    }

    /**
     * 可以将上面两个方法抽取出来，这样直接就进行了两步
     * 完成inputPass====>formPass=====>dbPass
     */
    public static String inputPassToDbPass(String inputPass,String dbSalt){
        String formP = inputPassToFormPass(inputPass);
        return formPassToDbPass(formP,dbSalt);
    }

    public static void main(String[] args) {
        String pass = inputPassToDbPass("123456","1a2b3c");
        System.out.println(pass);
    }
}
