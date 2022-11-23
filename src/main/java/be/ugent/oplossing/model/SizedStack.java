package be.ugent.oplossing.model;
import java.util.Stack;

//specialised implementation of the stack but with limited size
public class SizedStack<T> extends Stack<T> {
    private int maxSize = 0;
    public SizedStack(int maxSize){
        super();
        this.maxSize = maxSize;
    }
    @Override
    public T push(T obj){
        while (this.size() >= maxSize) this.remove(0);
        return super.push(obj);
    }
    @Override
    public T pop(){
        if (this.size() == 0) return null;
        return super.pop();
    }
}
