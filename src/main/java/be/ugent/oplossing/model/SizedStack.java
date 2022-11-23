package be.ugent.oplossing.model;
import java.util.Stack;


public class SizedStack<T> extends Stack<T> {
    public int maxSize = 0;
    public SizedStack(int maxSize){
        super();
        this.maxSize = maxSize;
    }
    @Override
    public T push(T obj){
        while (this.size() >= maxSize){
            System.out.println("to long removing ell");
            this.remove(0);}
        return super.push(obj);
    }
    @Override
    public T pop(){
        if (this.size() == 0) return null;
        return super.pop();
    }
}
