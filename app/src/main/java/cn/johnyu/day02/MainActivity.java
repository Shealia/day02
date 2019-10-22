package cn.johnyu.day02;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ServiceConnection connection;
    MyBinder binder;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb=findViewById(R.id.pb);
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder=(MyBinder)service;

                new Thread(){
                    @Override
                    public void run() {
                        while (true){
                            int p=binder.getProgress();
                            pb.setProgress(p);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("Activiy","disconnect");

            }
        };

        Intent intent=new Intent(this,MyService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);


    }

    public void access(View view) {
        Intent intent=new Intent(this,MyService.class);
        intent.putExtra("uname","John");
        startService(intent);
       // stopService(intent);
    }

    public void bind(View view) {


    }

    public void unbind(View view) {
        Intent intent=new Intent(this,MyService.class);
        unbindService(connection);
    }
}
