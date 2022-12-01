package com.example.loyaltyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button=findViewById(R.id.button);
        final EditText editText=findViewById(R.id.editText);
        final EditText editText2=findViewById(R.id.editText2);
        TextView textView = findViewById(R.id.textView5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setText("Clicked");
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://10.0.2.2:8080/loyaltyfirst/login?user="+editText.getText().toString()+
                        "&pass="+editText2.getText().toString();
                //textView.setText(url);
                StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String str = s.trim();
                        String cid = str.substring(str.lastIndexOf(":")+1);
                        //textView.setText(cid);
                        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                        intent.putExtra("cid",cid);
                        startActivity(intent);
                    }
                },null);
                queue.add(request);
                /*
                if(editText.getText().toString().equals("andrew")&&
                        editText2.getText().toString().equals("password")) {
                    Toast.makeText(MainActivity.this,"Login Sucessful",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    // intent.putExtra(); Way to move input to next activity
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"InvalidUsername/Password",Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }
}