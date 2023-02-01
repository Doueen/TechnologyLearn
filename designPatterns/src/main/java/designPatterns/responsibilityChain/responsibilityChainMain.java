package designPatterns.responsibilityChain;

/**
 * 责任链模式测试；
 */
public class responsibilityChainMain {




    public static void main(String[] args) {
      Handler0 handler0=new Handler0();
      Handler1 handler1=new Handler1();
      Handler2 handler2=new Handler2();
      handler0.setNextHandler(handler1);
      handler1.setNextHandler(handler2);
      handler0.doHandle("--这里是责任链模式测试");


    }



}
