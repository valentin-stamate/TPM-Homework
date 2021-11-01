package two;

import util.Util;

public class Cannibal implements Runnable {
    private final Bowl bowl;
    private final int id;
    private int label = Integer.MAX_VALUE;

    public Cannibal(int id, Bowl bowl) {
        this.bowl = bowl;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            Util.sleep(500);
            bowl.eatFood(this);
        }
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public int getId() {
        return id;
    }
}
