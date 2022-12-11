package com.example.adminapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context , ArrayList<Model> mList){
        this.mList = mList;
        this.context= context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
         return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model = mList.get(position);
        holder.Token.setText(model.getToken());

       holder.Item.setText(model.getItem());
        holder.Quantity.setText(model.getQuantity());
        holder.Bill.setText(model.getBill());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Token,Bill,Item,Quantity;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Token = itemView.findViewById(R.id.token);
            Bill = itemView.findViewById(R.id.bill);
           Item= itemView.findViewById(R.id.itemname);
           Quantity= itemView.findViewById(R.id.quantity);
        }

    }


}
