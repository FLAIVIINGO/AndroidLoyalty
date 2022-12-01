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

public class MainActivity4 extends AppCompatActivity {

    RecyclerView recycler_view2;
    TransactionAdapter2 adapter2;
    List<TransactionModel2> tListModel2 = new ArrayList<>();
    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity4);
        String cid = getIntent().getStringExtra("cid");
        TextView dateView = findViewById(R.id.trans_date);
        TextView transPointsView = findViewById(R.id.trans_points);
        // initialize text view for date and points and spinner eventually

        queue = Volley.newRequestQueue(MainActivity4.this);
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
                // Create array for spinner
                spin_list = createSpinnerList(transaction_list);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spin_list);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tref = adapterView.getSelectedItem().toString();
                        // check for tref in transaction_list and return the TransactionModel
                        TransactionModel t_mod = getTransactionModel(transaction_list, tref);
                        dateView.setText(t_mod.getDate());
                        transPointsView.setText(t_mod.getPoints());
                        populateTable(tref);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        },null);
        queue.add(request);
    }

    public void populateTable(String tref) {

        TextView prod_name = findViewById(R.id.prodName_tv);
        TextView quantity = findViewById(R.id.prodQuantity_tv);
        TextView points = findViewById(R.id.prodPoints_tv);


        queue = Volley.newRequestQueue(MainActivity4.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/TransactionDetails.jsp?tref="+tref;
        if(tref.equals("11")) {
            set11();
        }
        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String str = s.trim();
                if(tref.equals("11")) {
                    set11();
                }
                List<String> input_list = Arrays.asList(str.split("#"));
                for(int i = 0; i < input_list.size(); i++) {
                    List<String> row = Arrays.asList(input_list.get(i).split(","));
                    tListModel2.add(new TransactionModel2(row.get(3),row.get(5),row.get(4)));
                }
                recycler_view2 = findViewById(R.id.t_recycler_view);
                setRecyclerView();
            }
        },null);
        queue.add(request);
    }

    public void set11() {
        return;
    }

    public void setRecyclerView() {
        recycler_view2.setHasFixedSize(true);
        recycler_view2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new TransactionAdapter2(this, tListModel2);
        recycler_view2.setAdapter(adapter2);
    }

    public TransactionModel getTransactionModel(List<TransactionModel> trans_list, String tref) {
        // loop through and find model
        TransactionModel result = null;
        for(int i = 0; i < trans_list.size(); i++) {
            if(trans_list.get(i).getTrans_id().equals(tref)) {
                result = trans_list.get(i);
            }
        }
        return result;
    }

    public List<String> createSpinnerList(List<TransactionModel> trans_list) {
        List<String> tref_list = new ArrayList<>();
        for(int i = 0; i < trans_list.size(); i++) {
            tref_list.add(trans_list.get(i).getTrans_id());
        }

        return tref_list;
    }
}
