package com.example.jgw.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class OutCallReceiver extends BroadcastReceiver {
    private static final String TAG = "OutCallReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: 开始 ");
        Toast.makeText(context,"开始!",Toast.LENGTH_SHORT).show();
        //获取拨打的电话号码
        String outcallnumber = getResultData();
        //创建SharedPreferences对象,获取拦截号码
        SharedPreferences sp =context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String number =sp.getString("number",null);
        //判断是否是拦截电话号码
        if(outcallnumber.equals(number)){
            //清除电话
            setResultData(null);
            Log.i(TAG, "onReceive: 清除");
        }
    }
}