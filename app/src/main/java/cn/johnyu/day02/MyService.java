package cn.johnyu.day02;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int progress=0;
    private MyBinder binder=new MyBinder();
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
               while (true){
                   binder.setProgress(++progress);
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name= Thread.currentThread().getName();
        Log.i("服务",intent.getStringExtra("uname")+"\t"+name);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("销毁","destroy.....");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("绑定","bind.....");
       return  binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("解绑定","on unbind");
        return super.onUnbind(intent);
    }
}
