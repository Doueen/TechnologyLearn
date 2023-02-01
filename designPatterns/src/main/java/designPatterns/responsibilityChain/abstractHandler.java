package designPatterns.responsibilityChain;

public abstract class abstractHandler {
    /**
     * 责任链下一个处理者
     */
    protected abstractHandler nextHandler;

    /**
     * 设置下一个处理者
     * @param handler
     */
    public void setNextHandler(abstractHandler handler){
       this.nextHandler=handler;
    }

    /**
     * 进行处理
     * @param msg 要处理的信息
     */
    public void doHandle(String msg){
        // 这里根据处理的信息，符合条件则进行处理
        if (preHandle(msg)){
            this.handler(msg);
        }
        if(this.nextHandler!=null){
            nextHandler.doHandle(msg);
        }
        else {
            System.out.println("nextHandler 为空 ；处理链结束");
        }
    }

    /**
     * 进行处理
     * @param msg 待处理的信息
     */
    public abstract void handler(String msg);

    /**
     * 处理条件
     * @return 是否可以处理
     */
    public abstract boolean preHandle(String msg);


}
