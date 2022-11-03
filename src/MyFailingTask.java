public class MyFailingTask implements Runnable{

   private String taskMsg;

   public MyFailingTask(String msg){
      taskMsg = msg;
   }

   @Override
   public void run() {
      System.out.println(taskMsg);
      
      try {
         Thread.sleep(2000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      throw new RuntimeException("Failed Task Failed");
   }
   
}
