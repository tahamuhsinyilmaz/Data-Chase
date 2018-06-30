package com.example.mac.datachase;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class unuttum extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btn;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unuttum);

        btn =(Button) findViewById(R.id.button4);
        edt=(EditText) findViewById(R.id.editText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(unuttum.this);
                myAlertDialog.setTitle("Mail Gönderilecek");
                myAlertDialog.setMessage("Onaylıyor Musunuz?");
                myAlertDialog.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                            mAuth.sendPasswordResetEmail(edt.getText().toString());
                        Toast.makeText(unuttum.this,"Mail Gönderildi",Toast.LENGTH_SHORT).show();
                        // do something when the OK button is clicked
                    }});
                myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        // do something when the Cancel button is clicked
                    }});
                myAlertDialog.show();

            }
        });

    }
}
