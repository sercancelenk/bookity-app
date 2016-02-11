package sr.api.business.service;

import sr.api.persistence.domain.Book;
import sr.api.presentation.vo.BooksVO;


/**
 * @author sercan
 *
 */
public interface IBookService {
	BooksVO getAllBooks(int page, int maxResults);
	BooksVO searchByBookName(String bookName, int page, int maxResults);
	BooksVO searchByBookCategory(String bookCategory, int page, int maxResults);
	BooksVO searchByBookISBN(String bookISBN, int page, int maxResults);
	boolean saveBook(Book book);
	Book updateBook(Book book);
	boolean deleteBook(Book book);

	boolean delete(Book book);

	boolean deleteBook(String id);
}
