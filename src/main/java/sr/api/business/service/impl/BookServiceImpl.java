package sr.api.business.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sr.api.Util.Utility;
import sr.api.business.service.IBookService;
import sr.api.persistence.dao.IBookDao;
import sr.api.persistence.domain.Book;
import sr.api.presentation.vo.BooksVO;

/**
 * @author sercan
 */
@Service("iBookService")
@Transactional
public class BookServiceImpl implements IBookService {

    private static Logger logger = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    IBookDao iBookDao;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    @Transactional(readOnly = true)
    public BooksVO getAllBooks(int page, int maxResults) {
        try {
            Page<Book> result = _findAll(page, maxResults);

            if (isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result)) {
                int lastPage = result.getTotalPages() - 1;
                result = _findAll(lastPage, maxResults);
            }
            return new BooksVO(result.getTotalPages(), result.getTotalElements(), result.getContent(), page);
        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return null;
    }

    protected Page<Book> _findAll(int page, int maxResults) {
        try {
            final PageRequest pageRequest = new PageRequest(page, maxResults, new Sort(Sort.Direction.ASC, "bookName"));
            return iBookDao.findAll(pageRequest);
        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return null;
    }

    @Override
    public BooksVO searchByBookName(String bookName, int page, int maxResults) {
        try {
            if (bookName != null && !"".equals(bookName)) {
                PageRequest pageRequest = new PageRequest(page, maxResults, new Sort(Sort.Direction.ASC, "bookName"));

                Page<Book> result = iBookDao.findByBookName(bookName, pageRequest);
                if (isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result) && page > 0) {
                    int lastPage = result.getTotalPages() - 1;
                    pageRequest = new PageRequest(lastPage, maxResults, new Sort(Sort.Direction.ASC, "bookName"));
                    result = iBookDao.findByBookName(bookName, pageRequest);
                }
                return new BooksVO(result.getTotalPages(), result.getTotalElements(), result.getContent(), page);
            }
            return null;
        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return null;
    }

    @Override
    public BooksVO searchByBookCategory(String bookCategory, int page, int maxResults) {
        try {
            if (bookCategory != null && !"".equals(bookCategory)) {
                PageRequest pageRequest = new PageRequest(page, maxResults, new Sort(Sort.Direction.ASC, "bookName"));
                Page<Book> result = iBookDao.findByBookCategory(bookCategory, pageRequest);
                if (isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result) && page > 0) {
                    int lastPage = result.getTotalPages() - 1;
                    pageRequest = new PageRequest(lastPage, maxResults, new Sort(Sort.Direction.ASC, "bookName"));
                    result = iBookDao.findByBookCategory(bookCategory, pageRequest);
                }
                return new BooksVO(result.getTotalPages(), result.getTotalElements(), result.getContent(), page);
            }
            return null;
        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return null;
    }

    @Override
    public BooksVO searchByBookISBN(String bookISBN, int page, int maxResults) {
        try {
            if (bookISBN != null && !"".equals(bookISBN)) {
                PageRequest pageRequest = new PageRequest(page, maxResults, new Sort(Sort.Direction.ASC, "bookName"));
                Page<Book> result = iBookDao.findByBookISBN(bookISBN, pageRequest);
                if (isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result) && page > 0) {
                    int lastPage = result.getTotalPages() - 1;
                    pageRequest = new PageRequest(lastPage, maxResults, new Sort(Sort.Direction.ASC, "bookName"));
                    result = iBookDao.findByBookISBN(bookISBN, pageRequest);
                }
                return new BooksVO(result.getTotalPages(), result.getTotalElements(), result.getContent(), page);
            }
            return null;
        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return null;
    }

    @Override
    public boolean saveBook(Book book) {
        boolean result = false;
        try {
            iBookDao.save(book);
            result = true;
        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return result;
    }

    @Override
    public Book updateBook(Book book) {
        Book updatedBook = null;
        try {
            if (book == null || book.getId() == null || "".equals(book.getId()))
                return null;

            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(book.getId()));

            updatedBook = mongoTemplate.findOne(query, Book.class);

            if (updatedBook == null)
                return null;

            updatedBook.setBookAuthor(book.getBookAuthor());
            updatedBook.setBookName(book.getBookName());
            updatedBook.setCost(book.getCost());
            updatedBook.setBookCategory(book.getBookCategory());
            updatedBook.setBookISBN(book.getBookISBN());
            mongoTemplate.save(updatedBook);

        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return book;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public boolean deleteBook(Book book) {
        boolean result = false;
        try {
            try {
                iBookDao.delete(book);
                result = true;
            } catch (Exception ex) {
                result = false;
            }

        } catch (Exception ex) {
            logger.error(Utility.parseStackTrace(Thread.currentThread().getStackTrace()[1]) + " " + ex.getMessage().toString());
        }
        return result;
    }

    @Override
    public boolean delete(Book book) {
        boolean result = false;

        try {
            iBookDao.delete(book);
            result = true;
        } catch (Exception ex) {
            result = false;

        }
        return result;
    }


    private boolean isUserAfterOrOnLastPage(int page, Page<Book> result) {
        return page >= (result.getTotalPages() - 1);
    }

    private boolean hasDataInDataBase(Page<Book> result) {
        return result.getTotalElements() > 0;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public boolean deleteBook(String id) {
        boolean result = false;
        try {
            iBookDao.delete(id);
            result = true;
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

}
