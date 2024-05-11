package client;

import interfaces.Executable;
import java.io.Serializable;
import java.math.BigInteger;

public class JobOne implements Executable, Serializable {
    private static final long serialVersionUID = -1L;
    private int n;


    public JobOne(int n) {
        this.n = n;
    }

    @Override
    public Object execute() {
        BigInteger res = BigInteger.ONE;
        return res;
    }
}
