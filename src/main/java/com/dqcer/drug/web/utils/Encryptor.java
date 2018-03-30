package com.dqcer.drug.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


/**
 * <p>Description:加密工具类</p>
 * @author administrator
 * @date 2018/3/30 8:27
 * @param
 * @return
 */
public class Encryptor {

    private final static String [] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
            "z" };

    private Object salt;

    private String algorithm;

    public Encryptor(Object salt, String algorithm){
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String encode(String rawPass){
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //加密后的字符串
            result = byteArrayToHesString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("转码异常"+ e.getMessage());
        }
        return result;
    }

    public boolean isPasswordValid(String encPass, String rawPass){
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    private String mergePasswordAndSalt(String password){
        if (null == password)
            password = "";

        if ((null == salt) || "".equals(salt))
            return password;
        else
            return password + "{" + salt.toString() + "}";

    }

    private static String byteArrayToHesString(byte [] b){
        StringBuffer resultSb = new StringBuffer();
        int length = b.length;
        for (int i = 0;i < length; i++) {
            resultSb.append(byteToHesString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHesString(byte b) {
        int n = b;
        if (n < 0 )
            n = 256 + n;
        int d1 = n / hexDigits.length;
        int d2 = n % hexDigits.length;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String generateSalt(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String generateEncodePasword(String salt, String password){
        Encryptor encryptorMd5 = new Encryptor(salt, "sha-256");
        return encryptorMd5.encode(password);
    }

    public static boolean pswdIsCorrect(String password, String encodePassword, String salt ){
        Encryptor encryptorMd5 = new Encryptor(salt, "sha-256");
        String passwordEncode = encryptorMd5.encode(password);
        return passwordEncode.equals(encodePassword);
    }



}
