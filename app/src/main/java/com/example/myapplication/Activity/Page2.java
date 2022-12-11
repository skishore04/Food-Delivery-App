package com.example.myapplication.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Page2 extends AppCompatActivity {

    private EditText  mEmail , mPass,mUser;
    private Button registerbtn;
    private Button signinbtn;
    private FirebaseAuth mAuth;


    LinearLayout linearLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page2);

        mEmail = findViewById(R.id.email_reg);
        mPass = findViewById(R.id.pass_reg);
        mUser= findViewById(R.id.username_reg);
        mAuth= FirebaseAuth.getInstance();

        signinbtn =(Button) findViewById(R.id.button_sign_in);
        signinbtn.setOnClickListener(view -> {
            openPage3();
        });
        registerbtn =(Button) findViewById(R.id.button_register);
        registerbtn.setOnClickListener((View v) -> {
                    createUser();
                }

        );
        linearLayout =(LinearLayout) findViewById(R.id.my_layout);
        animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(800);
        animationDrawable.start();

    }
    public void openPage3() {
        Intent intent = new Intent(this, Page3.class);
        startActivity(intent);
    }
    private void createUser(){
        String email=mEmail.getText().toString().trim();
        String name=mUser.getText().toString().trim();
        String password=mPass.getText().toString().trim();

        if (name.isEmpty()){
            mUser.setError("Name is Required");
            mUser.requestFocus();
            return;
        }
        if (email.isEmpty()){
            mEmail.setError("Email is Required");
            mEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Please enter valid Email");
            mEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            mPass.setError("Password is Required");
            mPass.requestFocus();
            return;
        }
        if (password.length() < 8)
        {
            mPass.setError("Password must be grater than 8 charecters");
            mPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Users user = new Users(name,email);

                            FirebaseDatabase.getInstance().getReference("Uses")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Page2.this,"The User has been registered successfully",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Page2.this , Page3.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Page2.this,"",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(Page2.this,"Failed to register",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            startActivity(new Intent(getApplicationContext(), Page4.class));
            finish();
        }
    }
}