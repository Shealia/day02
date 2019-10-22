package cn.johnyu.day02;

import android.os.Binder;

public class MyBinder extends Binder {
    private int progress;

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
