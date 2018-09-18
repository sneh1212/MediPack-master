package com.example.ravi.medipack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class loginandsignup extends AppCompatActivity {

    Button btnsignup;
    TextView txtslogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginandsignup);

btnsignup=findViewById(R.id.btnsignup);
txtslogan=findViewById(R.id.txtSlogan);



    btnsignup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent signup= new Intent(loginandsignup.this,SignUp.class);
            startActivity(signup);

        }
    });


    }
}
