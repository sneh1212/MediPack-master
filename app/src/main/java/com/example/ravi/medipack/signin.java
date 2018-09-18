package com.example.ravi.medipack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ravi.medipack.Common.Common;
import com.example.ravi.medipack.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final EditText edtPhone,edtPassword;
        Button btnsignIn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        edtPassword=(MaterialEditText)findViewById(R.id.edtpassword);
        edtPhone=(MaterialEditText)findViewById(R.id.edtphone);
        btnsignIn=(Button)findViewById(R.id.btnsignin);

        // init firebase
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("user");
        btnsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDailog= new ProgressDialog(signin.this);
                mDailog.setMessage("please wait....");
                mDailog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check if user not exist in database
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {

                            //get user information

                            mDailog.dismiss();

                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {

                                {
                                    Intent homeIntent= new Intent(signin.this,Home.class);
                                    


                                }
                            } else {


                                Toast.makeText(signin.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                            }

                        }

                        else
                        {
                             mDailog.dismiss();
                            Toast.makeText(signin.this, "User not exist Database", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }
}
