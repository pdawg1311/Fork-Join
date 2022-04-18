import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import static java.util.concurrent.ForkJoinTask.invokeAll;

/**
 * Fork-Join
 * The advantages of the forkjoin model is that there isn't communication between processes. The forkjoin model works by breaking down processes until the if statement threshold and
 * delegates the tasks like so. Communication between processes slows everything down because before a process can execute they must communicate with each other first.
 * I have learnt how to implement the forkjoin model and how it works to speed up computation, it effectively recursively breaks down the task at hand to smaller tasks seen in
 * the computation method where we create the left and right forks, the if statement is what breaks the recursion.
 *
 *
 * */

public class MParallelSorter3 implements Sorter {
  public static ForkJoinPool forkJoinPool = new ForkJoinPool(10);
  private MSequentialSorter s = new MSequentialSorter();


  @Override
  public <T extends Comparable<? super T>> List<T> sort(List<T> list) {

    if(list.size()<2){return list;}
    if(list.size()<20){new ISequentialSorter().sort(list);}

    //int mid = (list.size())/2;

   // work left = new work(list.subList(0,mid));
   // work right = new work(list.subList(mid,list.size()));
      //invokeAll(left,right);
      int mid = list.size()/2;

       work l = new work(list.subList(0, mid));
      work r = new work(list.subList(mid, list.size()));
 invokeAll(l,r);

    return s.merg(l.getResult(),r.getResult());
  }



}


class work extends RecursiveTask{

  private MSequentialSorter s = new MSequentialSorter();
  private List t;
  private List result;



  public List getResult() {
    return this.t;
  }

  public work(List list){this.t=list;}

  @Override
  protected Object compute() {

      int mid = this.t.size()/2;
      if(this.t.size()>1) {


          work left = new work(t.subList(0, mid));
          work right = new work(t.subList(mid, t.size()));
          invokeAll(left,right);


          this.t = s.merg(left.t, right.t);
          // this.result=s.sort(t);
      }

      return null;
  }



}