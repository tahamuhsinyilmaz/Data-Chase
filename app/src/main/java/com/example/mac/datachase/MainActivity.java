package com.example.mac.datachase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {
    TextView txt;
    ToggleButton toggle;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("sirket");
    MqttHelper mqttHelper;
    NotificationCompat.Builder builder ;
    PendingIntent resultPendingIntent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
               /* case R.id.navigation_home:
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, new kamera()).commit();
                    return true;*/
                case R.id.navigation_isiklar:
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.container, new isiklar()).commit();
                    return true;
                case R.id.navigation_panjurlar:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    fragmentManager2.beginTransaction().replace(R.id.container, new panjur()).commit();
                    return true;
                case R.id.navigation_sicaklik:
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    fragmentManager3.beginTransaction().replace(R.id.container, new sicakliNem()).commit();
                    return true;
                case R.id.navigation_kamera:
                    FragmentManager fragmentManager4 = getSupportFragmentManager();
                    fragmentManager4.beginTransaction().replace(R.id.container, new kamera()).commit();
                    return true;
                case R.id.navigation_daha:
                    FragmentManager fragmentManager5 = getSupportFragmentManager();
                    fragmentManager5.beginTransaction().replace(R.id.container, new dahaFazla()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt =(TextView) findViewById(R.id.textView7);
        toggle=(ToggleButton) findViewById(R.id.toggleButton4);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        txt.setText(currentUser.getEmail());

        mqttHelper=new MqttHelper(this);

        Intent resultIntent = new Intent(this,MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder = new NotificationCompat.Builder(this,"M_CH_ID")
        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
        .setAutoCancel(true)
        .setContentTitle("DataChase: Ofisden Haber Var")
        .setContentIntent(resultPendingIntent);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               String value = dataSnapshot.getValue(String.class);
               if(value.equals("acik")){
                 toggle.setChecked(true);
               }
               else if(value.equals("kapali")){
                  toggle.setChecked(false);
               }


            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hata", "Failed to read value.", error.toException());
            }

        });
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("sirket");
                    myRef.setValue("acik");



                } else {
                    // The toggle is disabled
                    // The toggle is enabled
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("sirket");

                    myRef.setValue("kapali");

                }
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mqttHelper.setCallback(new MqttCallbackExtended() {

            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", mqttMessage.toString());
                if(toggle.getText().toString().equals("OFF")) {
                    if (mqttMessage.toString().equals("Alev")) {
                        builder.setContentText("Dikkat Ofis İçi Alev Algılandı.");
                        builder.setContentIntent(resultPendingIntent);
                        int notificationId = 001;
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(notificationId, builder.build());
                    } else if (mqttMessage.toString().equals("sel")) {
                        builder.setContentText("Dikkat Ofis İçi Sel Algılandı.");
                        builder.setContentIntent(resultPendingIntent);
                        int notificationId = 002;
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(notificationId, builder.build());
                    } else if (mqttMessage.toString().equals("hareket")) {
                        builder.setContentText("Dikkat Ofis İçi Hareket Algılandı.");
                        builder.setContentIntent(resultPendingIntent);
                        int notificationId = 003;
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(notificationId, builder.build());
                    } else if (mqttMessage.toString().equals("gaz")) {
                        builder.setContentText("Dikkat Ofis İçi Gaz Algılandı.");
                        builder.setContentIntent(resultPendingIntent);
                        int notificationId = 004;
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(notificationId, builder.build());
                    } else if (mqttMessage.toString().equals("titresim")) {
                        builder.setContentText("Dikkat Ofis içi Tireşim Algılandı.");
                        builder.setContentIntent(resultPendingIntent);
                        int notificationId = 005;
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(notificationId, builder.build());
                    }
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

        });


    }
    @Override
    public void onBackPressed() {
        // geri donmemek icin
    }


}
