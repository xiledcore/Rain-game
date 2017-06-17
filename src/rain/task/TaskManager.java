package rain.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class TaskManager {
	
	private final ExecutorService executorService;
	
	public TaskManager(int numberOfThreads) {
		if (numberOfThreads > 1) {
			executorService = Executors.newScheduledThreadPool(numberOfThreads);
		} else {
			executorService = Executors.newScheduledThreadPool(3);
		}
	}
	
	public void executeTask(Runnable task) {
		executorService.execute(task);
	}
	
	public void submitScheduledTask(long time, long initialDelay, Runnable task) {
		((ScheduledExecutorService) executorService).scheduleAtFixedRate(task, initialDelay, time, TimeUnit.SECONDS);
	}
}
