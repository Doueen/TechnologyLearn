package modules.JUC;


import java.io.IOException;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-04 10:30
 */
public class Main {
    public static void main(String[] args) throws IOException {
        setDefaultUncaughtExceptionHandler();

    }

    public static void setDefaultUncaughtExceptionHandler(){
        // 为所有线程添加未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler(
                (t, e) -> {
                    System.err.println("发生异常"+e);
                });

    }
}
