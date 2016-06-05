package Service.Implementation.BookService.Implementation;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Set;

import Config.GlobalContext;

import Domain.Book;
import Repository.BookRepository.BookRepository;
import Repository.BookRepository.Implementation.BookRepositoryImpl;
import Service.Implementation.BookService.BookService;

/**
 * Created by AbelN on 08/05/2016.
 */
public class BookServiceImpl extends Service implements BookService {



    //
    BookRepository repo;
    private static BookServiceImpl service = null;

    public BookServiceImpl()
    {
         repo = new BookRepositoryImpl(GlobalContext.getAppContext());
    }
    private final IBinder localBinder = new BookServiceLocalBinder();

    public static BookServiceImpl getInstance() {
        if (service == null)
            service = new BookServiceImpl();
        return service;
    }

    @Override
    public boolean duplicateCheck(String title) {
        Set<Book> books;
        books =   repo.findAll();

        for (Book book : books)
        {
            if (book.getTitle().trim().equalsIgnoreCase(title))
                return true;

        }
        return false;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class BookServiceLocalBinder extends Binder {
        public BookServiceImpl getService() {
            return BookServiceImpl.this;
        }
    }
}
