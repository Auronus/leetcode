package com.company.graph;

import java.util.Objects;

public class Node {
    char data;
    //Костыль
    int indexInArray;

    public Node(char data, int indexInArray) {
        this.data = data;
        this.indexInArray = indexInArray;
    }
    public Node(char data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return data == node.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, indexInArray);
    }
}
