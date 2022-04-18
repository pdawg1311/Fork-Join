import java.util.ArrayList;
import java.util.List;

/**
 * Merge Sort
 * Sequentially sorting is good for small amounts of data that would barely make a difference if we were split each task up as the time it would take would be small difference since the data set size is smaller.
 * This is the base algorithm so there would be no parallel sort merge-sort if it weren't for the first invention of merge-sort.
 *I have learnt how the merge sort sequentially sorts the split left and right sublists to eventually merge them into one sorted list.
 * */

public class MSequentialSorter implements Sorter {

  @Override
  public <T extends Comparable<? super T>> List<T> sort(List<T> list) {

    if(list.size()<2){return list;}


    int mid = list.size()/2;
    List<T> left = sort(list.subList(0,mid));
    List<T>right=sort(list.subList(mid, list.size()));
    return merg(left,right) ;
  }


  <T extends Comparable<? super T>> List<T> merg(List<T> left, List<T> right){
    List<T> list= new ArrayList<>();
    int i =0;
    int j =0;
    int k=0;


    while(i<left.size()&&j< right.size()){
      if(left.get(i).compareTo(right.get(j))<0){
        list.add(k,left.get(i));
        i++;
      }
      else{
        list.add(k,right.get(j));
        j++;
      }
      k++;
    }

    while(i<left.size()){
      list.add(k,left.get(i));
      i++;
      k++;
    }
    while(j< right.size()){
      list.add(k,right.get(j));
      j++;
      k++;

    }

    return list;
  }

}