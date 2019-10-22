package cn.johnyu.day02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class StroeActivity extends AppCompatActivity
        implements View.OnClickListener {
    private Button storeBtn;
    private Button readBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroe);
        storeBtn = findViewById(R.id.store);
        readBtn = findViewById(R.id.read);
        storeBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store:
                storeToFile();
                break;
            case R.id.read:
                readFromFile();
                break;
        }
    }

    private void storeToPref() {
        SharedPreferences prf = getSharedPreferences("john", MODE_PRIVATE);
        SharedPreferences.Editor editor = prf.edit();
        Set<String> set = new HashSet<>();
        set.add("football");
        set.add("cba");
        editor.putString("uname", "john");
        editor.putStringSet("favs", set);
        editor.commit();

    }

    private void readFromPref() {
        SharedPreferences prf = getSharedPreferences("john", MODE_PRIVATE);
        String uname=prf.getString("uname","tom");
        Set<String> favs=prf.getStringSet("favs",null);
        Log.i("数据",uname);
        //favs.forEach(s->Log.i("favs",s));

        for(String s:favs){
            Log.i("favs:",s);
        }

    }
    private void storeToFile()  {
        try(OutputStream outputStream=
                    openFileOutput("aa.txt",MODE_PRIVATE|MODE_APPEND)) {
            outputStream.write("a我是中国人\n".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readFromFile()  {
        try(InputStream inputStream=openFileInput("aa.txt");
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));){
            String line=null;
            while ((line=reader.readLine())!=null){
                Log.i("info:",line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
