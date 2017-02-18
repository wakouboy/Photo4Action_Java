package com.example.wakouboy.photo4action;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

// 配置gradle时候记得备份！！！！
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView task1_textView = (TextView) findViewById(R.id.task1_textView);
        task1_textView.setClickable(true);
        Log.v("AAA", "MSG");
        try {
            connect();
        } catch (java.net.URISyntaxException e){
            e.printStackTrace();
            return ;
        }

        task1_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.wakouboy.photo4action.task1.Task1ViewActivity.class);
                startActivity(intent);
            }
        });
    };
    public void connect() throws java.net.URISyntaxException {
        Nanostate nanostate = Nanostate.getNanostate();
        nanostate.connect();
    };

}

