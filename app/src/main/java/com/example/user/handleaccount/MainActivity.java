package com.example.user.handleaccount;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //对数据库进行增加一条记录  由于数据库的内容已经通过内容提供者暴露出来 所以我们只需要使用内容解析者去操作就可以了
    public void add (View view) {
        Uri uri = Uri.parse("content://com.account.provider/insert");
        ContentValues values = new ContentValues();

        //key 对应表的字段  value 对应值
        values.put("name", "yumi");
        values.put("money", 99999);
        Uri url = getContentResolver().insert(uri, values);
        Log.v("Insert:", url+"");
    }

    //对数据库进行删除一条记录
    public void delete (View view) {
        Uri uri = Uri.parse("content://com.account.provider/delete");
        int delete = getContentResolver().delete(uri, "name=?", new String[] {"yumi"});
        Toast.makeText(this, "删除了"+delete+"行", Toast.LENGTH_SHORT).show();
    }

    //对数据库进行修改一条记录
    public void update (View view) {
        Uri uri = Uri.parse("content://com.account.provider/update");
        ContentValues values = new ContentValues();
        values.put("money", 0.0);
        int update = getContentResolver().update(uri, values, "name=?", new String[] {"yumi"});
        Toast.makeText(this, "更新了"+update+"行", Toast.LENGTH_SHORT).show();
    }

    //对数据库进行查找一条记录
    public void select (View view) {
        Uri uri = Uri.parse("content://com.account.provider/query");
        Cursor cursor = getContentResolver().query(
                uri, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 ) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String money = cursor.getString(2);
                System.out.println("~~~~~~name:" + name + "----money :" + money);
            }
        }
    }
}
