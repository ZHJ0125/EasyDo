package zhj.com.easydo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Menu;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ListView list;
    /*TextView back_mark, ok_mark;
    TextView show_time;*/
    private DrawerLayout drawerLayout;
    private LinearLayout drawerContent;
    String msg;
    TextView txt;
    ArrayAdapter<String> itemAdapter;
    ArrayList<String> itemArrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建toolbar对象
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerContent = (LinearLayout) findViewById(R.id.drawerContent);

        // 设置Toolbar
        toolbar.setTitle("EasyDo");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        // 设置toolbar支持actionbar
        setSupportActionBar(toolbar);

        // 使用ActionBarDrawerToggle，配合DrawerLayout和ActionBar,以实现推荐的抽屉功能。
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

        // 设置左侧侧滑菜单的文本对象
        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        TextView text4 = (TextView) findViewById(R.id.text4);
        // 设置侧滑菜单监听
        text1.setOnClickListener(new mClick());
        text2.setOnClickListener(new mClick());
        text3.setOnClickListener(new mClick());
        text4.setOnClickListener(new mClick());
        // 设置返回和确定的监听事件
        /*back_mark = (TextView)findViewById(R.id.mark_back);
        ok_mark = (TextView)findViewById(R.id.mark_ok);
        back_mark.setOnClickListener(new markClick());
        ok_mark.setOnClickListener(new markClick());*/

        // 设置时间对象
        /*show_time = (TextView)findViewById(R.id.NowTime);*/

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_write:
                        //Toast.makeText(MainActivity.this, "Write New Memorandum", Toast.LENGTH_SHORT).show();
                        //setContentView(R.layout.make_new_memory);       // 直接切换Activity的方式
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, TwoActivity.class);
                        //txt = findViewById(R.id.textList);
                        //txt.setText("change!!!!");
                        //createFile();
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        MainActivity.this.finish();       // 系统会关闭掉之前的Activity
                        break;
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "刷新消息列表内容", Toast.LENGTH_SHORT).show();
                        // 将此按钮的功能改为更新列表内容
                        // 使用intent检测是否传递来消息
                        txt = findViewById(R.id.textList);
                        Intent intent2 = getIntent();
                        Bundle bundle = intent2.getExtras();
                        msg = bundle.getString("new","Nothing");
                        if (msg.equals(null)){
                            Toast.makeText(MainActivity.this, "暂无新消息", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            txt.setText(msg);
                            itemArrey.add(0,msg);
                            itemAdapter.notifyDataSetChanged();
                        }
                        break;
                }
                return true;
            }
        });

        //下面是ListView对象的设置
        list = (ListView)findViewById(R.id.listView);
        /*String data[] = {
                "讨论实验室人员安排",
                "打扫实验室卫生",
                "进行Android实训"
        };*/

        itemArrey = new ArrayList<String>();
        /*txt = findViewById(R.id.textList);
        txt.setText("new itemArrey!!");*/
        itemArrey.add("安排实验室人员");
        itemArrey.add("打扫实验室卫生");
        itemArrey.add("进行Android实训");
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemArrey);
        list.setAdapter(itemAdapter);
        list.setOnItemClickListener(new mItemClick());
    }

    //监听ListView点击事件
    class mItemClick implements OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){
            Toast.makeText(MainActivity.this, "您选择的项目是："+((TextView)arg1).getText(), Toast.LENGTH_SHORT).show();
        }
    }

    // 添加菜单至主布局
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 设置侧滑栏目的监听事件
    class mClick implements OnClickListener{
        @Override
        public void onClick (View v) {
            // 关闭DrawerLayout
            drawerLayout.closeDrawer(drawerContent);
            switch (v.getId()) {
                case R.id.text1:
                    Toast.makeText(MainActivity.this, "全部", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.text2:
                    Toast.makeText(MainActivity.this, "日程", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.text3:
                    Toast.makeText(MainActivity.this, "重复", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.text4:
                    Toast.makeText(MainActivity.this, "以前", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    /*// 创建文件
    private void createFile() {
        //传入路径 + 文件名
        File mFile = new File("/sdcard/test.txt");
        if (!mFile.exists()) {
            //mFile.delete();
            try {
                //创建文件
                mFile.createNewFile();
                //给一个吐司提示，提示创建成功
                Toast.makeText(MainActivity.this, "文件创建成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/
}
