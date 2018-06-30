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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class kayit extends AppCompatActivity {
    EditText mail,sifre,tekrar;
    Button kayit;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        mail = (EditText) findViewById(R.id.editText6);
        sifre = (EditText) findViewById(R.id.editText7);
        tekrar = (EditText) findViewById(R.id.editText8);
        kayit =(Button) findViewById(R.id.button8);
        mAuth = FirebaseAuth.getInstance();

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sifre.getText().toString().equals(tekrar.getText().toString())){
                    createAccount(mail.getText().toString(),sifre.getText().toString());
                }
                else{
                    Toast.makeText(kayit.this,"Girilen şifre değerleri uyuşmuyor",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void  createAccount(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(kayit.this, "Kayıt başarılı.",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(kayit.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(kayit.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
