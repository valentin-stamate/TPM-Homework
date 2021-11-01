import two.Bowl;
import two.Chief;
import two.Cannibal;

import java.util.ArrayList;
import java.util.List;

public class MainClassTwo {

    public static void main(String[] args) {
        int maxCapacity = 5;
        int cannibalsNumber = 13;


        List<Cannibal> cannibals = new ArrayList<>();
        Bowl bowl = new Bowl(maxCapacity, cannibals);

        for (int i = 0; i < cannibalsNumber; i++) {
            cannibals.add(new Cannibal(i, bowl));
        }

        Chief chief = new Chief(bowl);

        Thread thread = new Thread(chief);
        thread.start();

        for (Cannibal cannibal : cannibals) {
            new Thread(cannibal).start();
        }

    }

}
