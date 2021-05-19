package sample;

import java.util.Arrays;
import java.util.stream.Stream;

public class testFile {

    public static void main(String[] args) {

        String string = "Hello World? hellow world";

        for (int i=0;i<6;i++) {

            System.out.println(Math.toRadians(i * 360 / 6)+" Sin "+Math.sin(Math.toRadians(i * 360 / 6)));
        }

    }
}
