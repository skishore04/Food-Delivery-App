package com.example.myapplication.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.Page6;
import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodDomain> popularfood;

    public FoodAdapter(ArrayList<FoodDomain> popularfood) {

        this.popularfood = popularfood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder,int position) {
        holder.title.setText(popularfood.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularfood.get(position).getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularfood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

       holder.addBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent= new Intent(holder.itemView.getContext(), Page6.class);
                intent.putExtra("object",popularfood.get(position));
                holder.itemView.getContext().startActivity(intent);
           }
       });



    }

    @Override
    public int getItemCount() {
        return popularfood.size();
    }
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, fee;
            ImageView pic;
            TextView addBtn;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                fee = itemView.findViewById(R.id.fee);
                pic = itemView.findViewById(R.id.pic);
                addBtn = itemView.findViewById(R.id.addBtn);

            }
        }



}
