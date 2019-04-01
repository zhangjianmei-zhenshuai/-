package zjm.com.xiangmu.ui.activity;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import zjm.com.xiangmu.data.utils.LogUtil;

public class APP extends Application {
    private static APP instance;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init(getApplicationContext());//Log

        //泄漏检测
        /*instance = this;
        mRefWatcher = Constants.DEBUG ?  LeakCanary.install(this) : RefWatcher.DISABLED;*/

        Fresco.initialize( this );//初始化Fresco  上下文&build

        UMConfigure.init(this,"5c6e41f9f1f556774100094c"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
    }

    public static APP getInstance() {//泄漏检测
        return instance;
    }

    public static RefWatcher getRefWatcher() {//泄漏检测
        return getInstance().mRefWatcher;
    }
}
