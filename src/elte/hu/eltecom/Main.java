package elte.hu.eltecom;

import elte.hu.eltecom.file.FileManager;
import elte.hu.eltecom.user.AdminUser;
import elte.hu.eltecom.user.Language;
import elte.hu.eltecom.user.User;

public class Main {


    public static void main(String args[]){
        if (args.length>0){
            Manager manager = FileManager.readGraph(args[0]);
            User user = manager.getUserById(3);
            if (user instanceof AdminUser){
                AdminUser admin = (AdminUser) user;
                admin.kickUser(manager.getUserById(1));
                admin.kickUser(manager.getUserById(2));
            }
            User user2 = manager.createUser("Grabowski", Language.HUN);
            manager.linkUsers(user2, user);
            FileManager.writeGraph(manager, args[0]);
        }
    }
}
