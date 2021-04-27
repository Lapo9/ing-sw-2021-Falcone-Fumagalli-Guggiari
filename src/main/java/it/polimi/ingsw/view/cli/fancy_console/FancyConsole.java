package it.polimi.ingsw.view.cli.fancy_console;

import static it.polimi.ingsw.view.cli.fancy_console.Style.*;

public class FancyConsole {


    private static String reset(){
        return "\033[0m";
    }


    private static String style(Style style) {
        return "\033[" + style.toString() + "m";
    }




    //FIXME it doesn't work
    public static void clear() {
        for(int i = 0; i < 1; ++i) {
            System.out.print("\r");
        }
    }





    public static String BOLD(String s) {
        return style(BOLD) + s + reset();
    }


    public static String UNDERLINED(String s) {
        return style(UNDERLINED) + s + reset();
    }


    public static String FRAMED(String s) {
        return style(FRAMED) + s + reset();
    }







    public static String RED(String s) {
        return style(RED) + s + reset();
    }

    public static String BACK_RED(String s) {
        return style(BACK_RED) + s + reset();
    }



    public static String GREEN(String s) {
        return style(GREEN) + s + reset();
    }

    public static String BACK_GREEN(String s) {
        return style(BACK_GREEN) + s + reset();
    }



    public static String YELLOW(String s) {
        return style(YELLOW) + s + reset();
    }

    public static String BACK_YELLOW(String s) {
        return style(BACK_YELLOW) + s + reset();
    }



    public static String BLUE(String s) {
        return style(BLUE) + s + reset();
    }

    public static String BACK_BLUE(String s) {
        return style(BACK_BLUE) + s + reset();
    }



    public static String MAGENTA(String s) {
        return style(MAGENTA) + s + reset();
    }

    public static String BACK_MAGENTA(String s) {
        return style(BACK_MAGENTA) + s + reset();
    }



    public static String CYAN(String s) {
        return style(CYAN) + s + reset();
    }

    public static String BACK_CYAN(String s) {
        return style(BACK_CYAN) + s + reset();
    }



    public static String WHITE(String s) {
        return style(WHITE) + s + reset();
    }

    public static String BACK_WHITE(String s) {
        return style(BACK_WHITE) + s + reset();
    }



    public static String BLACK(String s) {
        return style(BLACK) + s + reset();
    }

    public static String BACK_BLACK(String s) {
        return style(BACK_BLACK) + s + reset();
    }


}
