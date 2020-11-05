package com.example.smstest;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText recp, body;
    Button send;
    String phone, bodys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recp = findViewById(R.id.recp);
        body = findViewById(R.id.body);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = recp.getText().toString();
                bodys = body.getText().toString();
                if(phone.isEmpty()){
                    recp.requestFocus();
                    recp.setError("Fill first");
                }else{
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                            SmsManager sms = SmsManager.getDefault();
                            try {
                                sms.sendTextMessage(phone,null,bodys,null,null);
                                Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, "Failed to sent message", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                        }
                    }
                }

            }
        });
    }
}