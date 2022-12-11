package com.example.myapplication.Activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Domain.CategoryDomain;
import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CategoryAdapter;
import com.example.myapplication.adapters.FoodAdapter;

import java.util.ArrayList;

public class Page5 extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewFood;
    private TextView menu;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);


        menu =(TextView) findViewById(R.id.menu_text);
        animationDrawable = (AnimationDrawable) menu.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

        recyclerViewCategory();
        recyclerViewFood();

        TextView cartBtn= (TextView) findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Page5.this, CartListActivity.class);
                startActivity(intent);
            }
        });

    }
    private void recyclerViewCategory()
    {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList= findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Breakfast", "img_break"));
        category.add(new CategoryDomain("Lunch","img_rice"));
        category.add(new CategoryDomain("Drinks","img_drink"));
        category.add(new CategoryDomain("Snacks","img_snacks"));

         adapter=new CategoryAdapter(category);
         recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewFood()
    {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewFood=findViewById(R.id.recyclerView2);
        recyclerViewFood.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList=new ArrayList<>();
        foodList.add(new FoodDomain("Dosa","img_dosa","ntg",30.00));
        foodList.add(new FoodDomain("Idly","img_id","ntg",30.00));
        foodList.add(new FoodDomain("Mysore Bajji","img_bajji","ntg",30.00));
        foodList.add(new FoodDomain("Uttapam","img_utt","ntg",40.00));
        foodList.add(new FoodDomain("Vada","img_vada","ntg",35.00));
        foodList.add(new FoodDomain("Paratha","img_par","ntg",40.00));
        foodList.add(new FoodDomain("Veg Biryani","img_vegbir","ntg",80.00));
        foodList.add(new FoodDomain("Veg Manchurian","img_vegman","ntg",70.00));
        foodList.add(new FoodDomain("Chicken Biryani","img_chibir","ntg",120.00));
        foodList.add(new FoodDomain("Egg Fired Rice","img_eggbir","ntg",70.00));
        foodList.add(new FoodDomain("Chicken 65","img_chiman","ntg",80.00));
        foodList.add(new FoodDomain("Meals","img_meal","ntg",60.00));

        adapter2=new FoodAdapter(foodList);
        recyclerViewFood.setAdapter(adapter2);



    }
}