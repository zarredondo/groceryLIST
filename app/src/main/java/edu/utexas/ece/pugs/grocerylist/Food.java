package edu.utexas.ece.pugs.grocerylist;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Food {
    protected int id;
    protected String name;

    public Food(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
