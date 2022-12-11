package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Page4 extends AppCompatActivity {
    private Button verifymail;
    private TextView verifyMsg;
    private Button signout;
    private FirebaseAuth mAuth;
    private Button order;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        verifymail= (Button) findViewById(R.id.verify);
        verifyMsg= (TextView) findViewById(R.id.addverify);
        mAuth=FirebaseAuth.getInstance();
        order =(Button) findViewById(R.id.button_order);
        welcome = (TextView) findViewById(R.id.wel);

        if(!mAuth.getCurrentUser().isEmailVerified()){
            verifymail.setVisibility(View.VISIBLE);
            verifyMsg.setVisibility(View.VISIBLE);
        }
        verifymail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        for (int i=0; i < 4; i++) {
                           Toast toast= Toast.makeText(Page4.this, "Email verification is sent.Sign out first.Check your email,click on verification link and sign in again", Toast.LENGTH_LONG);

                            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 110, 0);
                            toast.show();
                        }
                        verifymail.setVisibility(View.GONE);
                        verifyMsg.setVisibility(View.GONE);
                    }
                });
            }
        });

        if(mAuth.getCurrentUser().isEmailVerified()){
            order.setVisibility(View.VISIBLE);
            welcome.setVisibility(View.VISIBLE);
        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPage5();
            }
        });

        signout= (Button) findViewById(R.id.button_sign_out);
        signout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Page4.this , Page3.class));
            finish();
        });

    }
    public void openPage5() {
        Intent intent = new Intent(this, Page5.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}