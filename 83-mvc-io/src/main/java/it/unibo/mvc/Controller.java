package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    void setNxtToPrint(String newNextString);

    String getNxtToPrint();
    
    List<String> getPrintedString();

    void printCurrentString();
}
