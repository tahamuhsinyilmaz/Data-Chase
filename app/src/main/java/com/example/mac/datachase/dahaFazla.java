package com.example.mac.datachase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by mac on 27.02.2018.
 */
public class dahaFazla extends Fragment {
    Button kayit,sifreDegis,cikisYap;
    View vi;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       vi=inflater.inflate(R.layout.daha_layout,container,false);
       kayit=(Button) vi.findViewById(R.id.button5);
       sifreDegis=(Button) vi.findViewById(R.id.button6);
       cikisYap =(Button) vi.findViewById(R.id.button7);
        mAuth=FirebaseAuth.getInstance();
       currentUser = mAuth.getCurrentUser();



       kayit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(currentUser.getEmail().equals("tahamuhsinyilmaz@gmail.com")){
            Intent i = new Intent(vi.getContext(),kayit.class);
            startActivity(i);}
            else{
                   Toast.makeText(vi.getContext(),"Yetkisiz Kullanıcı",Toast.LENGTH_SHORT).show();
               }
           }
       });

       sifreDegis.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(vi.getContext(),sifreDegistir.class);
               startActivity(i);

           }
       });
       cikisYap.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mAuth.signOut();
               Toast.makeText(vi.getContext(),"ÇIKIŞ YAPILDI",Toast.LENGTH_SHORT).show();
               Intent i = new Intent(vi.getContext(),login.class);
               startActivity(i);
           }
       });
        return vi;
    }
}
