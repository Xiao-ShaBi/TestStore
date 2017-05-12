package wzf.com.teststore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wzf.com.teststore.database.DataBaseUtils;
import wzf.com.teststore.file.StringToFile;
import wzf.com.teststore.sp.SPUtils;


/**
 * 主要用来制作一个测试sp存储，文件存储，和数据库存储的效率
 * 尽量试试
 * 这里主要做效率测试，关于cpu内存占用情况暂不做分析
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_sp_cun)
    Button btnSpCun;
    @BindView(R.id.tv_sp_cun)
    TextView tvSpCun;
    @BindView(R.id.btn_sp_qu)
    Button btnSpQu;
    @BindView(R.id.tv_sp_qu)
    TextView tvSpQu;
    @BindView(R.id.btn_kongbai)
    Button btnKongbai;
    @BindView(R.id.tv_kongbai)
    TextView tvKongbai;
    @BindView(R.id.btn_file_cun)
    Button btnFileCun;
    @BindView(R.id.tv_file_cun)
    TextView tvFileCun;
    @BindView(R.id.btn_file_qu)
    Button btnFileQu;
    @BindView(R.id.tv_file_qu)
    TextView tvFileQu;
    @BindView(R.id.btn_data_cun)
    Button btnDataCun;
    @BindView(R.id.tv_data_cun)
    TextView tvDataCun;
    @BindView(R.id.btn_data_qu)
    Button btnDataQu;
    @BindView(R.id.tv_data_qu)
    TextView tvDataQu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 测试运行时间的程序
     *
     * @param str
     * @param runnable
     */
    public String test(String str, Runnable runnable) {
        long l = System.currentTimeMillis();
        runnable.run();
        l = System.currentTimeMillis() - l;
        Log.e("TAG", str + "的测试的时间为" + l);
        return str + "时间为:" + l;
    }

    @OnClick({R.id.btn_sp_cun, R.id.btn_sp_qu, R.id.btn_file_cun, R.id.btn_file_qu, R.id.btn_data_cun, R.id.btn_data_qu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sp_cun:
                String spCun = test("sp存", new Runnable() {
                    @Override
                    public void run() {
                        SPUtils.SPStore(MainActivity.this, "test", testStr);
                    }
                });
                tvSpCun.setText(spCun);
                break;
            case R.id.btn_sp_qu:
                String spQu = test("sp取", new Runnable() {
                    @Override
                    public void run() {
                        String test = SPUtils.getSP(MainActivity.this, "test");
                    }
                });
                tvSpQu.setText(spQu);
                break;
            case R.id.btn_file_cun:
                String fileCun = test("file存", new Runnable() {
                    @Override
                    public void run() {
                        StringToFile.storeToFile(testStr, MainActivity.this);
                    }
                });
                tvFileCun.setText(fileCun);
                break;
            case R.id.btn_file_qu:
                String fileQu = test("file取", new Runnable() {
                    @Override
                    public void run() {
                        String test = SPUtils.getSP(MainActivity.this, "test");
                    }
                });
                tvFileQu.setText(fileQu);
                break;

            case R.id.btn_data_cun:
                String dataCun = test("data存", new Runnable() {
                    @Override
                    public void run() {
                        DataBaseUtils.storeDataBase(MainActivity.this, "test", testStr);
                    }
                });
                tvDataCun.setText(dataCun);
                break;
            case R.id.btn_data_qu:
                String dataQu = test("data取", new Runnable() {
                    @Override
                    public void run() {
                        String test = DataBaseUtils.getDataBase(MainActivity.this, "test");
                    }
                });
                tvDataQu.setText(dataQu);
                break;
        }
    }

    @OnClick(R.id.btn_kongbai)
    public void onViewClicked() {
        String spQu = test("啥也不做", new Runnable() {
            @Override
            public void run() {
            }
        });
        tvKongbai.setText(spQu);
    }


    //545   sp多次存取之后存时间为5取0   file存1取0  data 存7取3
    //第一次存取时间为           10  0        2  0         18  4
//    String testStr = "sdhfuuuuikljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljkljklj";

    //20个字符             sp 7 0  file 3 0  data 19 3
//    String testStr = "zhejiushimimashdjsjf";

    //有中文时  1102   数据与之前差不多，就不存了
//    String testStr = "接口文档：\n" +
//            "A、数据上报/物品库：http://doc.datagrand.com/developer/smart-rec#数据上报\n" +
//            "B、个性化推荐：http://doc.datagrand.com/developer/smart-rec#推荐调用\n" +
//            "\n" +
//            "需求描述：\n" +
//            "1、万年历服务器同步近3个月精品文章数据至达观服务器，并保持实时更新，每条资讯涉及字段如下，详见接口文档A\n" +
//            "1）itemid：文章ID\n" +
//            "2）cateid：文章分类，支持多个\n" +
//            "3）title：文章标题\n" +
//            "4）item_modify_time：文章最新修改时间\n" +
//            "\n" +
//            "2、万年历客户端上报用户在无线信息流的操作数据至万年历服务器，万年历服务器实时同步至达观服务器，详见接口文档A\n" +
//            "1）userid：用户唯一id\n" +
//            "2）itemid：对应item表的itemid\n" +
//            "3）action_type：用户对item执行的操作“点击=rec_click”“分享=share”\n" +
//            "\n" +
//            "3、万年历客户端带设备标识与无线信息流页数向万年历服务请求数据，万年历服务器实时带该设备标识达观服务器请求推荐资讯ID列表，成功返回后将ID列表对应资讯返回给客户端。若没有正确返回ID列表，则按既有规则返回人工推荐列表对应数据。\n" +
//            "\n" +
//            "4、万年历服务器需保存客户端上传的操作数据，便于后续统计\n" +
//            "\n" +
//            "5、达观服务器一次请求返回的推荐资讯ID列表最多包含64个内容ID，万年历服务器可以针对同一设备ID的下一次达观服务器请求时机做调整。\n" +
//            "比如上一次返回的N条数据中，客户端第一次请求后返回10条，第二次请求即重新向达观服务器请求新的ID列表。\n" +
//            "或者直到客户端请求完所有上一个ID列表所包含的数据后，客户端的下一次请求才向达观服务器请求新的ID列表。";

    //sp存的时间变化比较大，大数据时间稍微久一点，file变化相对较小，速度也是三种最快的 data在第一次创建数据库的时候比较慢，之后速度相比另外两种也比较慢，但是用来存复杂数据比较好。取得时间也比较慢
//    String testStr = "1";
    //超大数据 10万
    String testStr = "要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的" +"要理解 Java 中String的运作方式，必须明确一点：String 是一个非可变类（immutable）。\n" +
            "什么是非可变类呢？简单说来，非可变类的实例是不能被修改的，每个实例中包含\n" +
            "的信息都必须在该实例创建的时候就提供出来，并且在对象的整个生存周期内固定\n" +
            "不变。java 为什么要把String 设计为非可变类呢？你可以问问 james Gosling ：）。但\n" +
            "是非可变类确实有着自身的优势，如状态单一，对象简单，便于维护。其次，该类\n" +
            "对象对象本质上是线程安全的，不要求同步。此外用户可以共享非可变对象，甚至\n" +
            "可以共享它们的内部信息。（详见《Effective java》item 13）。String 类在java 中被大\n" +
            "量运用，甚至在class 文件中都有其身影，因此将其设计为简单轻便的非可变类是比\n" +
            "较合适的";
}

