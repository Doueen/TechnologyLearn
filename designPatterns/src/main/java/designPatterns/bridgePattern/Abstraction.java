package designPatterns.bridgePattern;

/**
 * @author ZhangHongzheng
 * @Description 抽象化角色
 * @create 2022-11-15 10:19
 */
public abstract class Abstraction {
    private Implementor implementor;

    public Abstraction(Implementor implementor){
        this.implementor=implementor;
    }

    public void operation(){
        implementor.operationImpl();
    }
}
