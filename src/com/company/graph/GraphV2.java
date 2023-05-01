package com.company.graph;

import java.util.*;

public class GraphV2 {
    Map<Node, List<Node>> edges;

    public GraphV2() {
        edges = new HashMap<>();
    }

    public void addNode(Node node) {
        List<Node> edgesList = new ArrayList<>();
        //edgesList.add(node);
        edges.put(node, edgesList);
    }

    public void addEdge(Node src, Node destination) {
        List<Node> destinations = edges.get(src);
        destinations.add(destination);
        edges.put(src, destinations);
    }

    private List<Node> getNodeEdges(Node head) {
        return edges.get(head);
    }

    public boolean checkEdge(Node src, Node destination) {
        List<Node> destinations = edges.get(src);
        for (Node node : destinations) {
            if (node == destination) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (Map.Entry<Node, List<Node>> entry : edges.entrySet()) {
            Node src = entry.getKey();
            List<Node> destinations = entry.getValue();
            System.out.print(src.data + " -> ");
            for (Node node : destinations) {
                System.out.print(node.data + " | ");
                //printDestinations(node, src);
            }
            System.out.println();
        }
    }

    /*private void printDestinations(Node head, Node src) {
        for (Node node : edges.get(head)) {
            System.out.print(node.data + " -> ");
            if (node.equals(src)) {
                break;
            }
            printDestinations(node, src);
        }
        System.out.println();
    }*/

    public void breadthFirstSearch(Node src) {
        HashMap<Node, Boolean> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        visited.put(src,true);
        queue.offer(src);

        while (queue.size() != 0) {
            src = queue.poll();
            System.out.println(src.data + " = visited");

            for (int i = 0; i < edges.get(src).size(); i++) {
                Node n = edges.get(src).get(i);
                if (visited.get(n) == null || !visited.get(n) ) {
                    visited.put(n, true);
                    queue.offer(n);
                }
            }
        }
    }

    /*
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
    }*/
}
