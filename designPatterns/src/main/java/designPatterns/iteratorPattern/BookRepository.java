package designPatterns.iteratorPattern;

/**
 * @Description 图书仓库容器
 *
 * @author ZhangHongzheng
 * @create 2022-11-14 9:52
 */
public class BookRepository implements Container {
    public Book[] books = new Book[5];

    {
        Book book0 = new Book(1);
        Book book2 = new Book(40);
        Book book3 = new Book(5);
        Book book4 = new Book(1);
        Book book1 = new Book(6);
        books[0] = book0;
        books[1] = book1;
        books[2] = book2;
        books[3] = book3;
        books[4] = book4;
    }

    @Override
    public Iterator getIterator() {
        return new BookIterator();
    }

    /**
     * 普通局部类，依赖于外围类的实例对象，为外围对象提供服务，
     */
    private class BookIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < books.length) {
                return true;
            }

            return false;
        }

        /**
         * 调用next之后会让索引加一，持有外围类实例对象引用
         * @return
         */
        @Override
        public Object next() {
            if (hasNext()) {
                return books[index++];
            }
            return null;
        }
    }

    /**
     * 静态内部类，只为Books提供服务，与正常类相似，不依赖于BookRepository实现
     */
    private static class Book {
        String name;
        Integer count;

        public Book(Integer count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
