package com.example.panagiotis.marvelcomics.ex;

import com.example.panagiotis.marvelcomics.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class testing {
    public static void main(String[] args){
        getHash();

    }

    private static void getHash() {
        String marvelHash = "";

        try {

            String timeStamp    = getUnixTimeStamp();
            String marvelData   = timeStamp + Constants.hash + Constants.apikey;

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(marvelData.getBytes());

            StringBuilder stringBuilder = new StringBuilder(2 * hash.length);

            for (byte b : hash)
                stringBuilder.append(String.format("%02x", b&0xff));

            marvelHash = stringBuilder.toString();
            System.out.println(marvelHash);

        } catch (NoSuchAlgorithmException e) {

            System.out.println("[DEBUG]" + " MarvelApiUtils generateMarvelHash - " +
                    "NoSuchAlgorithmException");
        }
    }

    public static String getUnixTimeStamp () {
        System.out.println(String.valueOf(System.currentTimeMillis() / 1000L));
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }
}
