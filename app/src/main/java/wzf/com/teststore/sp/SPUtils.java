package wzf.com.teststore.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wzf on 2017/5/12.
 * sp存储的工具类
 */

public class SPUtils {

    public static void SPStore(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("test", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getSP(Context context, String key) {
        return context.getSharedPreferences("test", Context.MODE_PRIVATE).getString(key, "");
    }

}
