package com.example.loyaltyfirst;

import android.os.Bundle;
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

public class MainActivity5 extends AppCompatActivity {

    RequestQueue queue = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity5);
        String cid = getIntent().getStringExtra("cid");
        TextView przDesc = findViewById(R.id.prize_desc);
        TextView pntsNeed = findViewById(R.id.points_need);

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
            }
        },null);
        queue.add(request);
    }
}
