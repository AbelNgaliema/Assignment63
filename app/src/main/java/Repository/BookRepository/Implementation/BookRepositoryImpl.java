package Repository.BookRepository.Implementation;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import Config.DBConstants;
import Domain.Author;
import Domain.Book;
import Domain.Publisher;
import Repository.BookRepository.BookRepository;

/**
 * Created by AbelN on 20/04/2016.
 */
public class BookRepositoryImpl extends SQLiteOpenHelper implements BookRepository {

    public static final String TABLE_NAME = "book";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_AUHTORNAME = "authorName";
    public static final String COLUMN_AUHTORSURNAME = "authorSurname";
    public static final String COLUMN_PUBLISHERNAME= "publisherName";
    public static final String COLUMN_PUBLISHERCITY = "publisherCity";
    public static final String COLUMN_PUBLISHERREGISTRATIONNUMBER = "publisherRegistrationNumber";
    public static final String COLUMN_ISBNNUMBER = "isbn";

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void close() {
        this.close();
    }

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_YEAR + " INTEGER NOT NULL, "
            + COLUMN_ISBNNUMBER + " TEXT NOT NULL, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_AUHTORNAME + " TEXT NOT NULL , "
            + COLUMN_AUHTORSURNAME + " TEXT NOT NULL , "
            + COLUMN_PUBLISHERCITY + " TEXT NOT NULL , "
            + COLUMN_PUBLISHERNAME+ " TEXT NOT NULL , "
            + COLUMN_PUBLISHERREGISTRATIONNUMBER + " TEXT NOT NULL , "
            + COLUMN_QUANTITY + " INTEGER NOT NULL , "
            + COLUMN_PRICE + " REAL NOT NULL);";




    public BookRepositoryImpl(Context context) {
        super(context,DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public BookRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Book findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_YEAR,
                        COLUMN_TITLE,
                        COLUMN_QUANTITY,
                        COLUMN_ISBNNUMBER,
                        COLUMN_PRICE,
                        COLUMN_AUHTORNAME,
                        COLUMN_AUHTORSURNAME,
                        COLUMN_PUBLISHERNAME,
                        COLUMN_PUBLISHERCITY,
                        COLUMN_PUBLISHERREGISTRATIONNUMBER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {


            final Author author = new Author.Builder().name(cursor.
                    getString(cursor.getColumnIndex(COLUMN_AUHTORNAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_AUHTORSURNAME)))
                    .build();

            final Publisher publisher = new Publisher.Builder().
                    city(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHERCITY)))
                    .registration(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHERREGISTRATIONNUMBER)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHERNAME)))
                    .build();


            final Book book = new Book.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID)))).
                     price(cursor.getLong(cursor.getColumnIndex(COLUMN_PRICE)))
                    .year((int) cursor.getLong(cursor.getColumnIndex(COLUMN_YEAR)))
                    .isbnNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ISBNNUMBER)))
                    .quantity(cursor.getColumnIndex(COLUMN_QUANTITY))
                    .publisher(publisher)
                    .author(author)
                    .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)))
                    .build();

            return book;
        } else {
            return null;
        }

    }

    @Override
    public Book save(Book entity) {
        open();
        ContentValues values = new ContentValues();
       values.put(COLUMN_ID, entity.getId());

        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_TITLE, entity.getTitle());
        values.put(COLUMN_QUANTITY, entity.getQuantity());
        values.put(COLUMN_ISBNNUMBER, entity.getIsbnNumber());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put (COLUMN_AUHTORNAME, entity.getAuthor().getName());
        values.put (COLUMN_AUHTORSURNAME, entity.getAuthor().getSurname());
        values.put (COLUMN_PUBLISHERCITY, entity.getPublisher().getCity());
        values.put (COLUMN_PUBLISHERNAME, entity.getPublisher().getName());
        values.put (COLUMN_PUBLISHERREGISTRATIONNUMBER, entity.getPublisher().getRegistration());



        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Book insertedEntity = new Book.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }



    @Override
    public Book update(Book entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_YEAR, entity.getYear());
        values.put(COLUMN_TITLE, entity.getTitle());
        values.put(COLUMN_QUANTITY, entity.getQuantity());
        values.put(COLUMN_ISBNNUMBER, entity.getIsbnNumber());
        values.put(COLUMN_PRICE, entity.getPrice());
        values.put (COLUMN_AUHTORNAME, entity.getAuthor().getName());
        values.put (COLUMN_AUHTORSURNAME, entity.getAuthor().getSurname());
        values.put (COLUMN_PUBLISHERCITY, entity.getPublisher().getCity());
        values.put (COLUMN_PUBLISHERNAME, entity.getPublisher().getName());
        values.put (COLUMN_PUBLISHERREGISTRATIONNUMBER, entity.getPublisher().getRegistration());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Book delete(Book entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Set<Book> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Book> books = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Author author = new Author.Builder().name(
                        cursor.getString(cursor.getColumnIndex(COLUMN_AUHTORNAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_AUHTORSURNAME)))
                        .build();

                final Publisher publisher = new Publisher.Builder().
                        city(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHERCITY)))
                        .registration(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHERREGISTRATIONNUMBER)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHERNAME)))
                        .build();


                final Book book = new Book.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID)))).
                        price(cursor.getLong(cursor.getColumnIndex(COLUMN_PRICE)))
                        .year((int) cursor.getLong(cursor.getColumnIndex(COLUMN_YEAR)))
                        .isbnNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ISBNNUMBER)))
                        .quantity(cursor.getColumnIndex(COLUMN_QUANTITY))
                        .publisher(publisher)
                        .author(author)
                        .title(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)))
                        .build();
                books.add(book);
            } while (cursor.moveToNext());
        }
        return books;
    }



    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
