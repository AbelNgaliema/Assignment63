package Repository.SupplierRepository.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import Config.DBConstants;
import Domain.Supplier;
import Repository.SupplierRepository.SupplierRepository;

/**
 * Created by AbelN on 07/05/2016.
 */
public class SupplierRepositoryImpl  extends SQLiteOpenHelper implements SupplierRepository {

    public static final String TABLE_NAME = "Supplier";
    private SQLiteDatabase db;




    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS ="address";
    public static final String COLUMN_TELEPHONENUMBER="telephoneNumber";
    public static final String COLUMN_REGISTRATIONNUMBER="registrationNumber";



   /* public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ISBNNUMBER= "isbnNumber";
    public static final String COLUMN_PRICE = "price";*/

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void close() {
        this.close();
    }


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_REGISTRATIONNUMBER + " TEXT NOT NULL , "
            + COLUMN_TELEPHONENUMBER + " REAL NOT NULL , "
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT);";




    public SupplierRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public SupplierRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public Supplier findById(Long id) {



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                       COLUMN_ID,
                        COLUMN_ADDRESS,
                        COLUMN_TELEPHONENUMBER,
                        COLUMN_REGISTRATIONNUMBER,
                        COLUMN_NAME

                        ,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Supplier customer = new Supplier.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID))))
                    .registrationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATIONNUMBER)))
                    .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                    .telephoneNumber(cursor.getColumnIndex(COLUMN_TELEPHONENUMBER))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();

            return customer;
        } else {
            return null;
        }

    }

    @Override
    public Supplier save(Supplier entity) {


        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_ADDRESS, entity.getAddress());
        values.put(COLUMN_REGISTRATIONNUMBER, entity.getRegistrationNumber());
        values.put(COLUMN_TELEPHONENUMBER, entity.getTelephoneNumber());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Supplier insertedEntity = new Supplier.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }



    @Override
    public Supplier update(Supplier entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_ADDRESS, entity.getAddress());
        values.put(COLUMN_TELEPHONENUMBER, entity.getTelephoneNumber());
        values.put(COLUMN_REGISTRATIONNUMBER, entity.getRegistrationNumber());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Supplier delete(Supplier entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Supplier> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Supplier> customers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Supplier customer = new Supplier.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .telephoneNumber(cursor.getColumnIndex(COLUMN_TELEPHONENUMBER))
                        .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                        .registrationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATIONNUMBER)))
                        .build();
                customers.add(customer);
            } while (cursor.moveToNext());
        }
        return customers;
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


