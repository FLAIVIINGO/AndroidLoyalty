package com.example.loyaltyfirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter3 extends RecyclerView.Adapter<TransactionAdapter3.ViewHolder> {
    Context context;
    List<TransactionModel3> prod_list;

    public TransactionAdapter3(Context context, List<TransactionModel3> prod_list) {
        this.context = context;
        this.prod_list = prod_list;
    }

    @NonNull
    @Override
    public TransactionAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout3,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter3.ViewHolder holder, int position) {
        if(prod_list != null && prod_list.size() > 0) {
            TransactionModel3 model3 = prod_list.get(position);
            holder.redeem_tv.setText(model3.getRedeem_date());
            holder.exchange_tv.setText(model3.getEx_center());
        }
    }

    @Override
    public int getItemCount() {
        return prod_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView redeem_tv, exchange_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            redeem_tv = itemView.findViewById(R.id.redeem_tv);
            exchange_tv = itemView.findViewById(R.id.exchange_tv);
        }
    }
}
