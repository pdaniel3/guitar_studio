//package timeTest;
//Author: Marcus Kelly
//Use for conceptual timing
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {
	static int timeCount = 0;	//for tracking
	public static void main(String[] args)
	{
		Runnable hiRun = new Runnable(){
			public void run(){
				System.out.println("HelloWorld");
				System.out.println("Time count: " + timeCount );
				timeCount = timeCount + 250;	//every 250 miliseconds
				//250 milliseconds = 1/4 second
			}
		};
		
		ScheduledExecutorService execute = Executors.newScheduledThreadPool(1);
		execute.scheduleAtFixedRate(hiRun, 0, 250, TimeUnit.MILLISECONDS);
		
	}
}
