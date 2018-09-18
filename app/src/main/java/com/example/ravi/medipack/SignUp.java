package com.example.ravi.medipack;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ravi.medipack.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone,edtName,edtPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName= (MaterialEditText)findViewById(R.id.edtName);
        edtPassword =(MaterialEditText)findViewById(R.id.edtpassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtphone);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);

        // init firebase
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("user");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDailog= new ProgressDialog(SignUp.this);
                mDailog.setMessage("please wait....");
                mDailog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check if user already user phone
                         if (dataSnapshot.child(edtPhone.getText().toString()).exists())
                         {
                             mDailog.dismiss();
                             Toast.makeText(SignUp.this, "Phone number alredy registerd", Toast.LENGTH_SHORT).show();
                             
                         }
                         else
                         {
                             mDailog.dismiss();
                             User user=new User(edtName.getText().toString(),edtPassword.getText().toString());
                             table_user.child(edtPhone.getText().toString()).setValue(user);
                             Toast.makeText(SignUp.this, "sign up successfully   !  ", Toast.LENGTH_SHORT).show();
                             finish();

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
