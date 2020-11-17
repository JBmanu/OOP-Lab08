package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class SimpleController implements Controller{
    private final List<String> stringHistory = new LinkedList<>();
    private String nextString;

    public void setNextStringToPrint(String nextString) {
        this.nextString = Objects.requireNonNull(nextString, "This method does not accept null values.");
    }

    public String getNextStringToPrint() {
        return this.nextString;
    }

    public List<String> getPrintedStringsHistory() {
        return stringHistory;
    }

    public void printCurrentString() throws IllegalStateException{
        if (this.nextString == null) {
            throw new IllegalStateException("There is no string set");
    }
        stringHistory.add(this.nextString);
        System.out.println(this.nextString);
    }

}
