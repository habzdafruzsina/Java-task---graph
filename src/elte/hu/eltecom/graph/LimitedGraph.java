package elte.hu.eltecom.graph;

import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.graph.exceptions.UnsupportedGraphOperation;
import elte.hu.eltecom.user.User;


public class LimitedGraph implements PrintableGraph{

    private final User[] nodes;
    private final boolean[][] edges;

    public LimitedGraph(int maxNodeQuantity){
        nodes = new User[maxNodeQuantity];
        edges = new boolean[maxNodeQuantity][maxNodeQuantity];
        for (int i=0; i<maxNodeQuantity; i++){
            for (int j=0; j<maxNodeQuantity; j++) {
                edges[i][j] = false;
            }
        }
    }

    private Integer findNode(User user){
        for (int i=0; i<nodes.length; i++){
            if (user.equals(nodes[i])){
                return i;
            }
        }
        return null;
    }

    @Override
    public void addNode(User user){
        try {
            if (findNode(user)==null){
                for (int i=0; i<=nodes.length; i++){
                    if (nodes[i] == null){
                        nodes[i] = user;
                        return;
                    }
                }
            }

        } catch (ArrayIndexOutOfBoundsException e){
            throw new UnsupportedGraphOperation("A nodes nevu tomb megtelt. Nem lehet tobb elemet beletenni.");
        }
    }

    @Override
    public void removeNode(User user) throws NodeNotFoundException{
        if(!(findNode(user)==null)){
            for(int i=0; i<edges.length; i++){
                edges[findNode(user)][i]=false;
                edges[i][findNode(user)]=false;
            }
            nodes[findNode(user)] = null;
        } else {
            throw new NodeNotFoundException(user.getId());
        }
    }

    @Override
    public void linkNodes(User to, User add){
        if(!(findNode(to)==null) && !(findNode(add)==null)){
            edges[findNode(to)][findNode(add)]=true;
        }
    }

    @Override
    public int getNodeNumber(){
        int nodeNumber = 0;
        for(int i=0; i<nodes.length; i++){
            if (!(nodes[i]==null)){
                nodeNumber++;
            }
        }
        return nodeNumber;
    }

    @Override
    public String print(){
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<nodes.length; i++){
            if(!(nodes[i]==null)) {
                sb.append(nodes[i].toString());
                sb.append(System.lineSeparator());
            }
        }


        for (int i=0; i<nodes.length; i++){
            for (int j=0; j<nodes.length; j++){
                if (!(nodes[i]==null) && !(nodes[j]==null)) {
                    if (edges[i][j] == true) {
                        sb.append("true ");
                    } else {
                        sb.append("false ");
                    }
                }
            }
            if (!(nodes[i] == null)) sb.append(System.lineSeparator());
        }

        String graphRepresentation = sb.toString();
        return graphRepresentation;
    }
}
