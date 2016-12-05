package hardware.Computer_Hardware;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pc on 19/10/2559.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hardware.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE "+
            "(_id integer primary key, User text, Password text, Name text);";
    private static final String CREATE_HARDWARE_TABLE = "create table hardware "+
            "(_id integer primary key, name text, pic text, des text );";
    private static final String CREATE_ORDER_TABLE = "create table orderTABLE"+
            "(_id integer primary key, Officer text, Desk text, Food text, Item text);";

    public MySQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   //Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_HARDWARE_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
