package udpWork.Data;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Objects;

public class User implements Serializable {
    private final InetAddress address;
    private final int port;

    public User(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public String toString() {
        return "address=" + address +
                ", port=" + port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return port == user.port && address.equals(user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port);
    }
}