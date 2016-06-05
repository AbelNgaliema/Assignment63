package Repository.BuyRepository.Implementation;

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
import Domain.Buy;
import Repository.BuyRepository.BuyRepository;

/**
 * Created by AbelN on 24/04/2016.
 */
public class BuyRepositoryImpl extends SQLiteOpenHelper implements BuyRepository {


    private long id;

    public static final String TABLE_NAME = "buy";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MODE = "mode";
    public static final String COLUMN_CASHIER= "cashier";
    public static final String COLUMN_BOOK= "book";
    public static final String COLUMN_CUSTOMER= "customer";


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void close() {
        this.close();
    }

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MODE + " TEXT NOT NULL, "
            +COLUMN_BOOK +" TEXT NOT NULL,"
            +COLUMN_CUSTOMER +" TEXT NOT NULL,"
            +COLUMN_CASHIER + " TEXT NOT NULL );";




    public BuyRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public BuyRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Buy findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_MODE,
                        COLUMN_CUSTOMER,
                        COLUMN_BOOK,
                        COLUMN_CASHIER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {


            final Buy buy = new Buy.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID)))).
                     mode(cursor.getString(cursor.getColumnIndex(COLUMN_MODE)))
                    .book(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK)))
                    .customer(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER)))
                    .cashier(cursor.getString(cursor.getColumnIndex(COLUMN_CASHIER)))
                    .build();

            return buy;
        } else {
            return null;
        }

    }

    @Override
    public Buy save(Buy entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MODE, entity.getMode());
        values.put(COLUMN_CASHIER, entity.getCashier());
        values.put(COLUMN_BOOK, entity.getBook());
        values.put(COLUMN_CUSTOMER, entity.getCustomer());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Buy insertedEntity = new Buy.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }



    @Override
    public Buy update(Buy entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MODE, entity.getMode());
        values.put(COLUMN_CASHIER, entity.getCashier());
        values.put(COLUMN_BOOK, entity.getBook());
        values.put(COLUMN_CUSTOMER, entity.getCustomer());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Buy delete(Buy entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Set<Buy> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Buy> buys = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Buy buy = new Buy.Builder().
                        id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID)))).
                         mode(cursor.getString(cursor.getColumnIndex(COLUMN_MODE)))
                        .book(cursor.getString(cursor.getColumnIndex(COLUMN_BOOK)))
                        .customer(cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER)))
                        .cashier(cursor.getString(cursor.getColumnIndex(COLUMN_CASHIER)))
                        .build();
                buys.add(buy);
            } while (cursor.moveToNext());
        }
        return buys;
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

    }}
