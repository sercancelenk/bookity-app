package sr.api.presentation.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import sr.api.business.service.IBookService;
import sr.api.business.service.IUserService;
import sr.api.persistence.domain.Book;
import sr.api.persistence.domain.User;
import sr.api.presentation.vo.BooksVO;

/**
 * Created by sercan on 10/02/16.
 */

@Component
public class StartupControl implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    IBookService bookService;

    @Autowired
    IUserService userService;

    private static Logger logger = Logger.getLogger(StartupControl.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Application listener startup event fired !!");
        this.initializingDatas();
    }

    private void initializingDatas(){
        if(userService.totalUserCount() <= 0){
            User user1 = new User();
            user1.setName("Admin");
            user1.setPass("123");
            user1.setUserName("admin@admin.com");
            user1.setRole(2);
            user1.setActivationCode("AD32CSDDD");
            user1.setStatus(true);
            userService.updateUser(user1);
        }

        BooksVO vo = bookService.getAllBooks(0, 10);
        if(vo.getBookList().size() <= 0){
            Book book = new Book();
            book.setBookAuthor("Ahmet Uygun");
            book.setBookCategory("Hikaye");
            book.setBookISBN("123-22-32");
            book.setBookName("Ikimizin Yerine");
            book.setCost(12);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Seda Akca");
            book.setBookCategory("Roman");
            book.setBookISBN("123-433-2332-23");
            book.setBookName("Hayallerin Bittigi Yer");
            book.setCost(23);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Mustafa Temiz");
            book.setBookCategory("Hikaye");
            book.setBookISBN("434-434-56");
            book.setBookName("Bir Küçük Erguvan");
            book.setCost(14);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Sami Pekaydın");
            book.setBookCategory("Teknik");
            book.setBookISBN("122-4990-232");
            book.setBookName("3D Max Eğitim");
            book.setCost(35);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Murat Aydın");
            book.setBookCategory("Roman");
            book.setBookISBN("433-232-332");
            book.setBookName("Bir Delinin Düşleri");
            book.setCost(18);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Rahman Ustura");
            book.setBookCategory("Teknik");
            book.setBookISBN("12-332-44-3");
            book.setBookName("Javascript Temelleri");
            book.setCost(23);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Rahman Ustura");
            book.setBookCategory("Teknik");
            book.setBookISBN("12-332-44-3");
            book.setBookName("Java SE");
            book.setCost(45);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Rahman Ustura");
            book.setBookCategory("Teknik");
            book.setBookISBN("12-332-44-34324");
            book.setBookName("Unreal Engine");
            book.setCost(63);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Ahmet Bayram");
            book.setBookCategory("Roman");
            book.setBookISBN("12-332-30-88");
            book.setBookName("Deniz Havası");
            book.setCost(13);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Feyyaz Ucar");
            book.setBookCategory("Hikaye");
            book.setBookISBN("12-332-44-344");
            book.setBookName("Futbolun Denklemi");
            book.setCost(23);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Esra Melek");
            book.setBookCategory("Roman");
            book.setBookISBN("12-332-44-332");
            book.setBookName("Sezenler Olmuş");
            book.setCost(23);
            bookService.saveBook(book);

            book = new Book();
            book.setBookAuthor("Yunus Ekin");
            book.setBookCategory("Teknik");
            book.setBookISBN("12-44-44-3");
            book.setBookName("Ruby On Rails");
            book.setCost(23);
            bookService.saveBook(book);



        }

    }
}
