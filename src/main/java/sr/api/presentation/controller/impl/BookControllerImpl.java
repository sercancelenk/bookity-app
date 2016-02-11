package sr.api.presentation.controller.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import sr.api.business.service.IBookService;
import sr.api.persistence.domain.Book;
import sr.api.presentation.aspect.PrepareResponse;
import sr.api.presentation.controller.IBookController;
import sr.api.presentation.vo.BooksVO;
import sr.api.presentation.vo.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
public class BookControllerImpl implements IBookController {

    @Autowired
    IBookService iBookService;
    @Value("10")
    protected int maxResults;

    @Override
    public ModelAndView books() {
        return new ModelAndView("books");
    }

    @Override
    public ResponseEntity<?> listAll(int page, Locale locale) {
        BooksVO booksVO = iBookService.getAllBooks(page, maxResults);
        return new ResponseEntity<BooksVO>(booksVO, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> create(@ModelAttribute("book") Book book) {

        iBookService.saveBook(book);
        BooksVO booksVO = iBookService.getAllBooks(0, maxResults);
        return new ResponseEntity<BooksVO>(booksVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(@PathVariable("id") String bookId,
                                    @RequestBody Book book) {
        if (bookId == null || "".equals(bookId)) {
            return new ResponseEntity<String>("Book id can not be null !!!", HttpStatus.OK);
        }
        if (book == null || book.getId() == null || "".equals(bookId)) {
            return new ResponseEntity<String>("Book info can not be null !!!", HttpStatus.OK);
        }
        if (!String.valueOf(book.getId()).equals(bookId)) {
            return new ResponseEntity<String>("Bad Request !!!", HttpStatus.BAD_REQUEST);
        }
        iBookService.updateBook(book);
        BooksVO booksVO = iBookService.getAllBooks(0, maxResults);
        return new ResponseEntity<BooksVO>(booksVO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") String bookId) {
        try {
            if (bookId == null || "".equals(bookId))
                return new ResponseEntity<String>("Book id can not be null !!!", HttpStatus.OK);

            iBookService.deleteBook(String.valueOf(bookId));
        } catch (org.springframework.security.access.AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }
        BooksVO booksVO = iBookService.getAllBooks(0, maxResults);
        return new ResponseEntity<BooksVO>(booksVO, HttpStatus.OK);
    }

    @Override
    public Response searchAnything(@RequestBody String searchData, HttpServletRequest request, Locale locale) {

        Response response = new Response();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject searchObject = gson.fromJson(searchData, JsonObject.class);
        String searchCategory = searchObject.get("cat").getAsJsonObject().get("name").getAsString();
        String searchTerm = searchObject.get("term").getAsString();
        Integer page = searchObject.get("page").getAsInt();

        if ("Name".equals(searchCategory)) {
            response.setData(iBookService.searchByBookName(searchTerm, page, maxResults));
            response.setValid(true);
        } else if ("Category".equals(searchCategory)) {
            response.setData(iBookService.searchByBookCategory(searchTerm, page, maxResults));
            response.setValid(true);
        } else if ("ISBN".equals(searchCategory)) {
            response.setData(iBookService.searchByBookISBN(searchTerm, page, maxResults));
            response.setValid(true);
        }

        return response;


    }

}
