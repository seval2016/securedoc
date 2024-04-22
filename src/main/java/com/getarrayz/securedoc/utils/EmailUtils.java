package com.getarrayz.securedoc.utils;

public class EmailUtils {

    public static  String getEmailMessage(String name, String host, String token){
        return "Hello" + name + ",\n\nYour new account has been created. Please click on the link below to verify your account. \n\n" + getVerificarionUrl(host,token) + "\n\nThe Support Team";
    }

    public static  String getResetPasswordMessage(String name, String host, String token){
        return "Hello" + name + ",\n\nYour new password has been created. Please click on the link below to verify your account. \n\n" + getResetPasswordUrl(host,token) + "\n\nThe Support Team";
    }

    public static String getVerificarionUrl(String host,String token){
        return host + "/verify/account?token="+token;
    }


    public static String getResetPasswordUrl(String host,String token){
        return host + "/verify/password?token="+token;
    }
}
