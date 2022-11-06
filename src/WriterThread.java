import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Condition;

public class WriterThread implements Runnable{
   private MyLimitedList<String> sharedList;

   private Condition thisThread;

   private String lastMsgIfCantBeAdded = null;

   public WriterThread(MyLimitedList<String> list, Condition thisThread){
      sharedList = list;
      this.thisThread = thisThread;
   }

   @Override
   public void run() {
      while (true) {
         String msgForAdding = (lastMsgIfCantBeAdded == null) ? new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()) : lastMsgIfCantBeAdded;

         if (sharedList.offer(msgForAdding)) {
            lastMsgIfCantBeAdded = null;
         } else {
            lastMsgIfCantBeAdded = msgForAdding;
            System.out.println("Writer Thread stops, because MyLimitedList is full");
            try {
               thisThread.await();
            } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            System.out.println("Wirter thread was started");
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
