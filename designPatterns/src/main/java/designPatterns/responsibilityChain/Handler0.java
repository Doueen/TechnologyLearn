package designPatterns.responsibilityChain;

public class Handler0 extends abstractHandler{
    @Override
    public void handler(String msg) {
        System.out.println(toString()+msg);
    }

    @Override
    public boolean preHandle(String msg) {
        return msg!=null;
    }
}
