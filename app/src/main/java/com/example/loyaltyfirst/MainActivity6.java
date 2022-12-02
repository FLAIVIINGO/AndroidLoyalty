package com.example.loyaltyfirst;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {

    RequestQueue queue = null;
    String family_id;
    String certain_cid;
    String certain_num_points;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity6);
        final Button button = findViewById(R.id.button11);
        String cid = getIntent().getStringExtra("cid");

        queue = Volley.newRequestQueue(MainActivity6.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/Transactions.jsp?cid="+cid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String str = s.trim();
                List<String> tref_list = Arrays.asList(str.split("#"));
                List<String> trans_id_list = new ArrayList<>();
                for(int i = 0; i < tref_list.size(); i++) {
                    List<String> row = Arrays.asList(tref_list.get(i).split(","));
                    trans_id_list.add(row.get(0));
                }
                Spinner spinner = findViewById(R.id.trans6_spin);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,trans_id_list);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String tref = adapterView.getSelectedItem().toString();
                        String url = "http://10.0.2.2:8080/loyaltyfirst/SupportFamilyIncrease.jsp?cid="+cid+"&tref="+tref;
                        populateTextFields(url);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        },null);
        queue.add(request);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queue = Volley.newRequestQueue(MainActivity6.this);
                certain_cid = cid;
                String url = "http://10.0.2.2:8080/loyaltyfirst/FamilyIncrease.jsp?fid="+family_id+"&cid="+cid+"&npoints="+certain_num_points;
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        familyIncrease();
                    }
                },null);
                queue.add(request);
            }
        });
    }

    public void familyIncrease() {
        queue = Volley.newRequestQueue(MainActivity6.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/FamilyIncrease.jsp?fid="+family_id+"&cid="+certain_cid+"&npoints="+certain_num_points;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity6.this,"Points "+certain_num_points+ "to family " + family_id, Toast.LENGTH_LONG).show();
            }
        },null);
        queue.add(request);
    }

    public void populateTextFields(String url) {


        queue = Volley.newRequestQueue(MainActivity6.this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                TextView txn_points = findViewById(R.id.txn_points);
                TextView fam_id = findViewById(R.id.family_id);
                TextView fam_percent = findViewById(R.id.fam_percent);
                String str = s.trim();
                List<String> row = Arrays.asList(str.split(","));
                TransactionModel4 mod4 = new TransactionModel4(row.get(0),row.get(1),row.get(2));
                txn_points.setText(mod4.getTotal_points());
                fam_id.setText(mod4.getFamily_id());
                fam_percent.setText("30");
                family_id = mod4.getFamily_id();
                certain_num_points = mod4.getPercent_points();
            }
        },null);
        queue.add(request);
    }
}
