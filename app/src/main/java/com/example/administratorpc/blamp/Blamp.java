package com.example.administratorpc.blamp;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

/**
 * Created by AdministratorPC on 2016/11/14.
 */

public class Blamp extends Activity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//重写
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);//窗体模糊
        setContentView(R.layout.layout);//设置页面
        String tid= TelephonyManager.getSimSerialNumber();
        String aid=android.os.Build.SERIAL;
        TextView textView = (TextView) findViewById(R.id.textView);
        String uid=android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        int camearid= Camera.getNumberOfCameras();//获取摄像头数量
        textView.setText(aid+tid+uid+camearid);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Toast.makeText(Blamp.this, "屏幕长亮1小时",
                        Toast.LENGTH_SHORT).show();
                if (1 != 0) {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
                TimerTask task = new TimerTask() {
                    public void run() {
                        finish();
                    }

                };
                java.util.Timer timer = new java.util.Timer(false);
                timer.schedule(task, 3600000);

            }
        });
        Button button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(Blamp.this, "闪光灯长亮1小时",
                        Toast.LENGTH_SHORT).show();
                Camera camera = null;
                Camera.Parameters parameters = null;
                camera = Camera.open();
                parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);//开启
                camera.setParameters(parameters);

                TimerTask task = new TimerTask() {
                    public void run() {
                        WindowManager.LayoutParams lp=getWindow().getAttributes();
                        lp.alpha=0.3f;
                        getWindow().setAttributes(lp);
                       // finish();
                    }

                };
                java.util.Timer timer = new java.util.Timer(false);
                timer.schedule(task, 3600000);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(Blamp.this, "关闭闪光灯",
                        Toast.LENGTH_SHORT).show();
                try {
                    Camera m_Camera = Camera.open();
                    m_Camera = Camera.open();
                    Camera.Parameters mParameters;
                    mParameters = m_Camera.getParameters();
                    mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    m_Camera.setParameters(mParameters);
                    m_Camera.release();
                } catch (Exception ex) {
                }

            }
        });


    }
}
