package designPatterns.bridgePattern;

/**
 * @author ZhangHongzheng
 * @Description 具体实现化角色  例如JDBC MysqlDriver、OracleDriver、MariadbDriver
 * @create 2022-11-15 10:19
 */
public class ConcreteImplementorB implements Implementor{
    @Override
    public void operationImpl() {
        System.out.println("具体实现化角色"+this.getClass().getSimpleName());

    }
}
