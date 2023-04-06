package com.company;

import com.company.graph.Graph;
import com.company.graph.Node;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        /*executeEx88();
        executeEx349();
        executeEx435();
        executeEx1();*/
        bfs();
    }

    private static void executeEx88() {
        System.out.println("Execute exercise 88");
        new Ex88().merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        new Ex88().merge(new int[]{1}, 1, new int[]{}, 0);
        new Ex88().merge(new int[]{0}, 0, new int[]{1}, 1);
    }

    private static void executeEx349() {
        System.out.println("Execute exercise 349");
        System.out.println("Result output: " + Arrays.toString(new Ex349().intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
        System.out.println();
        System.out.println("Result output: " + Arrays.toString(new Ex349().intersection(new int[]{2, 1}, new int[]{1, 2})));
        System.out.println();
        System.out.println("Result output: " + Arrays.toString(new Ex349().intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println();
    }

    private static void executeEx435() {
        System.out.println("Execute exercise 435");
        System.out.println("Result output: " + new Ex435().eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        System.out.println();
        System.out.println("Result output: " + new Ex435().eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        System.out.println();
        System.out.println("Result output: " + new Ex435().eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
        System.out.println();
    }

    private static void executeEx1() {
        System.out.println("Execute exercise 1");
        System.out.println("Result output: " + new TwoSum().twoSumHashMap(new int[]{2, 7, 11, 15}, 9));
        System.out.println();
        System.out.println("Result output: " + new TwoSum().twoSumHashMap(new int[]{3, 2, 4}, 6));
        System.out.println();
        System.out.println("Result output: " + new TwoSum().twoSumHashMap(new int[]{3, 3}, 6));
        System.out.println();
    }

    private static void bfs() {
        Graph graph = new Graph();

        //Ну это кал какой-то
        graph.addNode(new Node('A', 0));
        graph.addNode(new Node('B', 1));
        graph.addNode(new Node('C', 2));
        graph.addNode(new Node('D', 3));
        graph.addNode(new Node('E', 4));

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 2);

        graph.print();

        graph.breadthFirstSearch(0);
        System.out.println();
        graph.breadthFirstSearchVersion2(0);
    }
}
