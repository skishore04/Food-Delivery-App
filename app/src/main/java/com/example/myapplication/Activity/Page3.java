package com.example.myapplication.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Page3 extends AppCompatActivity {
    private EditText mEmail, mPass;
    private Button login;
    private Button signup;
    private Button forgot;
    FirebaseAuth mAuth;

    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEmail = (EditText)findViewById(R.id.email_in);
        mPass = (EditText)findViewById(R.id.pass_in);
        mAuth = FirebaseAuth.getInstance();

        reset_alert= new AlertDialog.Builder(this);
        inflater= this.getLayoutInflater();

        setContentView(R.layout.activity_page3);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.my_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(800);
        animationDrawable.start();
        signup = (Button) findViewById(R.id.button_sign_up);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                openPage2();
            }
        });
        login =(Button) findViewById(R.id.button_login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        forgot = (Button) findViewById(R.id.forgotpass);
        forgot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = inflater.inflate(R.layout.reset_pop,null);

                reset_alert.setTitle("Forgot Password?")
                        .setMessage("Enter your Email Address to get Password reset link.")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText email= view1.findViewById(R.id.resetid);
                                if(email.getText().toString().isEmpty()){
                                    email.setError("Required Field");
                                    return;
                                }
                                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Page3.this,"Reset Email Sent",Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Page3.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel",null)
                        .setView(view1)
                        .create().show();
            }
        });
    }

    public void openPage2() {
        Intent intent = new Intent(Page3.this, Page2.class);
        startActivity(intent);
    }


    private void loginUser() {
        mEmail = (EditText)findViewById(R.id.email_in);
        mPass = (EditText)findViewById(R.id.pass_in);
        String email;
        email = mEmail.getText().toString();
        String pass;
        pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Page3.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Page3.this, Page4.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Page3.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else {
                mPass.setError("Empty Fields Are not Allowed");
            }
        } else if (email.isEmpty()) {
            mEmail.setError("Empty Fields Are not Allowed");
        } else {
            mEmail.setError("Pleas Enter Correct Email");
        }
    }
}