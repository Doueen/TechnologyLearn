package designPatterns.bridgePattern;

/**
 * @author ZhangHongzheng
 * @Description 拓展抽象化角色  例如JDBCDriverManager
 * @create 2022-11-15 10:21
 */
public class RefinedAbstraction  extends Abstraction{
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }
    public void refinedOperation(){
        super.operation();
        System.out.println("拓展抽象化角色");
        //对 Abstraction 中的 operation 方法进行拓展
    }
}
