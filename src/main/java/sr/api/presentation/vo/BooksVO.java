package sr.api.presentation.vo;

import java.util.List;

import sr.api.persistence.domain.Book;

/**
 * @author sercan
 *
 */
public class BooksVO {
	private int pagesCount;
	private long totalBooks;
	private String actionMessage;
	private List<Book> bookList;
	private int currentPage;

	public BooksVO() {
	}

	public BooksVO(int pagesCount, long totalBooks, List<Book> bookList, int currentPage) {
		super();
		this.pagesCount = pagesCount;
		this.totalBooks = totalBooks;
		this.bookList = bookList;
		this.currentPage = currentPage;
	}

	public int getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(int pagesCount) {
		this.pagesCount = pagesCount;
	}

	public long getTotalBooks() {
		return totalBooks;
	}

	public void setTotalBooks(long totalBooks) {
		this.totalBooks = totalBooks;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
