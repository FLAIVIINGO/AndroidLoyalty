package com.example.loyaltyfirst;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    RequestQueue queue = null;
    List<TransactionModel3> transaction_list3 = new ArrayList<>();
    RecyclerView recycler_view3;
    TransactionAdapter3 adapter3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity5);
        String cid = getIntent().getStringExtra("cid");


        queue = Volley.newRequestQueue(MainActivity5.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/PrizeIds.jsp?cid="+cid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String str = s.trim();
                List<String> prize_id_list = Arrays.asList(str.split("#"));
                Spinner spinner = findViewById(R.id.prize_spin);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,prize_id_list);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String prz_id = adapterView.getSelectedItem().toString();
                        String url = "http://10.0.2.2:8080/loyaltyfirst/RedemptionDetails.jsp?prizeid="+prz_id+"&cid="+cid;
                        populateTextFields(url);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        },null);
        queue.add(request);
    }

    public void populateTextFields(String url) {
        TextView przDesc = findViewById(R.id.prize_desc);
        TextView pntsNeed = findViewById(R.id.points_need);

        queue = Volley.newRequestQueue(MainActivity5.this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String str = s.trim();
                List<String> input_list = Arrays.asList(str.split("#"));
                transaction_list3.clear();
                for(int i = 0; i < input_list.size(); i++) {
                    List<String> row = Arrays.asList(input_list.get(i).split(","));
                    transaction_list3.add(new TransactionModel3(row.get(0),row.get(1),row.get(2),row.get(3)));
                }
                przDesc.setText(transaction_list3.get(0).getPrize_desc());
                pntsNeed.setText(transaction_list3.get(0).getPoints_needed());
                //populate the table
                populateTable();
            }
        },null);
        queue.add(request);
    }

    public void populateTable() {
        recycler_view3 = findViewById(R.id.prize_recycler);
        setRecyclerView();
    }

    public void setRecyclerView() {
        recycler_view3.setHasFixedSize(true);
        recycler_view3.setLayoutManager(new LinearLayoutManager(this));
        adapter3 = new TransactionAdapter3(this, transaction_list3);
        recycler_view3.setAdapter(adapter3);
    }
}
