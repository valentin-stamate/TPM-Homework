package one;

import util.Util;

import java.util.concurrent.locks.ReentrantLock;

public class Bowl {
	private final int maxCapacity;
	private final ReentrantLock rl;
	private int currentCapacity = 0;

	public Bowl(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.rl = new ReentrantLock();
	}
	
	public boolean isEmpty() {
		return currentCapacity == 0;
	}
	
	public void fillFood() {
		currentCapacity = maxCapacity;
	}
	
	public void eatFood(int cannibalId) {
		long currentTimeOne = Util.currentTime();
		rl.lock();

		while (isEmpty()) {
			Util.sleep(50);
		}
		
		Util.sleep(500);
		currentCapacity--;

		System.out.printf("Cannibal %-2d eating -> bowl capacity %-2d", cannibalId, currentCapacity);
		long currentTimeTwo = Util.currentTime();
		System.out.printf(" time waited : %s\n", Util.millisToTime(currentTimeTwo - currentTimeOne));

		rl.unlock();
	}
	
}
