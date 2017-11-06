package com.example.jgw.myapplication;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText et_ipnumber;
    private SharedPreferences sp;
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ipnumber = (EditText) findViewById(R.id.et_ipnumber);
        // 创建SharedPreferences对象
        sp = getSharedPreferences("config", MODE_PRIVATE);
    }
    public void click(View view) {
        //授权
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.PROCESS_OUTGOING_CALLS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((MainActivity.this), new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);

        } else {
            // 获取用户输入的拦截号码
            String number = et_ipnumber.getText().toString().trim();
            //创建Editor对象,保存用户输入的拦截号码
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("number", number);
            editor.commit();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "click: 保存成功 ");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获取用户输入的拦截号码
                String number = et_ipnumber.getText().toString().trim();
                //创建Editor对象,保存用户输入的拦截号码
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("number", number);
                editor.commit();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "click: 保存成功 ");
            } else {
                Toast.makeText(this, "权限申请被拒绝", Toast.LENGTH_SHORT).show();
            }
            return;

        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
