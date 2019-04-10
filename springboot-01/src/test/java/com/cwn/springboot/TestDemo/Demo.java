package com.cwn.springboot.TestDemo;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class Demo {


    public static void main(String args[]) throws IOException {

//        //解密
//        String aa="MTIz";
//        byte[] decodedata=new BASE64Decoder().decodeBuffer(aa);
//        System.out.println(new String(decodedata));

//        //加密
//        String en="123";
//        System.out.println(encode(en));
//
//        //解密
//        String de=encode(en);
//        System.out.println(decode(de));

        System.out.println(UUID.randomUUID().toString());

    }

    /**
     * 将 BASE64 编码的字符串 s 进行加码
     *
     * @return String
     */
    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"GBK");
        } catch (Exception e) {
            return null;
        }
    }
}
