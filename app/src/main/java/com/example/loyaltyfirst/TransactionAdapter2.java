package com.example.loyaltyfirst;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter2 extends RecyclerView.Adapter<TransactionAdapter2.ViewHolder> {
    Context context;
    List<TransactionModel2> prod_list;

    public TransactionAdapter2(Context context, List<TransactionModel2> prod_list) {
        this.context = context;
        this.prod_list = prod_list;
    }

    @NonNull
    @Override
    public TransactionAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter2.ViewHolder holder, int position) {
        if(prod_list != null && prod_list.size() > 0) {
            TransactionModel2 model2 = prod_list.get(position);
            holder.prodName_tv.setText(model2.getP_name());
            holder.prodQuantity_tv.setText(model2.getP_quantity());
            holder.prodPoints_tv.setText(model2.getP_points());
        }
    }

    @Override
    public int getItemCount() {
        return prod_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prodName_tv,prodQuantity_tv,prodPoints_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            prodName_tv = itemView.findViewById(R.id.prodName_tv);
            prodQuantity_tv = itemView.findViewById(R.id.prodQuantity_tv);
            prodPoints_tv = itemView.findViewById(R.id.prodPoints_tv);
        }
    }
}
