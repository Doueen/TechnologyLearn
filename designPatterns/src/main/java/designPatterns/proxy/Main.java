package designPatterns.proxy;

public class Main {
    public static void main(String[] args) {
        // 构造函数传入被代理对象
        ProxyFactory proxyFactory = new ProxyFactory(new TestNum());
        // JDK生成代理对象
        iTestAdd testNum = (iTestAdd) proxyFactory.getProxy(new ProxyFactory.PreHandler() {
            @Override
            //所有方法进行前置通知
            public void perHandlerMethod(Object[] args) {
                System.out.println(args[0].toString());
            }
        });
        testNum.addNum(111, 23);

    }
}


interface iTestAdd {
    int addNum(int a, int b);
}

class TestNum implements iTestAdd {

    public int addNum(int a, int b) {
        return a + b;
    }
}
