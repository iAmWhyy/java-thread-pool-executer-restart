import javax.annotation.processing.RoundEnvironment;

public class TestTaskExe implements Runnable{
   private boolean u;

   private SyncedThreadTestObj oiu;

   public TestTaskExe(boolean fiveSecTasker, SyncedThreadTestObj obj) {
      u = fiveSecTasker;
      oiu = obj;
   }

   @Override
   public void run() {
      while (true) {
         if (u) {
            oiu.methodLastFiveSecs();
         } else {
            oiu.methodLastOneSec();
         }
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
}
