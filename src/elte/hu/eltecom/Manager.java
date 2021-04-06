package elte.hu.eltecom;

import elte.hu.eltecom.graph.Graph;
import elte.hu.eltecom.graph.exceptions.NodeNotFoundException;
import elte.hu.eltecom.user.AdminUser;
import elte.hu.eltecom.user.Language;
import elte.hu.eltecom.user.User;

import java.util.HashMap;

public class Manager {

    final Graph graph;
    HashMap<Integer, User> users;

    public Manager(Graph graph){
        this.graph = graph;
        users = new HashMap();
    }

    public Graph getGraph(){return graph;}

    public User createUser(User user){
        users.put(user.getId() ,user);
        graph.addNode(user);
        return user;
    }

    public User createUser(String name, Language language){
        User user = new User(name, language);
        users.put(user.getId() ,user);
        graph.addNode(user);
        return user;
    }

    public User createAdmin(String name, Language language){
        AdminUser user = new AdminUser(name, language, this);
        return createUser(user);
    }

    public User getUserById(int id){
        return users.get(id);
    }

    public void linkUsers(User user1, User user2){
        graph.linkNodes(user1, user2);
    }

    public void deleteUser(User user){
        try {
            graph.removeNode(user);
            users.remove(user.getId());
        } catch (NodeNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}
