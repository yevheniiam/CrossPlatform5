package udpWork.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ActiveUser implements Serializable, Iterable<User>{
    private final ArrayList<User> users;

    public ActiveUser() {
        this.users = new ArrayList<>();
    }

    public void add(User user){
        users.add(user);
    }

    public boolean contains(User user){
        return users.contains(user);
    }

    public int size(){
        return users.size();
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        users.forEach(u -> stringBuilder.append(u.toString()).append(System.lineSeparator()));
        return stringBuilder.toString();
    }

    @Override
    public Iterator<User> iterator() {
        return users.iterator();
    }
}