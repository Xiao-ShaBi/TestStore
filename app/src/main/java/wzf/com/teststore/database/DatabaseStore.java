package wzf.com.teststore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wzf on 2017/5/12.
 * 操作数据库
 */

public class DatabaseStore extends SQLiteOpenHelper {

    public DatabaseStore(Context context) {
        super(context, "test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS test (key varchar, value varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
