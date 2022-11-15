package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> stringsListHistory = new LinkedList<>();
    private String nxtString;
    @Override
    public void setNxtToPrint(String newNextString) {
        if(newNextString != null) {
            this.nxtString = newNextString;
        }
        else {
            throw new IllegalArgumentException("Null values aren't accepted!");
        }
    }

    @Override
    public String getNxtToPrint() {
        return this.nxtString;
    }

    @Override
    public List<String> getPrintedString() {
        return List.copyOf(this.stringsListHistory);
    }

    @Override
    public void printCurrentString() {
        if(this.nxtString != null) {
            this.stringsListHistory.add(this.nxtString);
            System.out.println(this.nxtString);
        }
        else {
            throw new IllegalStateException("String set is null!");
        }
    }

}
