package designPatterns.iteratorPattern;

/**
 * @author ZhangHongzheng @Description
 * @create 2022-11-14 10:06
 */
public class IteratorMain {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        Iterator iterator = bookRepository.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }
}
