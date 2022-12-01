package com.example.loyaltyfirst;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView = findViewById(R.id.imageView);
        String value = getIntent().getStringExtra("cid");
        final TextView textView = findViewById(R.id.textView4);
        final TextView textView1 = findViewById(R.id.textView6);
        final Button button = findViewById(R.id.button7);
        final Button button2 = findViewById(R.id.button3);
        final Button button3 = findViewById(R.id.button4);

        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        String url = "http://10.0.2.2:8080/loyaltyfirst/Info.jsp?cid="+value;
        //textView.setText(url);
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                List<String> cust_info = Arrays.asList(s.split(","));
                // list will have two values, name & # of points
                String name = cust_info.get(0);
                String num_pts = cust_info.get(1);
                textView.setText(name);
                textView1.setText(num_pts);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("error",volleyError.toString());
            }
        });
        queue.add(request);
        if(value.equals("1")) {
            imageView.setImageResource(R.drawable.cid1);
            //textView.setText("Frank Wyatt");
        }
        else if(value.equals("2")) {
            imageView.setImageResource(R.drawable.cid2);
            //textView.setText("Briley Wyatt");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("cid",value);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                intent.putExtra("cid",value);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                intent.putExtra("cid",value);
                startActivity(intent);
            }
        });
    }
}
