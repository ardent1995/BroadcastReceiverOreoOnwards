package com.example.broadcastreceiveroreoonwards;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ExampleExplicitBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Explicit BroadcastReceiver Triggered", Toast.LENGTH_SHORT).show();
    }
}
