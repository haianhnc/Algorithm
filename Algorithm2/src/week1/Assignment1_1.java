package week1;
import java.io.* ;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Pair implements Comparable<Pair>{
    private Integer weight;
    private Integer lenght;

    Pair(Integer key, Integer value) {
        this.weight = key;
        this.lenght = value;
    }

    Integer getWeight() {
        return weight;
    }

    Integer getLenght() {
        return lenght;
    }

    //@Override
    public int compareByRatio(Pair p) {
        if ((getWeight() - getLenght()) < (p.getWeight() - p.getLenght())) {
            return 1;
        }
        else if ((getWeight() - getLenght()) == (p.getWeight() - p.getLenght())) {
             if(p.getWeight() == getWeight()){
                 return getLenght() - p.getLenght();
             }
             else {
                 return p.getWeight() - getWeight();
             }
        }
        else {
            return -1;
        }
    }

    @Override
    public  int compareTo(Pair p) {
        if ((getWeight() * p.getLenght()) < (p.getWeight() * getLenght())) {
            return 1;
        }
        else {
            return -1;
        }
    }

}

public class Assignment1_1 {

    public static void main(String args[]) {
        //Assignment1_1_test.txt
        ArrayList<Pair> listOfPair = new ArrayList<Pair>();
        try
        {
           FileInputStream textFile = new FileInputStream ("/Users/DoMinhHai/Documents/12_Cousera/Algorithm2/src/week1/Assignment1_1_test.txt");
            //FileInputStream textFile = new FileInputStream ("/Users/DoMinhHai/Documents/12_Cousera/Algorithm2/src/week1/test2.txt");

            Scanner inFile = new Scanner (textFile);
            System.out.println("File data.txt has been opened.");
            String oneLine = inFile.nextLine();
            int number = Integer.parseInt(oneLine);
            for(int i=0; i< number; i++) {
                String line = inFile.nextLine();
                String pair[] = line.split(" ");
                Pair simplePair = new Pair(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
                listOfPair.add(simplePair);
            }

            Collections.sort(listOfPair);
            for(Pair p: listOfPair) {
                System.out.println(p.getWeight() - p.getLenght());
            }
            PrintArray(listOfPair);
           calculateWeight(listOfPair);
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File data.txt was not found!");
        }
    }

    public static void calculateWeight(ArrayList<Pair> ap) {
        long totalWeight = 0;
        int completion = 0;

        for (Pair p: ap) {
            completion = completion + p.getLenght();
            totalWeight +=  p.getWeight() * completion;
        }

        System.out.println(totalWeight);
    }

    public static void PrintArray(ArrayList<Pair> listOfItems)
    {
        System.out.println("-------------------------------");

        //scroll through each item in array and put it in variable i
        for(Pair i : listOfItems)
        {
            System.out.println(i.getWeight() + " " + i.getLenght());
        }
    }

}
