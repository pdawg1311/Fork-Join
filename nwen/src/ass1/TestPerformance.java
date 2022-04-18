import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class TestPerformance {
  long timeOf(Runnable r,int warmUp,int runs) {
    System.gc();
    for(int i=0;i<warmUp;i++) {r.run();}
    long time0=System.currentTimeMillis();
    for(int i=0;i<runs;i++) {r.run();}
    long time1=System.currentTimeMillis();
    return time1-time0;
  }
  /**
   * The method timeOf returns a long and for each for loop it runs the runnable r which is the lambda in the method(msg) below
   * it calculates the time of each run by subtracting time1-time0
   *
   * */


  <T extends Comparable<? super T>>void msg(Sorter s,String name,T[][] dataset) {
    long time=timeOf(()->{
      for(T[]l:dataset){s.sort(Arrays.asList(l));}
      },20000,200);//realistically 20.000 to make the JIT do his job..
      System.out.println(name+" sort takes "+time/1000d+" seconds");
    }

    /**
     * the msg method takes a sorter to test that sorter, the name of the sorter for the print and the dataset to test the specified s on.
     * calculates time by calling the timeOf method the runnable which is being passed thru the for loop in the timeOf method is the for loop in the msg and it gets the
     * time of the sort by getting the clock time before it starts and the clock time after it has finished and subtracted finished-start.
     * */


  <T extends Comparable<? super T>>void msgAll(T[][] dataset) {
    //msg(new ISequentialSorter(),"Sequential insertion",TestBigInteger.dataset);//so slow
    //uncomment the former line to include performance of ISequentialSorter
   // msg(new MSequentialSorter(),"Sequential merge sort",dataset);
  // msg(new MParallelSorter1(),"Parallel merge sort (futures)",dataset);
    msg(new MParallelSorter2(),"Parallel merge sort (completablefutures)",dataset);
   // msg(new MParallelSorter3(),"Parallel merge sort (forkJoin)",dataset);
    }
    /**
     * The method msgALL calls the msg function with the type of sorter
     * */


  @Test
  void testBigInteger() {
    System.out.println("On the data type BigInteger");
    msgAll(TestBigInteger.dataset);
    }
    //testBigInteger tests the sort on the big integer dataset

  @Test
  void testFloat() {
    System.out.println("On the data type Float");
    msgAll(TestFloat.dataset);
    }
    //tests sort on the float dataset
  @Test
  void testPoint() {
    System.out.println("On the data type Point");
    msgAll(TestPoint.dataset);
    }
    //test sort on the point dataset

  @Test
  void testString() {
    System.out.println("On the data type String");
    msgAll(TestString.dataset);
  }

  //tests sort on the string dataset
  }
/*
With the model solutions, on a lab machine (2019) we may get those results:
On the data type Float
Sequential merge sort sort takes 1.178 seconds
Parallel merge sort (futures) sort takes 0.609 seconds
Parallel merge sort (completablefutures) sort takes 0.403 seconds
Parallel merge sort (forkJoin) sort takes 0.363 seconds
On the data type Point
Sequential merge sort sort takes 1.373 seconds
Parallel merge sort (futures) sort takes 0.754 seconds
Parallel merge sort (completablefutures) sort takes 0.541 seconds
Parallel merge sort (forkJoin) sort takes 0.48 seconds
On the data type BigInteger
Sequential merge sort sort takes 1.339 seconds
Parallel merge sort (futures) sort takes 0.702 seconds
Parallel merge sort (completablefutures) sort takes 0.452 seconds
Parallel merge sort (forkJoin) sort takes 0.492 seconds

On another lab machine in 2021 we get those results:
//they must have optimized sequential execution quite a bit!
On the data type Float
Sequential merge sort sort takes 0.635 seconds
Parallel merge sort (futures) sort takes 0.475 seconds //with a smart trick we may get 0.241 instead
Parallel merge sort (completablefutures) sort takes 0.25 seconds
Parallel merge sort (forkJoin) sort takes 0.253 seconds
On the data type Point
Sequential merge sort sort takes 0.76 seconds //with a smart trick we may get 0.296 instead
Parallel merge sort (futures) sort takes 0.505 seconds
Parallel merge sort (completablefutures) sort takes 0.279 seconds
Parallel merge sort (forkJoin) sort takes 0.296 seconds
On the data type BigInteger
Sequential merge sort sort takes 0.871 seconds
Parallel merge sort (futures) sort takes 0.574 seconds //with a smart trick we may get 0.372 instead
Parallel merge sort (completablefutures) sort takes 0.354 seconds
Parallel merge sort (forkJoin) sort takes 0.338 seconds
 
*/