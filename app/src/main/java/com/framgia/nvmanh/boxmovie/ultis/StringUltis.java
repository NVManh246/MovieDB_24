package com.framgia.nvmanh.boxmovie.ultis;

public final class StringUltis {
    public static String getImageUrl(String s){
        StringBuilder stringBuilder = new StringBuilder(Contants.Server.BASE_IMAGE_URL);
        stringBuilder.append(s);
        return stringBuilder.toString();
    }

    public static String getImageUrlYoutube(String key){
        StringBuilder s = new StringBuilder(Contants.Server.BASE_IMAGE_URL_YOUTUBE);
        s.append(key).append(Contants.Server.IMAGE_QUALITY);
        return s.toString();
    }
}
