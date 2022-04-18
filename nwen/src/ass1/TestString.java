import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;


public class TestString {
    /**
     * public static final BigInteger[][] dataset={
     * {"999101101","999101201","999101301"),new BigInteger("999101401"),new BigInteger("999101501")},
     * {new BigInteger("999191101"),new BigInteger("999181201"),new BigInteger("999171301"),new BigInteger("999161401"),new BigInteger("999151501")},
     * {new BigInteger("-999101101"),new BigInteger("-999101201"),new BigInteger("999101301"),new BigInteger("-999101401"),new BigInteger("999101501")},
     * {new BigInteger("0"),new BigInteger("-999101201"),new BigInteger("1"),new BigInteger("-999101401"),new BigInteger("999101501")},
     * {},
     * manyOrdered(10000),
     * manyReverse(10000),
     * manyRandom(10000)
     * };
     */

    public static final String[][] dataset = {
            {"a", "b", "c", "d", "e"},
            {"A", "b", "c", "D", "r"},
            {"G", "F", "L", "Q", "U"},
            {"lilly","hello","earth","fire","place"},
            {},manyOrdered(10000),
            manyReverse(10000),
           manyRandom(10000)


    };


    static private String[] manyRandom(int size){

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random r = new Random(0);
        String[] result = new String[size];
        for(int i=0;i<size;i++){
            String s="";

            for(int j=0;j<5;j++) {
               s += alphabet.charAt(r.nextInt(26));
            }
            result[i]=s;
        }
        return result;
    }

    static private String[] manyReverse(int size){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random r = new Random(0);
        String[] result = new String[size];

        int x=25;
        for(int i=0;i<size;i++){

            StringBuilder s= new StringBuilder();
            s.append(alphabet.charAt(x));

            result[i]= s.toString();
            x--;
            if(x<0){x=25;}
        }
        return result;
    }

    static private String[] manyOrdered(int size){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random r = new Random(0);
        String[] result = new String[size];
        int x=0;
        for(int i=0;i<size;i++){
            if(x==24){x=0;}
            String s="";

            s += alphabet.charAt(x);

            result[i]=s;
            x++;
        }
        return result;
    }




    @Test
    public void testISequentialSorter() {
        Sorter s=new ISequentialSorter();
        for(String[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMSequentialSorter() {
        Sorter s=new MSequentialSorter();
        for(String[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMParallelSorter1() {
        Sorter s=new MParallelSorter1();
        for(String[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMParallelSorter2() {
        Sorter s=new MParallelSorter2();
        for(String[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMParallelSorter3() {
        Sorter s=new MParallelSorter3();
        for(String[]l:dataset){TestHelper.testData(l,s);}
    }
}
