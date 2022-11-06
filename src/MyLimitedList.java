import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class MyLimitedList<T> {
   private int maxSize;

   private Condition writerThread;

   private Condition readerThread;

   private int itemCountForUnlock;

   private List<T> list;
   
   public MyLimitedList(int maxElements, Condition writerThread, Condition readerThread, int itemCountForReadThreadUnlock) {
      this.maxSize = maxElements;
      this.writerThread = writerThread;
      this.readerThread = readerThread;
      this.itemCountForUnlock = itemCountForReadThreadUnlock;
      list = Collections.synchronizedList(new ArrayList<>());
   }

   /**
    * Adds element to list, if count smaller than maxSize.
    * @param element
    * @return true, when added, and false, if no space available
    */
   public synchronized boolean offer(T element){
      if (list.size() < maxSize) {
         list.add(element);
         if (list.size() >= itemCountForUnlock) {
            readerThread.signalAll();
         }
         return true;
      }
      return false;
   }

   /**
    * this method extract all Elements of list and adds them to a new list, which gets returned. Return null, if list.count() equals zero.
    * @return
    */
   public synchronized List<T> popAllElements(){
      if (list.size() == 0) {
         return null;
      }
      List<T> tempForRet = new ArrayList<>();
      for (T element : list) {
         tempForRet.add(element);
      }
      list.clear();
      writerThread.signalAll();
      return tempForRet;
   }
}
