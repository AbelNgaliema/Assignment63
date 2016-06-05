package Repository.BranchRepository.Implementation;

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
import Domain.Branch;
import Repository.BranchRepository.BranchRepository;

/**
 * Created by AbelN on 07/05/2016.
 */
public class BranchRepositoryImpl  extends SQLiteOpenHelper implements BranchRepository {

    public static final String TABLE_NAME = "Branch";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_MANAGER = "manager";
    public static final String COLUMN_TELEPHONENUMBER = "telephoneNumber";


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void close() {
        this.close();
    }

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("

            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, "

            + COLUMN_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_MANAGER + " TEXT NOT NULL, "
            + COLUMN_TELEPHONENUMBER + " REAL NOT NULL);";




    public BranchRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public BranchRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    public Branch findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ADDRESS,
                        COLUMN_MANAGER,
                        COLUMN_TELEPHONENUMBER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {



            final Branch branch = new Branch.Builder().id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                    .manager(cursor.getString(cursor.getColumnIndex(COLUMN_MANAGER)))
                    .telephoneNumber((int)cursor.getColumnIndex(COLUMN_TELEPHONENUMBER))
                    .build();

            return branch;
        } else {
            return null;
        }

    }

    @Override
    public Branch save(Branch entity) {


        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ADDRESS, entity.getAddress());
        values.put(COLUMN_MANAGER, entity.getManager());
        values.put(COLUMN_TELEPHONENUMBER, entity.getTelephoneNumber());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Branch insertedEntity = new Branch.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }



    @Override
    public Branch update(Branch entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ADDRESS, entity.getAddress());
        values.put(COLUMN_MANAGER, entity.getManager());
        values.put(COLUMN_TELEPHONENUMBER, entity.getTelephoneNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Branch delete(Branch entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public Set<Branch> findAll() {


        SQLiteDatabase db = this.getReadableDatabase();
        Set<Branch> Branchs = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Branch Branch = new Branch.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                        .manager(cursor.getString(cursor.getColumnIndex(COLUMN_MANAGER)))
                        .telephoneNumber(cursor.getColumnIndex(COLUMN_TELEPHONENUMBER))
                        .build();
                Branchs.add(Branch);
            } while (cursor.moveToNext());
        }
        return Branchs;
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

