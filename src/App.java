import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// test implementation of error handling of following section: "Thread Pool Executor Handler"
// goal: the tasks, which throwing exceptions get restarted
// https://aozturk.medium.com/how-to-handle-uncaught-exceptions-in-java-abf819347906
public class App {
    public static void main(String[] args) throws Exception {
        // Create a fixed thread pool executor
        ExecutorService threadPool = new MyThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        threadPool.execute(new MyFailingTask("Das ist die fehlschlagene Task, die immer wieder gestartet werden sollte"));
        threadPool.execute(new MyRunningTask("Das ist die erste Running Task, die nur einmal laufen sollte"));
        threadPool.execute(new MyRunningTask("Das ist die zweite Running Task, die nur einmal laufen sollte"));
        // ...
    }
}
