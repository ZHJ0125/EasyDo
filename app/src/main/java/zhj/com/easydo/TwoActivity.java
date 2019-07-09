package zhj.com.easydo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TwoActivity extends AppCompatActivity {
    TextView back_mark, ok_mark;
    EditText getMemo;
    String Memo_content;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");// HH:mm:ss
    Date date = new Date(System.currentTimeMillis());
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载two.xml布局文件
        setContentView(R.layout.make_new_memory);
        getMemo = (EditText)findViewById(R.id.edit1);
        back_mark = (TextView)findViewById(R.id.mark_back);
        ok_mark = (TextView)findViewById(R.id.mark_ok);
        back_mark.setOnClickListener(new markClick());
        ok_mark.setOnClickListener(new markClick());
        time = (TextView)findViewById(R.id.NowTime);
        time.setText(simpleDateFormat.format(date));
    }
    class markClick implements OnClickListener{
        @Override
        public void onClick(View v) {
            if (v == back_mark){
                Intent intent = new Intent();
                intent.setClass(TwoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                TwoActivity.this.finish();
            }
            else if (v == ok_mark){
                // 如果输入内容不为空，则传递消息
                Memo_content = getMemo.getText().toString();
                if (TextUtils.isEmpty(Memo_content)){
                    Toast.makeText(TwoActivity.this, "您输入的内容为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent();
                    //intent.putExtra("new", Memo_content);   // 以键为new传递消息
                    intent.setClass(TwoActivity.this, MainActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    Bundle bundle = new Bundle();
                    bundle.putString("new", Memo_content);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                //Toast.makeText(TwoActivity.this, "You clicked OK", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
