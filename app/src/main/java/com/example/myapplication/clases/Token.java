package com.example.myapplication.clases;

public class Token {
    private static String token="void";

    public static void setToken(String tk) {
        Token.token = tk;
    }

    public static String getToken(){
        return Token.token;
    }
}
