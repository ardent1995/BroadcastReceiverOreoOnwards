package com.example.broadcastreceiveroreoonwards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ExampleDynamicBroadcastReceiver broadcastReceiver = new ExampleDynamicBroadcastReceiver();
    private ExampleImplicitBroadcastReceiver implicitBroadcastReceiver = new ExampleImplicitBroadcastReceiver();
    private ExampleOrderedReceiver1 exampleOrderedReceiver1 = new ExampleOrderedReceiver1();
    private ExampleLocalBroadcastReceiver exampleLocalBroadcastReceiver = new ExampleLocalBroadcastReceiver();
    private Button btnSendBroadcast,btnLocalBroadcast;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBroadcast = findViewById(R.id.btn_send_broadcast);
        btnLocalBroadcast = findViewById(R.id.btn_send_local_broadcast);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1st approach
                /*Intent intent = new Intent(MainActivity.this, ExampleExplicitBroadcastReceiver.class);
                sendBroadcast(intent);*/
                //2nd approach
                /*Intent intent = new Intent();
                intent.setClass(MainActivity.this,ExampleExplicitBroadcastReceiver.class);
                sendBroadcast(intent);*/
                //3rd approach
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName(MainActivity.this,ExampleExplicitBroadcastReceiver.class);
                intent.setComponent(componentName);
                sendBroadcast(intent);
            }
        });

        btnLocalBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcastreceiveroreoonwards.ACTION_EXAMPLE");
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        IntentFilter intentFilter = new IntentFilter("com.example.broadcastreceiveroreoonwards.ACTION_EXAMPLE");
        registerReceiver(implicitBroadcastReceiver,intentFilter);

        IntentFilter intentFilter1 = new IntentFilter("com.example.broadcastreceiveroreoonwards.ACTION_EXAMPLE");
        intentFilter1.setPriority(1);
        registerReceiver(exampleOrderedReceiver1,intentFilter1, Manifest.permission.VIBRATE,null);

        IntentFilter intentFilter2 = new IntentFilter("com.example.broadcastreceiveroreoonwards.ACTION_EXAMPLE");
        localBroadcastManager.registerReceiver(exampleLocalBroadcastReceiver,intentFilter2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(implicitBroadcastReceiver);
        unregisterReceiver(exampleOrderedReceiver1);
        localBroadcastManager.unregisterReceiver(exampleLocalBroadcastReceiver);
    }
}
