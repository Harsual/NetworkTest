package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import java.net.InetAddress;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btn = findViewById(R.id.Start);
        final EditText edt = findViewById(R.id.editText);





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = edt.getText().toString();
                openActivity2(str);

            }
        });
    }

    void openActivity2(String s){
        Intent intent = new Intent(this, ConnectToServer.class);
        intent.putExtra("IP",s);
        startActivity(intent);

    }






}
