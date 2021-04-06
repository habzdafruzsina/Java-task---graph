package elte.hu.eltecom.user;

public class User {

    private static int userCounter = 0;
    private final int id;
    protected String userName;
    protected Language language;

    public User(String userName, Language language){
        id = User.userCounter;
        User.userCounter++;
        this.userName = userName;
        this.language = language;
    }

    public int getId(){return id;}

    public String getUserName() {return userName;}

    public void setUserName(String name) {userName=name;}

    @Override
    public boolean equals(Object o){
        if (o instanceof User) {
            User user = (User) o;

            if (o == null){
                return false;
            }
            if (this.id == user.id){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return language + " " + userName;
    }
}
