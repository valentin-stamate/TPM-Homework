package two;

import util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Bowl {
	private final int maxCapacity;
	private int currentCapacity = 0;

	private final ReentrantLock rl;
	private final List<Cannibal> cannibals;

	private int minLabel;
	private int maxLabel = 0;

	public Bowl(int maxCapacity, List<Cannibal> cannibals) {
		this.maxCapacity = maxCapacity;
		this.cannibals = cannibals;
		this.rl = new ReentrantLock();
	}
	
	public boolean isEmpty() {
		return currentCapacity == 0;
	}
	
	public void fillFood() {
		currentCapacity = maxCapacity;
	}
	
	public void eatFood(Cannibal cannibal) {
		rl.lock();
		long currentTimeOne = Util.currentTime();
		cannibal.setLabel(maxLabel++);
		rl.unlock();

		while (cannibal.getLabel() != minLabel) {
			minLabel = getMinLabel(cannibals);
			Util.sleep(100);
		}

		while (isEmpty()) {
			Util.sleep(50);
		}

		Util.sleep(500);
		currentCapacity--;

		System.out.printf("Cannibal %-2d with label %-3d eating -> bowl capacity %-2d", cannibal.getId(), cannibal.getLabel(), currentCapacity);
		long currentTimeTwo = Util.currentTime();
		System.out.printf(" time waited : %s\n", Util.millisToTime(currentTimeTwo - currentTimeOne));
	}

	static private int getMinLabel(List<Cannibal> cannibals) {
		int min = Integer.MAX_VALUE;

		for (Cannibal cannibal : cannibals) {
			min = Math.min(min, cannibal.getLabel());
		}

		return min;
	}
	
}
