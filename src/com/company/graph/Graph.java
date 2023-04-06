package com.company.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    ArrayList<LinkedList<Node>> alist;

    public Graph() {
        alist = new ArrayList<>();
    }

    public void addNode(Node node) {
        LinkedList<Node> currentList = new LinkedList<>();
        currentList.add(node);
        alist.add(currentList);
    }

    public void addEdge(int scr, int destination) {
        LinkedList<Node> currentList = alist.get(scr);
        Node destinationNode = alist.get(destination).get(0);
        currentList.add(destinationNode);
    }

    public boolean checkEdge(int scr, int destination) {
        LinkedList<Node> currentList = alist.get(scr);
        Node destinationNode = alist.get(destination).get(0);

        for (Node node : currentList) {
            if (node == destinationNode) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (LinkedList<Node> currentList : alist) {
            for (Node node : currentList) {
                System.out.print(node.data + " -> ");
            }
            System.out.println();
        }
    }

    public void breadthFirstSearch(int src) {
        boolean[] visited = new boolean[alist.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[src] = true;
        queue.offer(src);

        while (queue.size() != 0) {
            src = queue.poll();
            System.out.println(alist.get(src).get(0).data + " = visited");

            for (int i = 0; i < alist.get(src).size(); i++) {
                int n = alist.get(src).get(i).indexInArray;
                if (!visited[n]) {
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }
    }

    public void breadthFirstSearchVersion2(int src) {
        boolean[] visited = new boolean[alist.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[src] = true;
        queue.offer(src);

        while (queue.size() != 0) {
            src = queue.poll();
            System.out.println(alist.get(src).get(0).data + " = visited");

            for (int i = 0; i < alist.get(src).size(); i++) {
                int n = getListElementIndexByNodes(alist.get(src).get(i));
                if (!visited[n]) {
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }
    }

    //Костыль №2
    private int getListElementIndexByNodes(Node node) {
        for (int i=0; i < alist.size(); i ++) {
            if (alist.get(i).get(0).equals(node)) {
                return i;
            }
        }
        return -1;
    }
}
