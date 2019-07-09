package zhj.com.easydo;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity{
    Button btn_forget, btn_setpwd, btn_pwdok;
    EditText edit_pwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_layout);
        // 设置密码页面
        btn_forget = (Button)findViewById(R.id.button_remind);
        btn_setpwd = (Button)findViewById(R.id.button_set_pwd);
        btn_pwdok = (Button)findViewById(R.id.btn_pwdok);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        btn_setpwd.setOnClickListener(new pwdClick());
        btn_forget.setOnClickListener(new pwdClick());
        btn_pwdok.setOnClickListener(new pwdClick());
    }

    // 设置密码按钮监听事件
    class pwdClick implements OnClickListener{
        @Override
        public void onClick (View v){
            if (v == btn_forget){
                Toast.makeText(PasswordActivity.this, "密码是123", Toast.LENGTH_SHORT).show();
            }
            if (v == btn_setpwd){
                Toast.makeText(PasswordActivity.this, "密码设置功能暂未实现", Toast.LENGTH_SHORT).show();
            }
            if (v == btn_pwdok){
                String pwdString = edit_pwd.getText().toString();
                if (TextUtils.isEmpty(edit_pwd.getText()) ){
                    Toast.makeText(PasswordActivity.this, "未输入密码", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pwdString.equals("123")){
                        Toast.makeText(PasswordActivity.this, "密码输入正确", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(PasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(PasswordActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
