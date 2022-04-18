import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static javax.swing.UIManager.get;


/**
 * Completable Futures
 * Completable Futures are use for asynchronous programming so that we do not have to wait for the completion of tasks and can execute tasks in parallel.
 * In this example I have executed the left and right lists' (sort,merge) functions in parallel. The advantages of CompletableFutures over Futures is that Futures do not have exception handling,
 * you can't perform actions on a futures result without blocking with the .get() method as it blocks until the result is available.
 * We can also have multiple streams going at once.
 * From using Completable Futures I have learnt why we should use completable futures over futures and from the advantages over using normal futures.
 * */

public class MParallelSorter2 implements Sorter {

  private MSequentialSorter s = new MSequentialSorter();

  private static final ExecutorService t= Executors.newWorkStealingPool(10);

  @Override
  public <T extends Comparable<? super T>> List<T> sort(List<T> list)  {

    if(list.size()<2){return list;}
    if(list.size()<20){new ISequentialSorter().sort(list);}

    int mid = list.size()/2;

    CompletableFuture<List<T>> left = CompletableFuture.supplyAsync(()->{
    return s.sort(list.subList(0, mid));},t);

  CompletableFuture<List<T>> right = CompletableFuture.supplyAsync(()->{
      return s.sort(list.subList(mid, list.size()));
    } ,t);

   // List<T> right = s.sort(list.subList(mid, list.size())) ;




    return  s.merg(left.join(),right.join());

  }



}

