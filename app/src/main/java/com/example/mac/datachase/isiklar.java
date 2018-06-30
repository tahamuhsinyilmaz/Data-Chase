package com.example.mac.datachase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

/**
 * Created by mac on 27.02.2018.
 */

public class isiklar extends Fragment {
    ToggleButton giris,koridor,anaSalon;
    AlmaYollamaKatmani almaYollamaKatmani;
    View vi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vi= inflater.inflate(R.layout.isiklar_layout,container,false);

        giris=(ToggleButton) vi.findViewById(R.id.toggleButton);
        koridor=(ToggleButton) vi.findViewById(R.id.toggleButton2);
        anaSalon=(ToggleButton) vi.findViewById(R.id.toggleButton3);
        almaYollamaKatmani = new AlmaYollamaKatmani(isiklar.super.getContext());

        giris.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        almaYollamaKatmani.publishMessage("girisiyak",2);

                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        almaYollamaKatmani.publishMessage("girissondur",2);// The toggle is disabled
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        koridor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        almaYollamaKatmani.publishMessage("koridoryak",2);// The toggle is enabled
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        almaYollamaKatmani.publishMessage("koridorsondur",2);// The toggle is disabled
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        anaSalon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        almaYollamaKatmani.publishMessage("anasalonyak",2);// The toggle is enabled
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        almaYollamaKatmani.publishMessage("anasalonsondur",2);// The toggle is disabled
                    } catch (MqttException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return vi;
    }
}
