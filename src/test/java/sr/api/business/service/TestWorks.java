package sr.api.business.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import sr.api.Util.enums.RoleEnums;
import sr.api.business.service.impl.UserService;
import sr.api.persistence.dao.IUserDao;
import sr.api.persistence.domain.Book;
import sr.api.persistence.domain.User;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by sercan on 10/02/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@WebAppConfiguration
public class TestWorks {

    private static Logger logger = Logger.getLogger(TestWorks.class);

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private UserService userService;

    @Autowired
    private IBookService iBookService;


    @Mock
    User dummyUser;
    Book dummyBook;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userDAOTest() {
        assertNotNull(iUserDao);
        logger.info("** userDAO loaded.");
        dummyUser = new User();
        dummyUser.setName("Dummy");
        dummyUser.setPass("123");
        dummyUser.setRole(RoleEnums.USER.getValue());
        dummyUser.setStatus(true);
        dummyUser.setUserName("dummy@dummy.com");
        iUserDao.save(dummyUser);
        assertNotNull(dummyUser);
        logger.info("** " + dummyUser.getName() + " user created");
        assertNotNull(iUserDao.findByUserName("dummy@dummy.com"));
        logger.info("** Dummy user finding on db by username");
        dummyUser = iUserDao.findOne(dummyUser.getId());
        assertNotNull(dummyUser);
        logger.info("** Dummy user finding on db on id");

        dummyUser = iUserDao.findByUserName(dummyUser.getUserName());
        assertNotNull("Dummy User Null", dummyUser);
        iUserDao.delete(dummyUser);
        logger.info("** Dummy user deleted.");

        logger.info("** User DAO Test finished Successfully");
    }

    @Test
    public void bookServiceTest() {
        assertNotNull(iBookService);
        logger.info("** bookService loaded successfully");
        Book book = new Book();
        book.setBookAuthor("Blah Blah");
        book.setBookCategory("Roman");
        book.setBookISBN("123-21-21");
        book.setBookName("Book Bane");
        book.setCost(12);
        assertTrue(iBookService.saveBook(book));
        logger.info("** " + book.getBookName() + " book created.");

        assertNotNull(iBookService.searchByBookCategory("Hikaye", 0, 10));
        logger.info("** Book search successfully");

        iBookService.delete(book);
        logger.info("** Book deleted successfully");
        logger.info("** Book Service Test finished successfully");
    }


}
