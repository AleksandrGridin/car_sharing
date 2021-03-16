package carsharing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console {

    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static String read() {
        String msg = "";
        try {
            msg =  bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public static void write(String msg) {
        System.out.println(msg);
    }

    public static void close() {
        try {
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
