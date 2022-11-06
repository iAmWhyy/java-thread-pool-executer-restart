public class SyncedThreadTestObj {
   public SyncedThreadTestObj() {

   }

   public synchronized void methodLastFiveSecs() {
      System.out.println("5secs method started");
      try {
         Thread.sleep(5000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("5secs method ended");
   }

   public synchronized void methodLastOneSec() {
      System.out.println("1secs method started");
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("1secs method ended");
   }
}
