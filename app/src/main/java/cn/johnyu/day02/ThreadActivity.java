package cn.johnyu.day02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity {
    private TextView tv;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        tv=findViewById(R.id.tv);
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
               String cai= msg.getData().getString("msg");
               tv.setText(cai);
            }
        };
    }

    public void test(View view) {

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String msg="Hello John";
               Message message= handler.obtainMessage();
                Bundle bundle=new Bundle();
                bundle.putString("msg",msg);
                message.setData(bundle);
                handler.sendMessage(message);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv.setText(msg);
//                    }
//                });
            }
        }.start();

    }
}
