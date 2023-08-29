package com.example.risolmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    ArrayList<ModelData> mItems;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=MainActivity.this;

        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(this);

        mRecyclerView = findViewById(R.id.recyclerview);
        mItems = new ArrayList<>();
        mManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new AdapterProcess(context, mItems);
        mRecyclerView.setAdapter(mAdapter);

        loadjson();
    }

    //proses mengambil data
    private void loadjson(){

        final String link_customer = "http://10.0.2.2:8080/uas/tampilbarang.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(link_customer, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                mItems.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        ModelData md = new ModelData();
                        md.setKode(jsonObject.getString("kode"));
                        md.setNama(jsonObject.getString("nama"));
                        md.setHarga(jsonObject.getString("harga"));
                        md.setStok(jsonObject.getString("stok"));

                        mItems.add(md);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void Tambah(View view) {
        Intent intent = new Intent(MainActivity.this, TambahActivity.class);
        startActivity(intent);
    }

    public void Info(View view) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }


}