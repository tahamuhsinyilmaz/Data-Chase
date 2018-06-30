package com.example.mac.datachase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;
import java.security.KeyStore;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by mac on 27.02.2018.
 */

public class panjur extends Fragment {
    Switch panjur1,panjur2,panjur3;
    AlmaYollamaKatmani almaYollamaKatmani1,almaYollamaKatmani2,almaYollamaKatmani3;
    TextView st;
    String deger;
    View vi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vi=inflater.inflate(R.layout.panjur_layout,container,false);
        
        panjur1=(Switch) vi.findViewById(R.id.switch2);
        panjur2=(Switch) vi.findViewById(R.id.switch3);
        panjur3 = (Switch) vi.findViewById(R.id.switch4);
        st=(TextView) vi.findViewById(R.id.textView5);
        almaYollamaKatmani1 = new AlmaYollamaKatmani(getActivity().getApplicationContext());
        //almaYollamaKatmani2 = new AlmaYollamaKatmani(panjur.super.getContext(),"datachase/panjur2");
        //almaYollamaKatmani3 = new AlmaYollamaKatmani(panjur.super.getContext(),"datachase/panjur3");

        panjur1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    try {
                        almaYollamaKatmani1.publishMessage("panjurac",2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        almaYollamaKatmani1.publishMessage("panjurkapa",2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*panjur2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    try {
                        almaYollamaKatmani1.publishMessage("panjurac",2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        almaYollamaKatmani1.publishMessage("panjurkapa",2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        panjur3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    try {
                        almaYollamaKatmani1.publishMessage("panjurac",2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        almaYollamaKatmani1.publishMessage("panjurkapa",2);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
        return vi;


    }

}
