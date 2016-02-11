package sr.api.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sr.api.persistence.domain.Book;

/**
 * @author sercan
 */
@Repository("iBookDao")
public interface IBookDao extends PagingAndSortingRepository<Book, String> {
    Page<Book> findByBookNameLike(Pageable pageable, String name);

    @Query("{bookName : {$regex : ?0, $options: 'i'}}")
    Page<Book> findByBookName(String bookName, Pageable pageable);

    @Query("{bookCategory : {$regex : ?0, $options: 'i'}}")
    Page<Book> findByBookCategory(String bookCategory, Pageable pageable);

    @Query("{bookISBN : {$regex : ?0, $options: 'i'}}")
    Page<Book> findByBookISBN(String bookISBN, Pageable pageable);
}
