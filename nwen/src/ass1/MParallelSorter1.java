import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static javax.swing.UIManager.get;

/**
 * Futures
 * The future merge sort lets us execute two line asynchronously which makes it faster than sequential merge sort as it sorts the left and right split lists at the same time.
 * In my opinion it was also far easier to understand and implement Futures instead of Completeable Futures because just by looking at the code it is obvious
 * which commands are being executed at the same time. I have learnt how to implement Futures with the merge sort algorithm to make it faster by executing functions in parallel.
 * I have also learnt why using Futures is faster, than sequentially processing the dataset.
 * */

public class MParallelSorter1 implements Sorter {

  private static final ExecutorService t= Executors.newFixedThreadPool(10);
  private MSequentialSorter s = new MSequentialSorter();

  @Override
  public <T extends Comparable<? super T>> List<T> sort(List<T> list) {

    if(list.size()<2){return list;}
    if(list.size()<20){new ISequentialSorter().sort(list);}

    int mid = (list.size())/2;
    //List<T>left=s.sort(list.subList(0,mid));
    //List<T>right=s.sort(list.subList(mid, list.size()));

    Future<List<T>> left = t.submit(()->s.sort(list.subList(0,mid))) ;
    List<T> right = s.sort(list.subList(mid, list.size())) ;
    return s.merg(get(left),right);
  }




  public static <T> T get (Future<T> f){
    try{
      return f.get();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }


}