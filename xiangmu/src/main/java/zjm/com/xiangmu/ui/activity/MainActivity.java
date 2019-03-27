package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.di.contract.Contract_Login;
import zjm.com.xiangmu.di.presenter.Presenter_Login;

public class MainActivity extends AppCompatActivity implements Contract_Login.Login_View_Interface {

    private TextView tv_login_kszc;
    private EditText ed_login_phone;
    private Button btn_login;
    private EditText ed_login_pwd;
    private Presenter_Login presenter_login;
    private ImageView img_login_eye;
    private SharedPreferences sp;
    private CheckBox checkBox1;
    private ImageView img_qq;
    private String pwd;



    //授权回调
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //得到用户名以及头像信息
            String name = data.get( "name" );
            String iconurl = data.get( "iconurl" );
            //带值跳转--用户名name和头像iconurl传到我的页面
            Intent intent = new Intent( MainActivity.this, ShouYeActivity.class );
            intent.putExtra( "name",name );
            intent.putExtra( "iconurl",iconurl );
            startActivity( intent );
            Toast.makeText( MainActivity.this, "qq登录成功", Toast.LENGTH_SHORT ).show();
            //结束当前页面
            finish();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Log.d( "log","1111111111111111111122222222222222222222222333333333333333333333" );
        //查找控件
        tv_login_kszc = (TextView)findViewById( R.id.tv_login_kszc );
        ed_login_phone = (EditText)findViewById( R.id.ed_login_phone );
        ed_login_pwd = (EditText)findViewById( R.id.ed_login_pwd );
        btn_login = (Button)findViewById( R.id.btn_login );
        img_login_eye = (ImageView)findViewById( R.id.img_login_eye );
        checkBox1 = findViewById( R.id.checkBox1 );
        img_qq = findViewById( R.id.img_qq );
        //调用数字键盘
        ed_login_phone.setInputType(InputType.TYPE_CLASS_NUMBER);

        //是否记住密码SP
        sp = getSharedPreferences( "user", MODE_PRIVATE );
        if (sp.getBoolean("jizhu", false)) {
            checkBox1.setChecked( sp.getBoolean("jizhu", false));
            ed_login_phone.setText( sp.getString("name", ""));
            ed_login_pwd.setText( sp.getString("pass", ""));
        }

        //新建P层
        presenter_login = new Presenter_Login();
        presenter_login.attahView( this );

        //点击快速注册
        kszc();
        //点击登录
        login();
        //当我点击小眼睛时
        eye();
        //当我点击qq登录时
        qq();
    }

    //当我点击qq登录时
    private void qq() {
        img_qq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户资料
                UMShareAPI mShareAPI=UMShareAPI.get( MainActivity.this );
                mShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener );
            }
        } );
    }

    //当我点击小眼睛时
    private void eye() {
        img_login_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_login_pwd.getInputType()==128){
                    //隐藏
                    ed_login_pwd.setInputType(129);
                    //闭眼
                    img_login_eye.setImageResource( R.mipmap.eye_close );
                }else {
                    //显示
                    ed_login_pwd.setInputType(128);
                    //睁眼
                    img_login_eye.setImageResource( R.mipmap.login_icon_eye );
                }
                ed_login_pwd.postInvalidate();
                Spannable text=ed_login_pwd.getText();
                Selection.setSelection(text, text.length());
            }
        } );
    }

    //点击登录
    private void login() {
        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取到输入框的账号密码,交给P层的方法
                String phone = ed_login_phone.getText().toString();
                pwd = ed_login_pwd.getText().toString();
                //账号密码交给P层处理
                presenter_login.requestData(phone, pwd );
            }
        } );
    }

    //点击快速注册-跳转到注册的页面
    private void kszc() {
        tv_login_kszc.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ZhuceActivity.class);
                startActivity( intent );
            }
        } );
    }


    @Override
    //数据刷新
    public void showLoginData(final String message) {
        runOnUiThread( new Runnable() {
            JSONObject json=null;
            @Override
            public void run() {
                try {
                     json = new JSONObject( message );
                    String messagee = json.getString( "message" );      // 提示語
                    Toast.makeText( MainActivity.this, ""+messagee, Toast.LENGTH_SHORT ).show();
                    String status = json.getString( "status" );         //狀態碼 是否登錄成功
                    JSONObject result = json.getJSONObject( "result" );
                    String headPic = result.getString( "headPic" );     //得到用户头像
                    String nickName = result.getString( "nickName" );   //得到用户名称
                    String phone = result.getString( "phone" );         //得到用户手机号
                    String sessionId = result.getString( "sessionId" ); //得到用户登录凭证
                    int sex = result.getInt( "sex" );                   //性别
                    int userId = result.getInt( "userId" );             //用户id
                    //根据结果码status判断是否登录成功  0000代表成功
                    if (status.equals( "0000" )) {
                        //登陆成功 带值跳转(用户的信息)
                        Intent intent = new Intent( MainActivity.this, ShouYeActivity.class );
                        intent.putExtra( "headPic",headPic );intent.putExtra( "nickName",nickName );
                        intent.putExtra( "phone",phone );intent.putExtra( "sessionId",sessionId );
                        intent.putExtra( "sex",sex );intent.putExtra( "userId",userId );
                        intent.putExtra( "pwd",pwd );intent.putExtra( "userId",userId );
                        startActivity( intent );
                        //判断是否记住密码
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean( "jizhu",checkBox1.isChecked() );
                        editor.putString("name", ed_login_phone.getText().toString().trim());
                        editor.putString("pass", ed_login_pwd.getText().toString().trim());
                        editor.commit();
                        //Toast.makeText( MainActivity.this, ""+messagee, Toast.LENGTH_SHORT ).show();
                        //结束当前页面
                        finish();
                    }else{
                        //Toast.makeText( MainActivity.this, ""+messagee, Toast.LENGTH_SHORT ).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } );
    }

    //qq回调Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        APP.getRefWatcher().watch(this);//内存检测
    }
}
