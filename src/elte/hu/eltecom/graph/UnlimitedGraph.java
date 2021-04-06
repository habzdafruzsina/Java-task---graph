package elte.hu.eltecom.graph;

import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.user.User;

import java.util.LinkedList;
import java.util.List;

public class UnlimitedGraph implements Graph{

    private final List<Node> nodes;

    public UnlimitedGraph(){
        nodes = new LinkedList<>();
    }

    private Node findNode(User user){
        for(int i=0; i<nodes.size(); i++){
            if (nodes.get(i).getValue().equals(user)){
                return nodes.get(i);
                //return nodes.get(nodes.indexOf(user));
            }
        }
        return null;
    }

    @Override
    public void addNode(User user){
        Node node = new Node(user);
        if (findNode(user) == null) {
            nodes.add(node);
        }
    }

    @Override
    public void removeNode(User user) throws NodeNotFoundException{
        if (!(findNode(user)==null)){
            for (int i=0; i<nodes.size(); i++){
                nodes.get(i).removeNeighbor(findNode(user));
            }
            nodes.remove(findNode(user));
        } else {
            throw new NodeNotFoundException(user.getId());
        }
    }

    @Override
    public void linkNodes(User to, User add){
        if(!(findNode(to)==null) && !(findNode(add)==null)){
            findNode(to).addNeighbor(findNode(add));
        }
    }

    @Override
    public int getNodeNumber(){
        return nodes.size();
    }
}
