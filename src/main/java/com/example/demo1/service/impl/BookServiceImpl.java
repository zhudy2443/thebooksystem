package com.example.demo1.service.impl;


import com.example.demo1.dao.BookDao;
import com.example.demo1.dao.Impl.BookDaoImpl;
import com.example.demo1.pojo.Book;
import com.example.demo1.pojo.Page;
import com.example.demo1.service.BookService;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        /* 数据边界的有效检查 */
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        // 设置当前页码
        page.setpageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getpageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;

        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }

        // 设置总页码
        page.setPageTotal(pageTotal);

        page.setpageNo(pageNo);
        // 求当前页数据的开始索引
        int begin = (page.getpageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
