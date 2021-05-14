package sample;

import java.util.Arrays;
import java.util.stream.Stream;

public class testFile {

    public static void main(String[] args) {

        String string = "Hello World? hellow world";

        System.out.println(string.length());
        String[] list= string.split(" ");

        String s = "Hello World?";
        String [] sLen = s.split(" ");
        System.out.println(Arrays.toString(Arrays.copyOfRange(list, 0, sLen.length)));
        System.out.println(Arrays.toString(sLen));


        if (Arrays.equals(sLen, Arrays.copyOfRange(list, 0, sLen.length))){
            System.out.println(sLen);
        }

    }
}
