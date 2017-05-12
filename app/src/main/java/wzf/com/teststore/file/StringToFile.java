package wzf.com.teststore.file;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wzf on 2017/5/12.
 * 将字符串存储到file中
 */

public class StringToFile {

    public static void storeToFile(String str, Context context) {
        String filename = "myfile";
        FileOutputStream outputStream = null;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getFromFile(Context context) {
        String filename = "myfile";
        FileInputStream inputStream = null;
        StringBuffer sb = new StringBuffer();
        try {
            inputStream = context.openFileInput(filename);
            int len;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != 0) {
                sb.append(new String(b, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
