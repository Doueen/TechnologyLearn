package designPatterns.bridgePattern;

/**
 * @author ZhangHongzheng
 * @Description main测试主类
 * @create 2022-11-15 10:29
 */
public class BridgePatternMain {
    public static void main(String[] args) {

        RefinedAbstraction refinedAbstraction=new RefinedAbstraction(new ConcreteImplementorA());
        refinedAbstraction.refinedOperation();
    }
}
