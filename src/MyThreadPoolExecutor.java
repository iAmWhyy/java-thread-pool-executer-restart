import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;

class MyThreadPoolExecutor extends ThreadPoolExecutor {
   public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
           TimeUnit unit, BlockingQueue<Runnable> workQueue) {
       super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
   }
   
   @Override
   public void afterExecute(Runnable r, Throwable t) {
       super.afterExecute(r, t);
       // If submit() method is called instead of execute()
       if (t == null && r instanceof Future<?>) {
           try {
               Object result = ((Future<?>) r).get();
           } catch (CancellationException e) {
               t = e;
           } catch (ExecutionException e) {
               t = e.getCause();
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
       if (t != null) {
           // Exception occurred
           System.err.println("Uncaught exception is detected! " + t
                   + " st: " + Arrays.toString(t.getStackTrace()));
           // ... Handle the exception
           // Restart the runnable again
           execute(r);
       }
       // ... Perform cleanup actions
   }
}