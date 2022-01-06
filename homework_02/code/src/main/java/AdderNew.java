public class AdderNew implements Runnable {
    OptimisticListNew<Integer> list;

    AdderNew(OptimisticListNew<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int max = 25000;
        for (int i = 1; i <= max; i++) {
            list.add(i);
        }

//        for (int i = 0; i < 1000; i++) {
//            list.remove(i);
//        }
//
//        int k = 0;
//        for (int i = 0; i < 2500; i++) {
//            if (list.contains(i)) {
//                k++;
//            }
//        }

//        System.out.printf("Thread found %d elements\n", k);
    }
}
