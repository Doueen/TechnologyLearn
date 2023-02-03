##设计模式实现

|模块名|简介|
|:---|:---:|
|eventCenter|事件中心:观察者模式|
|responsibilityChain|责任链模式|
|proxy|反射动态代理：代理模式|
|iteratorPattern|迭代器模式|
|BridgePattern|桥接模式|
|
###这里建议说清楚每一种设计模式用途和特点，否则以后都看不懂了
- **proxy 动态代理测试** 代理模式
    - JDK动态代理,代理类与目标实现相同接口,使用API`Proxy.newProxyInstance()`;
- **eventCenter** 观察者模式
  - java观察者模式实现
- **iteratorPattern** 迭代器模式
  - 提供一种方法顺序访问聚合对象中的各个元素，无需暴露对象内部表示
- **BridgePattern** 桥接模式
  - 实现和抽象开，减少继承和多重继承，例如JDBC
  - 桥接模式中的实现化角色 (Implementor) 对应上图的 Driver 接口，具体实现化 (Concrete Implementor) 角色对应 Mysql Driver、Oracle Driver 和 Mariadb Driver，扩展抽象化 (Refined Abstraction) 角色对应 Driver Manager，不具有抽象化 (Abstraction) 角色作为扩展抽象化角色的父类
- **responsibilityChain** 责任链模式
  - 将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。将这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止。
  - 例如Java.utils.ServiceClassloader