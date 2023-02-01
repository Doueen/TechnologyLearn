package modules.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangHongzheng
 * @Description
 * @create 2022-11-04 17:57
 */
public class StreamMainObject {

    public static void main(String[] args) {
        List<Books> list = new ArrayList<>();
        list.add(new Books("HLM",5,"hhhh") );
        list.add(new Books("XYJ",9,"hhhh") );
        list.add(new Books("SGYY",5,"hhhh") );
        list.add(new Books("SHZ",56,"hhhh") );
        list.add(new Books("GCXXJ",89,"ftdh") );
        list.add(new Books("LZZY",65,"hhhh") );

     List<Books> list1 =
                list.stream()
                        // 去重
                        .distinct()
                        // 筛选
                        .filter(name -> name.num > 10)
                        // 映射 操作元素进行覆盖
                        .map(name -> new Books(name.name, name.num, name.name))
                        // 排序
                        .sorted((n1, n2) -> n1.num - n2.num)
                        // 跳过一个
                        .skip(1)
                        // 设置最大长度
                        .limit(4)
                        // 终结：遍历
                        // .forEach(System.out::println);
                        // 终结：计数
                        //  .count();
                        // 终结：最大
                        //  .max((n1, n2) -> n1.length() - n2.length());
                        // 终结：转化为集合
                        .collect(Collectors.toList());
        list1.stream().forEach(
                book->book.num=5
        );
        System.out.println(list1);
        System.out.println(list);
    }
}
class Books{
    String name;
    int num;
    String describe;

    public Books(String name, Integer num, String describe) {
        this.name = name;
        this.num = num;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", describe='" + describe + '\'' +
                '}';
    }


}
