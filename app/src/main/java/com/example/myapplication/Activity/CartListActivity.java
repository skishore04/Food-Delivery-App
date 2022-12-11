package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CartListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, emptyTxt;
    TextView TotalBill;
    public TextView item111,itemnum,item22;
    private double tax;
    private ScrollView scrollView;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root =db.getReference().child("Orders");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart = new ManagementCart(this);

        FoodDomain foodpage =getIntent().getParcelableExtra("Menu");

        initView();
        initList();
        CalculateCart();

        item111= (TextView) findViewById(R.id.item11);
        itemnum= (TextView) findViewById(R.id.itemnum);
        item22 = (TextView) findViewById(R.id.item22);




        Button home= findViewById(R.id.buttonhome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this, Page4.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                finish();
            }
        });
        TextView TotalBill =findViewById(R.id.TotalBill);
        TextView buttonDone = findViewById(R.id.check);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Output = TotalBill.getText().toString();
                Intent intent = new Intent(CartListActivity.this, LastPage.class);
                intent.putExtra("myText",Output);
                String Output1 = item111.getText().toString();
                intent.putExtra("item",Output1);
                String Output2 = itemnum.getText().toString();
                intent.putExtra("num",Output2);

                startActivity(intent);

            }
        });
        Intent intent = getIntent();
        String item = intent.getStringExtra("item1");
        String num = intent.getStringExtra("itemnum");

        item111.setText(item);
        itemnum.setText(num);

  /*   if (item111.getText().toString().equals(""))
     {
         confirmitem.setVisibility(View.INVISIBLE);

     }
     confirmitem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             String Item= item111.getText().toString();
             String num= itemnum.getText().toString();
            HashMap<String,String> orderMap = new HashMap<>();
             orderMap.put("Item ",Item);
             orderMap.put("Quantity",num);
             root.push().setValue(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     Toast.makeText(CartListActivity.this, "Item confirmed Successfully", Toast.LENGTH_SHORT).show();

                 }
             });

         }
     });*/




    }


   /* private void buttonDone(FoodDomain foodpage) {
        Intent i = new Intent(CartListActivity.this, Page4.class);
        i.putExtra("menu", foodpage);
        startActivityForResult(i, 1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1000) {
            setResult(Activity.RESULT_OK);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }*/

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        TotalBill = findViewById(R.id.TotalBill);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("Rs." + itemTotal);
        taxTxt.setText("Rs." + tax);
        deliveryTxt.setText("Rs." + delivery);
        TotalBill.setText("Rs." + total);
    }

}