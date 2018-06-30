package com.example.mac.datachase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText mail,sifre;
    Button girisYap,unuttum;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mail=(EditText) findViewById(R.id.editText4);
        sifre=(EditText) findViewById(R.id.editText5);
        girisYap =(Button) findViewById(R.id.button2);
        unuttum = (Button) findViewById(R.id.button3);

        mAuth=FirebaseAuth.getInstance();

        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(mail.getText().toString(),sifre.getText().toString());
            }
        });

        unuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,unuttum.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            Intent i = new Intent(login.this,MainActivity.class);
            startActivity(i);

        }

    }
    public void signIn(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("girildi", "signInWithEmail:success");
                            Intent i = new Intent(login.this,MainActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("hata", "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
