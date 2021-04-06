package elte.hu.eltecom.user;

import elte.hu.eltecom.Manager;

public class AdminUser extends User {

    private Manager manager;

    public AdminUser(String userName, Language language, Manager manager){
        super(userName, language);
        this.manager = manager;
    }

    public void kickUser(User user){
        manager.deleteUser(user);
    }

    @Override
    public String toString() {
        return language + " #" + userName;
    }
}
