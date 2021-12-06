package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CharSQLiteOpenHelper  extends SQLiteOpenHelper {
    public CharSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table charTemplate(" +
                "id int primary key," +
                " level int," +
                " ps int," +
                " eva int," +
                " imp int," +
                " pun int," +
                " mag int," +
                " fza int," +
                " agl int," +
                " per int," +
                " car int," +
                " name text," +
                " archetype text," +
                " description text," +
                " notes text)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}