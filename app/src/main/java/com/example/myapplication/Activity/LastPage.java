package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class LastPage extends AppCompatActivity {

    TextView display,name,txt,item,num;
    TextView item1;
    Bundle intentExtras;
   private FirebaseDatabase db = FirebaseDatabase.getInstance();
   private DatabaseReference root =db.getReference().child("Orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
        TextView txt;
        Button k;
name=findViewById(R.id.NameTxt);
        display =findViewById(R.id.TotalBill);
        item = findViewById(R.id.item1);
        num=findViewById(R.id.num);
        intentExtras =getIntent().getExtras();
        if(intentExtras!=null)
        {
            String myText = intentExtras.getString("myText");
            display.setText(myText);
            String itemname = intentExtras.getString("item");
            item.setText(itemname);
            String qua = intentExtras.getString("num");
            num.setText(qua);
        }
        txt=findViewById(R.id.textView);
        k=findViewById(R.id.button);


        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random=new Random();
                int val = random.nextInt(1000);
                txt.setText(Integer.toString(val));
                k.setVisibility(View.INVISIBLE);

                String bill= display.getText().toString();
               // root.child(" bill").setValue(bill);
                String token = txt.getText().toString();
              //  root.child("Token").setValue(token);

                String Item = item.getText().toString();
                String Quat = num.getText().toString();

                HashMap<String,String> orderMap = new HashMap<>();
                orderMap.put("Token",token);
                orderMap.put("Bill",bill);
                orderMap.put("Item",Item);
                orderMap.put("Quantity",Quat);

                root.push().setValue(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LastPage.this, "Order paced Successfully", Toast.LENGTH_SHORT).show();

                    }
                });






               /* FirebaseDatabase.getInstance().getReference("Orders")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())

                .child("01").setValue(token);
                FirebaseDatabase.getInstance().getReference("Orders")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("02").setValue(bill).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LastPage.this, "Order paced Successfully", Toast.LENGTH_SHORT).show();

                    }
                });*/


            }
        });

        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LastPage.this, Page4.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });



    }
}