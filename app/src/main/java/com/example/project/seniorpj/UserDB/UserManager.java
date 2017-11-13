package com.example.project.seniorpj.UserDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Smew on 22/9/2560.
 */

public class UserManager extends SQLiteOpenHelper implements UserManagerHelper {

    public static final String TAG = UserManager.class.getSimpleName();
    private SQLiteDatabase mDatabase;

    public UserManager(Context context) {
        super(context, UserManagerHelper.DATABASE_NAME, null, UserManagerHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USER = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                User.TABLE_NAME,
                User.Column.ID,
                User.Column.USERNAME,
                User.Column.PASSWORD,
                User.Column.EMAIL,
                User.Column.AGE,
                User.Column.GENDER
        );

        db.execSQL(CREATE_TABLE_USER);
        Log.i(TAG, CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER = "DROP TABLE IF EXISTS " + UserManagerHelper.DATABASE_VERSION;

        db.execSQL(DROP_USER);

        Log.i(TAG, DROP_USER);
        onCreate(mDatabase);

    }

    @Override
    public long registerUser(User user) {
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.Column.USERNAME, user.getUsername());
        values.put(User.Column.PASSWORD, user.getPassword());
        values.put(User.Column.EMAIL, user.getEmail());
        values.put(User.Column.AGE, user.getAge());
        values.put(User.Column.GENDER, user.getGender());

        long result = mDatabase.insert(User.TABLE_NAME, null, values);
        mDatabase.close();

        return result;
    }

    @Override
    public User checkUserLogin(User user) {
        mDatabase = this.getReadableDatabase();

        Cursor cursor = mDatabase.query(User.TABLE_NAME,
                null,
                User.Column.USERNAME + " = ? AND " +
                        User.Column.PASSWORD + " = ?",
                new String[]{user.getUsername(), user.getPassword()},
                null,
                null,
                null);

        User currentUser = new User();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                currentUser.setId((int) cursor.getLong(0));
                currentUser.setUsername(cursor.getString(1));
                currentUser.setPassword(cursor.getString(2));
                mDatabase.close();
                return currentUser;
            }
        }

        return null;
    }

    @Override
    public int changePassword(User user) {
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.Column.USERNAME, user.getUsername());
        values.put(User.Column.PASSWORD, user.getPassword());

        int row = mDatabase.update(User.TABLE_NAME,
                values,
                User.Column.ID + " = ?",
                new String[]{String.valueOf(user.getId())});

        mDatabase.close();
        return row;
    }
}