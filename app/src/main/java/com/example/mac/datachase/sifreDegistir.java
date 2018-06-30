package com.example.mac.datachase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sifreDegistir extends AppCompatActivity {
    EditText yeni,yeniTekrar;
    Button tamamla;
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre_degistir);
        yeni= (EditText) findViewById(R.id.editText3);
        yeniTekrar= (EditText) findViewById(R.id.editText9);
        tamamla =(Button) findViewById(R.id.button9);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        tamamla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yeni.getText().toString().equals(yeniTekrar.getText().toString())){
                        user.updatePassword(yeni.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(sifreDegistir.this,"Şifre Değişti.",Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(sifreDegistir.this,MainActivity.class);
                                            startActivity(i);
                                        }
                                    }
                                });
                }
                else{
                    Toast.makeText(sifreDegistir.this,"Girilen Şifre Değerleri Uyuşmuyor.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
