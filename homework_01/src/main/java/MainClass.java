public class MainClass {

    public static void main(String[] args) {
        int maxCapacity = 5;
        int cannibalsNumber = 13;

        Bowl bowl = new Bowl(maxCapacity);

        Chief chief = new Chief(bowl);

        Thread thread = new Thread(chief);
        thread.start();

        for (int i = 0; i < cannibalsNumber; i++) {
            new Thread(new Cannibal(i, bowl)).start();
        }

    }

}
