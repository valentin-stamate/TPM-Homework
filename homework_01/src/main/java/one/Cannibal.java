package one;

import util.Util;

public class Cannibal implements Runnable {
    private final Bowl bowl;
    private final int id;

    public Cannibal(int id, Bowl bowl) {
        this.bowl = bowl;
        this.id = id;
    }

    @Override
    public void run() {
        Util.sleep(500);
        bowl.eatFood(id);
    }

}
