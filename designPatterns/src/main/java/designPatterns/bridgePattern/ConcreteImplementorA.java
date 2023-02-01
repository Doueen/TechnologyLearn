package designPatterns.bridgePattern;

/**
 * @author ZhangHongzheng
 * @Description 具体实现化角色  例如JDBC MysqlDriver、OracleDriver、MariadbDriver
 * @create 2022-11-15 10:17
 */
public class ConcreteImplementorA implements Implementor{
    @Override
    public void operationImpl() {
        System.out.println("具体实现化角色"+this.getClass().getSimpleName());
        // 这里是具体实现
    }

}
