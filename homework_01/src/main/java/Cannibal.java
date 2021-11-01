public class Cannibal implements Runnable {
    private final Bowl bowl;
    private final int id;

    public Cannibal(int id, Bowl bowl) {
        this.bowl = bowl;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bowl.eatFood(id);
    }

}
