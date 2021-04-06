package elte.hu.eltecom.graph;

import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.user.User;

public interface Graph{

    void addNode(User user);
    void removeNode(User user) throws NodeNotFoundException;
    void linkNodes(User to, User add);
    int getNodeNumber();
}