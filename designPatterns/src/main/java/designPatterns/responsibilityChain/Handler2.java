package designPatterns.responsibilityChain;

public class Handler2 extends abstractHandler{
    @Override
    public void handler(String msg) {
        System.out.println(toString()+msg);
    }

    @Override
    public boolean preHandle(String msg) {
        return msg!=null;
    }
}