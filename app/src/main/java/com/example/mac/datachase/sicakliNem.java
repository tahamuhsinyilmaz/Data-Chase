package com.example.mac.datachase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

/**
 * Created by mac on 27.02.2018.
 */

public class sicakliNem extends Fragment{
    TextView sicaklik,nem;
    View vi;
    AlmaYollamaKatmani almaYollamaKatmani;
    MqttHelper mqttHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.sicaklik_layout,container,false);

        sicaklik=(TextView) vi.findViewById(R.id.textView14);
        nem=(TextView) vi.findViewById(R.id.textView15);
        almaYollamaKatmani=new AlmaYollamaKatmani(sicakliNem.super.getContext());

        mqttHelper=new MqttHelper(sicakliNem.super.getContext());
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
                if(mqttMessage.toString().contains("%")){
                    nem.setText(mqttMessage.toString());
                }
                else if (mqttMessage.toString().contains(".0"))
                {
                    sicaklik.setText(mqttMessage.toString()+" ºC");
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

        });


        return vi;
    }

}
