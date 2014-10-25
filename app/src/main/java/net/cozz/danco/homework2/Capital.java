package net.cozz.danco.homework2;

/**
 * Created by danco on 10/25/14.
 */
public class Capital {
    private long id;
    private String capital;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }


    @Override
    public String toString() {
        return capital;
    }
}
