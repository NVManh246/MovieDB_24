package com.framgia.nvmanh.boxmovie.ultis;

public final class StringUltis {
    public static String getImageUrl(String s){
        StringBuilder stringBuilder = new StringBuilder(Contants.Server.BASE_IMAGE_URL);
        stringBuilder.append(s);
        return stringBuilder.toString();
    }
}
