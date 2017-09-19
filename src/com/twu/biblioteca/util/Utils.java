package com.twu.biblioteca.util;

import java.io.Console;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    final private static int WELCOME_TIME=800;

    public static Object read(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher((CharSequence) str);
        if (matcher.matches()) return Integer.parseInt(str);
        else return str;
    }

    private static String readPWD(){
        Console cons = System.console();
        return String.valueOf(cons.readPassword());
    }

    public static void screenFroze() {
        try {
            Thread.sleep(WELCOME_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
