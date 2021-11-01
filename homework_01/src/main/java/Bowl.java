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
		rl.lock();
		while (isEmpty()) {
			Util.sleep(50);
		}
		
		System.out.printf("Cannibal %-2d eating -> bowl capacity %-2d\n", cannibalId, currentCapacity);
		Util.sleep(500);

		currentCapacity--;
		rl.unlock();
	}
	
}
