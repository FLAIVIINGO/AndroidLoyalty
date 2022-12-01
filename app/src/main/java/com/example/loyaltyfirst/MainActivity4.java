package com.example.loyaltyfirst;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity4);
        String cid = getIntent().getStringExtra("cid");
        TextView dateView = findViewById(R.id.trans_date);
        TextView transView = findViewById(R.id.trans_points);
        // initialize text view for date and points and spinner eventually

        RequestQueue queue = Volley.newRequestQueue(MainActivity4.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/Transactions.jsp?cid="+cid;
        StringRequest request= new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                List<TransactionModel> transaction_list = new ArrayList<>();
                String str = s.trim();
                List<String> input_list = Arrays.asList(str.split("#"));
                for(int i = 0; i < input_list.size(); i++) {
                    List<String> row = Arrays.asList(input_list.get(i).split(","));
                    transaction_list.add(new TransactionModel(row.get(0),row.get(1),row.get(2),row.get(3)));
                }
                // with this list we can populate a spinner and display date and points of selected
                Spinner spinner = findViewById(R.id.tref_spin);
                List<String> spin_list = new ArrayList<>();
                for(int i = 0; i < transaction_list.size(); i++) {
                    spin_list.add(transaction_list.get(i).getTrans_id());
                }
                /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spin_list);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });*/
            }
        },null);
    }
}
