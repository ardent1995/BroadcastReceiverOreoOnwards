package com.example.broadcastreceiveroreoonwards;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ExampleImplicitBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.example.broadcastreceiveroreoonwards.ACTION_EXAMPLE".equals(intent.getAction())){
            String message = intent.getStringExtra("com.example.broadcastreceiveroreoonwards.EXTRA_EXAMPLE");
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
