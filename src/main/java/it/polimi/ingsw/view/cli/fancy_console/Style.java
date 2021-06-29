package it.polimi.ingsw.view.cli.fancy_console;

public enum Style {
    BOLD("1"), UNDERLINED("4"), FRAMED("51"),

    RED    ("31"),       BACK_RED     ("41"),
    GREEN  ("32"),       BACK_GREEN   ("42"),
    YELLOW ("33"),       BACK_YELLOW  ("43"),
    BLUE   ("34"),       BACK_BLUE    ("44"),
    MAGENTA("35"),       BACK_MAGENTA ("45"),
    CYAN   ("36"),       BACK_CYAN    ("46"),
    WHITE  ("37"),       BACK_WHITE   ("47"),
    BLACK  ("30"),       BACK_BLACK   ("40");

    private String colorCode; //the code of the chosen color

    /**
     * Sets the colorCode
     * @param colorCode given
     */
    private Style(String colorCode){
        this.colorCode = colorCode;
    }

    /**
     * Transform the name of the style in his corresponding number
     * @return the code of the chosen color
     */
    public String toString(){
        return colorCode;
    }
}
