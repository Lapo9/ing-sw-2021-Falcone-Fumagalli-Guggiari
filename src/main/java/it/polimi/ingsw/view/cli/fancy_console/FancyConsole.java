package it.polimi.ingsw.view.cli.fancy_console;

import static it.polimi.ingsw.view.cli.fancy_console.Style.*;

/**
 * Allows to customize the strings in output from MainCLI
 */
public class FancyConsole {

    /**
     * Reset the text, we have to reset the text before printing another string
     * @return Code to clear any text effect
     */
    public static String reset(){
        return "\033[0m";
    }

    /**
     * Allows to complete the command "\033[xm" replacing "x" with the number chosen from the enum (with whom we'll customize the string)
     * @param style chosen to customize the String
     * @return customized string
     */
    private static String style(Style style) {
        return "\033[" + style.toString() + "m";
    }



    /**
     * Makes the given string bold
     * @param s string to customize
     * @return the bold string
     */
    public static String BOLD(String s) {
        return style(BOLD) + s + reset();
    }

    /**
     * Makes the given string underlined
     * @param s string to customize
     * @return the underlined string
     */
    public static String UNDERLINED(String s) {
        return style(UNDERLINED) + s + reset();
    }

    /**
     * Makes the given string framed
     * @param s string to customize
     * @return the framed string
     */
    public static String FRAMED(String s) {
        return style(FRAMED) + s + reset();
    }


    /**
     * Makes the letters of the given string red
     * @param s string to customize
     * @return the red string
     */
    public static String RED(String s) {
        return style(RED) + s + reset();
    }

    /**
     * Makes the background of the given string red with white letters
     * @param s string to customize
     * @return the background red string
     */
    public static String BACK_RED(String s) {
        return style(BACK_RED) + s + reset();
    }


    /**
     * Makes the letters of the given string green
     * @param s string to customize
     * @return the green string
     */
    public static String GREEN(String s) {
        return style(GREEN) + s + reset();
    }

    /**
     * Makes the background of the given string green with white letters
     * @param s string to customize
     * @return the background green string
     */
    public static String BACK_GREEN(String s) {
        return style(BACK_GREEN) + s + reset();
    }


    /**
     * Makes the letters of the given string yellow
     * @param s string to customize
     * @return the yellow string
     */
    public static String YELLOW(String s) {
        return style(YELLOW) + s + reset();
    }

    /**
     * Makes the background of the given string yellow with white letters
     * @param s string to customize
     * @return the background yellow string
     */
    public static String BACK_YELLOW(String s) {
        return style(BACK_YELLOW) + s + reset();
    }


    /**
     * Makes the letters of the given string blue
     * @param s string to customize
     * @return the blue string
     */
    public static String BLUE(String s) {
        return style(BLUE) + s + reset();
    }

    /**
     * Makes the background of the given string blue with white letters
     * @param s string to customize
     * @return the background blue string
     */
    public static String BACK_BLUE(String s) {
        return style(BACK_BLUE) + s + reset();
    }


    /**
     * Makes the letters of the given string magenta
     * @param s string to customize
     * @return the magenta string
     */
    public static String MAGENTA(String s) {
        return style(MAGENTA) + s + reset();
    }

    /**
     * Makes the background of the given string magenta with white letters
     * @param s string to customize
     * @return the background magenta string
     */
    public static String BACK_MAGENTA(String s) {
        return style(BACK_MAGENTA) + s + reset();
    }


    /**
     * Makes the letters of the given string cyan
     * @param s string to customize
     * @return the cyan string
     */
    public static String CYAN(String s) {
        return style(CYAN) + s + reset();
    }

    /**
     * Makes the background of the given string cyan with white letters
     * @param s string to customize
     * @return the background cyan string
     */
    public static String BACK_CYAN(String s) {
        return style(BACK_CYAN) + s + reset();
    }


    /**
     * Makes the letters of the given string white
     * @param s string to customize
     * @return the white string
     */
    public static String WHITE(String s) {
        return style(WHITE) + s + reset();
    }

    /**
     * Makes the background of the given string white with white letters
     * @param s string to customize
     * @return the background white string
     */
    public static String BACK_WHITE(String s) {
        return style(BACK_WHITE) + s + reset();
    }


    /**
     * Makes the letters of the given string black
     * @param s string to customize
     * @return the black string
     */
    public static String BLACK(String s) {
        return style(BLACK) + s + reset();
    }

    /**
     * Makes the background of the given string black with white letters
     * @param s string to customize
     * @return the background black string
     */
    public static String BACK_BLACK(String s) {
        return style(BACK_BLACK) + s + reset();
    }


}
