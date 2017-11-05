package week1;
import java.io.* ;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

class Edge implements Comparable<Edge>{
    Integer v1;
    Integer v2;
    Integer distance;

    Edge(Integer v1, Integer v2, Integer distance) {
        this.v1 = v1;
        this.v2 = v2;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge e) {
        return getDistance() - e.getDistance();
    }

    public Integer getDistance() {
        return distance;
    }
}

class Graph {
    ArrayList<Integer> verticles = new ArrayList<Integer>();
    ArrayList<Edge> edges = new ArrayList<Edge>();

    Graph() {

    }

    void addVerticle(Integer v){
        if(!verticles.contains(v)) {
            verticles.add(v);
        }
    }

    void addEdge(Edge e) {
        // skip checking same edge in graph
        edges.add(e);
        addVerticle(e.v1);
        addVerticle(e.v2);
    }

    boolean isExisted(Integer v){
        return verticles.contains(v);
    }
}

public class Assignment1_3 {
    public static void main(String args[]){
        //get data from file
        FileInputStream textFile = null;
        Graph graph = new Graph();
        try {
            textFile = new FileInputStream("/Users/DoMinhHai/Documents/12_Cousera/Algorithm2/src/week1/edges.txt");
            Scanner inFile = new Scanner (textFile);
            System.out.println("File data.txt has been opened.");
            String oneLine = inFile.nextLine();
            String first_line[] = oneLine.split(" ");
            Integer numberOfVerticles = Integer.parseInt(first_line[0]);
            Integer numberOfEdges = Integer.parseInt(first_line[1]);

            for(int i=0; i< numberOfEdges; i++) {
                String line = inFile.nextLine();
                String ed[] = line.split(" ");
                Integer v1 = Integer.parseInt(ed[0]);
                Integer v2 = Integer.parseInt(ed[1]);
                Integer distance = Integer.parseInt(ed[2]);
                Edge e = new Edge(v1, v2, distance);
                graph.addEdge(e);
            }
            System.out.println(graph.verticles.size() + "--" + numberOfVerticles);
            System.out.println(graph.edges.size() + "--" + numberOfEdges);

            MST(graph);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //FileInputStream textFile = new FileInputStream ("/Users/DoMinhHai/Documents/12_Cousera/Algorithm2/src/week1/test2.txt");
    }


    static void MST(Graph graph){
        Collections.sort(graph.edges);
        Graph valueGraph = new Graph();
        long total = 0;
        // init first verticle
        valueGraph.verticles.add(graph.verticles.get(0));

        while (valueGraph.verticles.size() < graph.verticles.size()) {
            Edge current = null;
            for (Edge e : graph.edges) {
                System.out.println(e.getDistance());
                if ((!valueGraph.isExisted(e.v2) && valueGraph.isExisted(e.v1)) || (!valueGraph.isExisted(e.v1) && valueGraph.isExisted(e.v2))) {
                    //save as current min edge
                    if(current == null){
                        current = e;
                    }
                    else if(current.distance > e.getDistance()){
                        current = e;
                    }
                }
            }
            //add current into valueGraph
            if(current != null) {
                total += current.getDistance();
                valueGraph.addEdge(current);
            }
        }
        System.out.println(total);
        System.out.println(valueGraph.verticles.size());
//        for(Integer v: graph.verticles){
//            if(!valueGraph.isExisted(v)){
//                // try to find minumum edges in graph
//
//            }
//        }
    }
}
