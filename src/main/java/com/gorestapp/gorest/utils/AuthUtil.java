package com.gorestapp.gorest.utils;

public class AuthUtil {

    public static String getUserid(String token){
        return  token.substring(token.indexOf("(|-|)")+5,token.length()) ;
    }

    public static String getUserEmail(String token){
        return token.substring(0,token.indexOf("(|-|)")) ;
    }

}
