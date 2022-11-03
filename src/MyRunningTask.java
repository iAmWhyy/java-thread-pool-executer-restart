public class MyRunningTask implements Runnable{

   private String taskMsg;

   public MyRunningTask(String msg){
      taskMsg = msg;
   }

   @Override
   public void run() {
      System.out.println(taskMsg);
      
   }
   
}
