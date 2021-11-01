package util;

public class Util {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long currentTime() {
        return System.currentTimeMillis();
    }

    public static String millisToTime(long ms) {
        long sec = ms / 1000;
        long msec = ms % 1000;
        return String.format("%-3dsec %-3dms", sec, msec);
    }
}
