package elte.hu.eltecom.graph;

import elte.hu.eltecom.user.User;

import java.util.ArrayList;
import java.util.Collection;

public class Node {

    private User value;
    private Collection<Node> neighbors;

    public Node(User user){
        value = user;
        neighbors = new ArrayList<>();
    }

    public void setValue(User user){
        value = user;
    }

    public User getValue(){
        return value;
    }

    public void addNeighbor(Node node){
        neighbors.add(node);
    }

    public void removeNeighbor(Node node){
        neighbors.remove(node);
    }
}
