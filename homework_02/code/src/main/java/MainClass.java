
public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 30; i++) {
            modifiedVersion();
            oldVersion();
        }
    }

    public static void modifiedVersion() throws InterruptedException {
        OptimisticListNew<Integer> list = new OptimisticListNew<>();
        long currentTimeA = System.currentTimeMillis();

        AdderNew adder1 = new AdderNew(list);
        AdderNew adder2 = new AdderNew(list);
        AdderNew adder3 = new AdderNew(list);
        AdderNew adder4 = new AdderNew(list);

        Thread a = new Thread(adder1);
        Thread b = new Thread(adder2);
        Thread c = new Thread(adder3);
        Thread d = new Thread(adder4);

        a.start();
        b.start();
        c.start();
        d.start();

        a.join();
        b.join();
        c.join();
        d.join();

        long currentTimeB = System.currentTimeMillis();

        System.out.printf("Time passed for modified %dms\n", currentTimeB - currentTimeA);
    }

    public static void oldVersion() throws InterruptedException {
        OptimisticListOld<Integer> list = new OptimisticListOld<>();
        long currentTimeA = System.currentTimeMillis();

        AdderOld adder1 = new AdderOld(list);
        AdderOld adder2 = new AdderOld(list);
        AdderOld adder3 = new AdderOld(list);
        AdderOld adder4 = new AdderOld(list);

        Thread a = new Thread(adder1);
        Thread b = new Thread(adder2);
        Thread c = new Thread(adder3);
        Thread d = new Thread(adder4);

        a.start();
        b.start();
        c.start();
        d.start();

        a.join();
        b.join();
        c.join();
        d.join();

        long currentTimeB = System.currentTimeMillis();
        System.out.printf("Time passed for original %dms\n", currentTimeB - currentTimeA);
    }

}