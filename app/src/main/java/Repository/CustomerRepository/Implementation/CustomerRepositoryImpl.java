package Repository.CustomerRepository.Implementation;

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
import Domain.Customer;
import Domain.CustomerAddress;
import Domain.PersonalInformation;
import Repository.CustomerRepository.CustomerRepository;

/**
 * Created by AbelN on 23/04/2016.
 */
public class CustomerRepositoryImpl extends SQLiteOpenHelper implements CustomerRepository {

    public static final String TABLE_NAME = "customer";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_POSTALCODE = "postalCode";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_IDNUMBER = "id_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CELLPHONENUMBER= "cellphone";
    public static final String COLUMN_TELEPHONENUMBER= "telephone";







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
            + COLUMN_CITY + " TEXT NOT NULL, "
            + COLUMN_POSTALCODE + " TEXT NOT NULL , "
            + COLUMN_ADDRESS + " TEXT NOT NULL , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_SURNAME + " TEXT NOT NULL , "
            + COLUMN_IDNUMBER + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NULL , "
            + COLUMN_CELLPHONENUMBER + " TEXT NULL , "
            + COLUMN_TELEPHONENUMBER + " TEXT NULL , "
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT);";




    public CustomerRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public CustomerRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public Customer findById(Long id) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CITY,
                        COLUMN_POSTALCODE,
                        COLUMN_ADDRESS,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_IDNUMBER,
                        COLUMN_EMAIL,
                        COLUMN_CELLPHONENUMBER,
                        COLUMN_TELEPHONENUMBER
                        ,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final CustomerAddress customerAddress = new CustomerAddress.Builder()
                    .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .postalCode((int)cursor.getColumnIndex(COLUMN_POSTALCODE))
                    .build();

            final PersonalInformation personalInformation = new PersonalInformation.Builder().idNumber(cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER)))
                    .cellphone((int) cursor.getColumnIndex(COLUMN_CELLPHONENUMBER))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .telephone((int) cursor.getColumnIndex(COLUMN_TELEPHONENUMBER))
                    .build();
            final Customer customer = new Customer.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID))))
                    .CustomerAddress(customerAddress)
                    .PersonalInformation(personalInformation)
                    .build();

            return customer;
        } else {
            return null;
        }

    }

    @Override
    public Customer save(Customer entity) {
        open();
        ContentValues values = new ContentValues();
      //  values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ADDRESS, entity.getCustomerAddress().getAddress());
        values.put(COLUMN_NAME, entity.getPersonalInformation().getName());
        values.put(COLUMN_SURNAME, entity.getPersonalInformation().getSutname());
        values.put(COLUMN_IDNUMBER, entity.getPersonalInformation().getIdNumber());
        values.put(COLUMN_CELLPHONENUMBER, entity.getPersonalInformation().getCellnumber());
        values.put(COLUMN_TELEPHONENUMBER, entity.getPersonalInformation().getTelephone());
        values.put(COLUMN_EMAIL, entity.getPersonalInformation().getEmailAddress());
        values.put(COLUMN_CITY, entity.getCustomerAddress().getCity());
        values.put(COLUMN_POSTALCODE, entity.getCustomerAddress().getPostalCode());


        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Customer insertedEntity = new Customer.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }



    @Override
    public Customer update(Customer entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ADDRESS, entity.getCustomerAddress().getAddress());
        values.put(COLUMN_NAME, entity.getPersonalInformation().getName());
        values.put(COLUMN_SURNAME, entity.getPersonalInformation().getSutname());
        values.put(COLUMN_IDNUMBER, entity.getPersonalInformation().getIdNumber());
        values.put(COLUMN_CELLPHONENUMBER, entity.getPersonalInformation().getCellnumber());
        values.put(COLUMN_TELEPHONENUMBER, entity.getPersonalInformation().getTelephone());
        values.put(COLUMN_EMAIL, entity.getPersonalInformation().getEmailAddress());
        values.put(COLUMN_CITY, entity.getCustomerAddress().getCity());
        values.put(COLUMN_POSTALCODE, entity.getCustomerAddress().getPostalCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Customer delete(Customer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Customer> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customer> customers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final CustomerAddress customerAddress = new CustomerAddress.Builder()
                        .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .postalCode((int)cursor.getColumnIndex(COLUMN_POSTALCODE))
                        .build();

                final PersonalInformation personalInformation = new PersonalInformation.Builder().idNumber(cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER)))
                        .cellphone((int) cursor.getColumnIndex(COLUMN_CELLPHONENUMBER))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .telephone((int) cursor.getColumnIndex(COLUMN_TELEPHONENUMBER))
                        .build();
                final Customer customer = new Customer.Builder().id(cursor.getLong((cursor.getColumnIndex(COLUMN_ID))))
                        .CustomerAddress(customerAddress)
                        .PersonalInformation(personalInformation)
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

