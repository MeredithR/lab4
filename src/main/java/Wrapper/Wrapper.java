package Wrapper;



import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;


public class Wrapper implements Serializable {
    private ArrayList<Object> data;
    private BigInteger message;
    public Wrapper(ArrayList<Object> data, @Nullable BigInteger message){
        this.data=data;
        this.message = message;
    }

    public ArrayList<Object> getData() {
        return data;
    }

    public BigInteger getMessage() {
        return message;
    }
}
