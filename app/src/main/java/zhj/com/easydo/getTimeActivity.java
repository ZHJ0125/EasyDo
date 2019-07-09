package zhj.com.easydo;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class getTimeActivity extends AppCompatActivity{
    TextView btn_back, btn_ok;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_remind_time);
        btn_back = (TextView) findViewById(R.id.date_back);        btn_ok = (TextView) findViewById(R.id.date_ok);
        btn_ok.setOnClickListener(new dClick());
        btn_back.setOnClickListener(new dClick());
    }
    class dClick implements OnClickListener{
        @Override
        public void onClick (View v){
            if (v == btn_back){
                Toast.makeText(getTimeActivity.this, "返回编辑界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getTimeActivity.this, TwoActivity.class);
                startActivity(intent);
            }
            if (v == btn_ok){
                Toast.makeText(getTimeActivity.this, "已保存，返回编辑界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getTimeActivity.this, TwoActivity.class);
                startActivity(intent);
            }
        }
    }
}
