package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<NewsApp> arrayList = new ArrayList<>();
    RecyclerView recycleview;
     NewsAppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleview = findViewById(R.id.recycleview);


        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://newsapi.org/v2/top-headlines?country=in&apiKey=bd53ac0305c842a8a4c365c802a52f15";

        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG","Response :" + response.toString());

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arr = jsonObject.getJSONArray("articles");

                   for (int i = 0; i < arr.length(); i++) {
                       arrayList.add(new NewsApp( arr.getJSONObject(i).getString("author"),
                               arr.getJSONObject(i).getString("title"),
                               arr.getJSONObject(i).getString("content"),
                               arr.getJSONObject(i).getString("url"),
                               arr.getJSONObject(i).getString("urlToImage"),
                               arr.getJSONObject(i).getString("publishedAt")));
                   }

                    }
                catch (JSONException ex)
                {
                    ex.printStackTrace();
                }
                adapter =new NewsAppAdapter(arrayList);
                recycleview.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.i("TAG","Error :" + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("request","value");
                return  params;
            }
        };
        mRequestQueue.add(mStringRequest);

        recycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                arrayList.remove(viewHolder.getAdapterPosition());
                adapter=new NewsAppAdapter(arrayList);
                recycleview.setAdapter(adapter);

            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recycleview);



    }
}