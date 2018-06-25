package com.project.util.encrypt;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class AesUtil {
    public static final String KEY_ALGORITHM = "AES";

    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static final String VIPARA = "0102030405060708";
    public static final String ENCODING = "UTF-8";

    public static byte[] encrypt(String content, String password) {
        try {
            SecretKeySpec skeySpec = getKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(VIPARA.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(content.getBytes());// "utf-8"
            return encrypted;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] decrypt(byte[] content, String password) {
        try {
            SecretKeySpec skeySpec = getKey(password);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(VIPARA.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(content);
            return original;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (IllegalBlockSizeException e) {
            return null;
        } catch (BadPaddingException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static int encryptString(String srcStr, String password)
            throws UnsupportedEncodingException {
        String resultStr = "";
        password = CryptUtil.encryptToMD5(password);
        byte[] result = encrypt(srcStr, password);
        resultStr = bytesToHexString(result);
        return 0;
    }

    public static String encryptString1(String srcStr, String password)
            throws UnsupportedEncodingException {
        String resultStr = "";
        password = CryptUtil.encryptToMD5(password);
        byte[] result = encrypt(srcStr, password);
        resultStr = bytesToHexString(result);
        return resultStr;

    }

    public static String decryptString(String destStr, String password) {
        String resultStr = "";
        try {

            password = CryptUtil.encryptToMD5(password);
            byte[] result = hexStringToBytes(destStr);
            result = decrypt(result, password);

            resultStr = new String(result, "UTF-8");
        } catch (Exception e) {
            return null;
        }
        return resultStr;
    }

    private static SecretKeySpec getKey(String strKey) {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16];

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, KEY_ALGORITHM);

        return skeySpec;
    }
}
