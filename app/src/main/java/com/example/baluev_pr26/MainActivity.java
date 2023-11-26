package com.example.baluev_pr26;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Идентификатор уведомления
    private static final int NOTIFY_ID = 101;

    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_1){
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Напоминание")
                            .setContentText("Пора покормить кота")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(MainActivity.this);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }else if(v.getId() == R.id.btn_2){
            Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Напоминание")
                            .setContentText("Пора покормить кота")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(contentIntent);

            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(MainActivity.this);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }else if(v.getId() == R.id.btn_3){
            Intent notificationIntent = new Intent(MainActivity.this, SecondActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            String bigText = "Это я, почтальон Печкин. Принёс для вас посылку. "
                    + "Только я вам её не отдам. Потому что у вас документов нету. ";

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Посылка")
                            .setContentText("Это я, почтальон Печкин. Принес для вас посылку")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                    R.drawable.ic_launcher_foreground)) // большая картинка
                            .addAction(R.drawable.ic_launcher_foreground, "Запустить активность",
                                    pendingIntent)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                            .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(MainActivity.this);
            notificationManager.notify(NOTIFY_ID, builder.build());
        }
    }
}