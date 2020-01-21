package com.shrestharohit.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    private Button not1, not2;
    private int counter,counter2;
    BroadcastReceiverExample broadcastReceiverExample=new BroadcastReceiverExample();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        not1 = findViewById(R.id.not1);
        not2 = findViewById(R.id.not2);

        not1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
DisplayNotification();
counter++;
            }
        });
        not2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
DisplayNotification2();
counter2++;
            }
        });
    }
    private void DisplayNotification(){
        Notification notification=new NotificationCompat.Builder(this,CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_sms_black_24dp)
                .setContentTitle("Message First ")
                .setContentText("Body of the first message")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(counter,notification);
    }
    private void DisplayNotification2(){
        Notification notification=new NotificationCompat.Builder(this,CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setContentTitle("Message Second ")
                .setContentText("Body of the second message")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(counter2,notification);
    }

    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiverExample,intentFilter);
    }
    protected void onStop() {

        super.onStop();
        unregisterReceiver(broadcastReceiverExample);
    }
}
