package sg.edu.rp.c346.id20018318.ourndpsongs;

import java.io.Serializable;

public class Year implements Serializable {
    private int year;

    public Year(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.valueOf(year);
    }
}
