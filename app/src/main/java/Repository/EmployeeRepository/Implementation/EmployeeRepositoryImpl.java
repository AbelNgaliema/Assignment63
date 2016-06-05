package Repository.EmployeeRepository.Implementation;

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
import Domain.Employee;
import Repository.EmployeeRepository.EmployeeRepository;

/**
 * Created by AbelN on 07/05/2016.
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository {

    public static final String TABLE_NAME = "Employee";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_POSITION ="position";
    public static final String COLUMN_SALARY="salary";
    public static final String COLUMN_SURNAME="surname";
    public static final String COLUMN_SYSTEMNAME="systemKey";

    public static final String COLUMN_PASSWORD="pasword";



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
            + COLUMN_POSITION + " TEXT NOT NULL, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_SALARY + " REAL NOT NULL , "
            + COLUMN_SURNAME + " TEXT NOT NULL , "
            + COLUMN_SYSTEMNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL , "
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT);";




    public EmployeeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public EmployeeRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public Employee findById(Long id) {



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SALARY,
                        COLUMN_PASSWORD,
                        COLUMN_SYSTEMNAME,
                        COLUMN_POSITION,
                        COLUMN_SURNAME,
                        COLUMN_NAME

                        ,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Employee employee = new Employee.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .systemName(cursor.getString(cursor.getColumnIndex(COLUMN_SYSTEMNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .position(cursor.getString(cursor.getColumnIndex(COLUMN_POSITION)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();

            return employee;
        } else {
            return null;
        }

    }

    @Override
    public Employee save(Employee entity) {


        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_POSITION, entity.getPosition());
        values.put(COLUMN_SALARY, entity.getSalary());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_SYSTEMNAME, entity.getSystemName());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Employee insertedEntity = new Employee.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }



    @Override
    public Employee update(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_POSITION, entity.getPosition());
        values.put(COLUMN_SALARY, entity.getSalary());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_SYSTEMNAME, entity.getSystemName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Employee delete(Employee entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Employee> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Employee> customers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Employee customer = new Employee.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .systemName(cursor.getString(cursor.getColumnIndex(COLUMN_SYSTEMNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .position(cursor.getString(cursor.getColumnIndex(COLUMN_POSITION)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
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



