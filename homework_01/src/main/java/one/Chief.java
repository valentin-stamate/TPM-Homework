package one;

import util.Util;

public class Chief implements Runnable {
	private final Bowl bowl;
	
	public Chief(Bowl bowl) {
		this.bowl = bowl;
	}

	@Override
	public void run() {
		while (true) {
			if (bowl.isEmpty()) {
				System.out.println("Chief cooking.");
				Util.sleep(1500);
				bowl.fillFood();
			}
			
			Util.sleep(100);
		}
	}

}
