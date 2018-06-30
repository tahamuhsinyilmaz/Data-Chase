package com.example.mac.datachase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

/**
 * Created by mac on 13.03.2018.
 */

public class AlmaYollamaKatmani {
    MqttHelper mqttHelper;
    public String msg;
    public AlmaYollamaKatmani(Context c){
        mqttHelper= new MqttHelper(c);
    }

    public String receiveMessage(){

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
                   msg=mqttMessage.toString();



            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }

        });
    return msg;
    }
    public void publishMessage(@NonNull String msg, int qos)
            throws MqttException, UnsupportedEncodingException {
        byte[] encodedPayload = new byte[0];
        encodedPayload = msg.getBytes("UTF-8");
        MqttMessage message = new MqttMessage(encodedPayload);
        message.setId(5866);
        message.setRetained(true);
        message.setQos(qos);
       mqttHelper.mqttAndroidClient.publish("datachase/android",message);
    }
}
