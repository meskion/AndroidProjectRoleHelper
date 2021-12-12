package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import classes.CharTemplate;

public class CharTemplateRepository {

    private CharSQLiteOpenHelper sqlHelper;
    private SQLiteDatabase db;
    private static CharTemplateRepository instance;
    private Context cxt;

    private CharTemplateRepository(Context context) {
        sqlHelper = new CharSQLiteOpenHelper(context, "administracion", null, 1);

    }

    public static CharTemplateRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CharTemplateRepository(context);
        }
        instance.cxt = context;
        return instance;
    }

    public void save(CharTemplate ch) {
        db = sqlHelper.getWritableDatabase();
        boolean isNew = findById(ch.getId()) == null;
        ContentValues values = new ContentValues();
        values.put("id", ch.getId());
        values.put("level", ch.getLevel());
        values.put("ps", ch.getPs());
        values.put("eva", ch.getEva());
        values.put("imp", ch.getImp());
        values.put("pun", ch.getPun());
        values.put("mag", ch.getMag());
        values.put("fza", ch.getFza());
        values.put("agl", ch.getAgl());
        values.put("per", ch.getPer());
        values.put("car", ch.getCar());
        values.put("name", ch.getName());
        values.put("archetype", ch.getArchetype());
        values.put("description", ch.getDescription());
        values.put("notes", ch.getNotes());
        if (isNew) {
            db.insert("charTemplate", null, values);

            Toast.makeText(cxt, "new char insert", Toast.LENGTH_SHORT).show();
        } else {

            String[] args = new String[]{String.valueOf(ch.getId())};
            db.update("charTemplate", values, "id=?", args);

        }


    }

    public Map<Integer, String> findAll() {
        db = sqlHelper.getWritableDatabase();
        Cursor row = db.rawQuery("select id, name from charTemplate", null);

        Map<Integer, String> templates = new HashMap<>();
        row.moveToFirst();
        while (!row.isAfterLast()) {
            templates.put(row.getInt(0), row.getString(1)); //add the item
            row.moveToNext();
        }

        return templates;
    }

    public CharTemplate findById(int id) {
        db = sqlHelper.getWritableDatabase();
        CharTemplate ch = null;
        String[] args = new String[]{String.valueOf(id)};
        Cursor charRow = db.rawQuery("select * from charTemplate where id=?", args, null);
        charRow.moveToFirst();
        if (charRow.getCount() > 0) {
            ch = new CharTemplate(
                    charRow.getInt(0),
                    charRow.getInt(1),
                    charRow.getInt(2),
                    charRow.getInt(3),
                    charRow.getInt(4),
                    charRow.getInt(5),
                    charRow.getInt(6),
                    charRow.getInt(7),
                    charRow.getInt(8),
                    charRow.getInt(9),
                    charRow.getInt(10),
                    charRow.getString(11),
                    charRow.getString(12),
                    charRow.getString(13),
                    charRow.getString(14)
            );
        }
        return ch;

    }


    public void delete(int id) {
        db = sqlHelper.getWritableDatabase();
        db.delete("charTemplate", "id=" + id, null);

    }

    public void deleteAll(){
        db = sqlHelper.getWritableDatabase();
        db.delete("charTemplate", null, null);

    }
}
