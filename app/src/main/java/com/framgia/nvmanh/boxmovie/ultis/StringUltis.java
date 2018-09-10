package com.framgia.nvmanh.boxmovie.ultis;

public final class StringUltis {

    private static final int NUMBER_CHAR_OF_YEAR = 4;

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

    public static String getFirstCharater(String s){
        return String.valueOf(s.charAt(0)).toUpperCase();
    }

    public static String getQureryCreateTable(String... args){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < args.length; i++){
            builder.append(args[i]);
        }
        return builder.toString();
    }

    public static String getQueryDropTable(String key, String tableName){
        StringBuilder builder = new StringBuilder(key);
        builder.append(tableName);
        return builder.toString();
    }

    public static String getYear(String date){
        return date.substring(0, NUMBER_CHAR_OF_YEAR);
    }
}
