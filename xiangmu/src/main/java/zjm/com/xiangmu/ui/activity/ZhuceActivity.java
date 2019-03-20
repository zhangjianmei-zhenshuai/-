package zjm.com.xiangmu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import zjm.com.xiangmu.R;
import zjm.com.xiangmu.di.contract.Contract_Register;
import zjm.com.xiangmu.di.presenter.Presenter_Register;

public class ZhuceActivity extends AppCompatActivity implements Contract_Register.Register_View_Interface {

    private TextView tv_register_ljdl;
    private Button btn_register;
    private EditText ed_register_phone;
    private EditText ed_register_pwd;
    private Contract_Register.Register_Presenter_Interface presenter_register;
    private EditText ed_register_yzm;
    private double rd;  //随机数
    private int  sws;  //生成的4位数
    private TextView tv_register_yzm;
    private ImageView img_register_eye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_zhuce );
        tv_register_ljdl = (TextView)findViewById( R.id.tv_register_ljdl );
        btn_register = (Button)findViewById( R.id.btn_register );
        ed_register_phone = (EditText)findViewById( R.id.ed_register_phone );
        //调用数字键盘
        ed_register_phone.setInputType(InputType.TYPE_CLASS_NUMBER);
        ed_register_pwd = (EditText)findViewById( R.id.ed_register_pwd );
        ed_register_yzm = (EditText)findViewById( R.id.ed_register_yzm );
        ed_register_yzm.setKeyListener(null);//禁止输入内容--验证码随即生成  生成后无法被认为修改
        tv_register_yzm = (TextView)findViewById( R.id.tv_register_yzm );
        img_register_eye = findViewById( R.id.img_register_eye );

        //新建P层
        presenter_register = new Presenter_Register();
        //绑定
        presenter_register.attahView( this );

        //点击已有账户?立即登录
       ljdl();
       //点击注册按钮
        zhuce();
        //点击生成验证码时
        yzm();
        //当我点击小眼睛时
        eye();

    }

    //当我点击小眼睛时
    private void eye() {
        img_register_eye.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_register_pwd.getInputType()==128){
                    //隐藏
                    ed_register_pwd.setInputType(129);
                    //闭眼
                    img_register_eye.setImageResource( R.mipmap.eye_close );
                }else {
                    //显示
                    ed_register_pwd.setInputType(128);
                    //睁眼
                    img_register_eye.setImageResource( R.mipmap.register_icon_eye );
                }
                ed_register_pwd.postInvalidate();
                Spannable text=ed_register_pwd.getText();
                Selection.setSelection(text, text.length());
            }
        } );
    }

    //点击生成验证码时
    private void yzm() {
        tv_register_yzm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do {
                    rd = Math.random();
                    sws = (int) (rd*10000);
                } while (rd < 0.1);
                //给输入框赋值
                ed_register_yzm.setText( sws+"" );
            }
        } );
    }

    //点击注册按钮
    private void zhuce() {
        btn_register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取账号密码
                String phone = ed_register_phone.getText().toString();
                String pwd = ed_register_pwd.getText().toString();
                //账号密码给P层处理
                presenter_register.requestRegisterData( phone,pwd );
            }
        } );
    }

    //点击已有账户?立即登录  跳转到登录页面  结束当前的页面
    private void ljdl() {
        tv_register_ljdl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ZhuceActivity.this, MainActivity.class );
                startActivity( intent );
                finish();;
            }
        } );
    }

    @Override
    //数据刷新
    public void showRegisterData(final String message) {
        //开启子线程
        runOnUiThread( new Runnable() {
            JSONObject json=null;
            @Override
            public void run() {
                try {
                    json = new JSONObject( message );
                    String messagee = json.getString( "message" );
                    String status = json.getString( "status" );
                    //根据结果码判断是否登录成功 如果成功直接跳转到登录页面进行登录
                    if (status.equals( "0000")) {
                        Intent intent = new Intent( ZhuceActivity.this, MainActivity.class );
                        startActivity( intent );
                        //结束当前页面
                        finish();
                        Toast.makeText( ZhuceActivity.this, messagee+",已跳转到登录页面", Toast.LENGTH_SHORT ).show();
                    }else{
                        Toast.makeText( ZhuceActivity.this, ""+messagee, Toast.LENGTH_SHORT ).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } );
    }
}
