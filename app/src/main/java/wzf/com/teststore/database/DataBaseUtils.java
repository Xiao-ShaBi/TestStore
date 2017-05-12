package wzf.com.teststore.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wzf on 2017/5/12.
 * 操作数据库
 */

public class DataBaseUtils {

    public static void storeDataBase(Context context, String key, String value) {
        DatabaseStore ds = new DatabaseStore(context);
        SQLiteDatabase writableDatabase = ds.getWritableDatabase();
        writableDatabase.execSQL("insert into test(key, value) values(?,?)", new Object[]{key, value});
        writableDatabase.close();
    }

    public static String getDataBase(Context context, String key) {
        DatabaseStore ds = new DatabaseStore(context);
        SQLiteDatabase readableDatabase = ds.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select * from test where key = ?", new String[]{key});
        String value = "";
        while (cursor.moveToNext()) {
            value = cursor.getString(cursor.getColumnIndex("value"));//获取第二列的值
        }
        return value;
    }

}
