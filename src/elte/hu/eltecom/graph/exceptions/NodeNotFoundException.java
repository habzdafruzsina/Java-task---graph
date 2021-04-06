package elte.hu.eltecom.graph.exceptions;

public class NodeNotFoundException extends Exception{

    public NodeNotFoundException(int userId){
        super("Node with id: " + userId + " is not found!");
    }
}
