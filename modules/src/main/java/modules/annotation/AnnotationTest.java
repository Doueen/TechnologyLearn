package modules.annotation;

import javax.annotation.Resource;

@Resource
public class AnnotationTest {
    @AnReName(header = "add_",ender = "end")
     void TestAnno2(){

    }
    @AnReName(header = "add_1",ender = "end")
    void TestAnno1(){

    }
    @AnReName(header = "add_3",ender = "end")
    void TestAnno3(){

    }
}
