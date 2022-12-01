package com.example.loyaltyfirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context context;
    List<TransactionModel> transaction_list;

    public TransactionAdapter(Context context, List<TransactionModel> payment_list) {
        this.context = context;
        this.transaction_list = payment_list;
    }
    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        if(transaction_list != null && transaction_list.size() > 0) {
            TransactionModel model = transaction_list.get(position);
            holder.trans_tv.setText(model.getTrans_id());
            holder.date_tv.setText(model.getDate());
            holder.points_tv.setText(model.getPoints());
            holder.total_tv.setText(model.getTotal());
        }
    }

    @Override
    public int getItemCount() {
        return transaction_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trans_tv, date_tv, points_tv, total_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            trans_tv = itemView.findViewById(R.id.trans_tv);
            date_tv = itemView.findViewById(R.id.date_tv);
            points_tv = itemView.findViewById(R.id.points_tv);
            total_tv = itemView.findViewById(R.id.total_tv);
        }
    }
}
