package com.example.loyaltyfirst;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity3 extends AppCompatActivity{

    RecyclerView recycler_view;
    TransactionAdapter adapter;
    List<TransactionModel> transaction_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String cid = getIntent().getStringExtra("cid");
        RequestQueue queue = Volley.newRequestQueue(MainActivity3.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/Transactions.jsp?cid="+cid;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String str = s.trim();

                List<String> input_list = Arrays.asList(str.split("#"));
                for(int i = 0; i < input_list.size(); i++) {
                    List<String> row = Arrays.asList(input_list.get(i).split(","));
                    transaction_list.add(new TransactionModel(row.get(0),row.get(1),row.get(2),row.get(3)));
                }
                recycler_view = findViewById(R.id.recycler_view);
                setRecyclerView();

            }
        }, null);
        queue.add(request);
    }

    public void setRecyclerView() {
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter(this,transaction_list);
        recycler_view.setAdapter(adapter);
    }
}
