package com.framgia.nvmanh.boxmovie.ultis;

public final class StringUltis {
    public static String getImageUrl(String s1, String s2){
        StringBuilder stringBuilder = new StringBuilder(s1);
        stringBuilder.append(s2);
        return stringBuilder.toString();
    }
}
