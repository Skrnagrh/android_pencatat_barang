package com.example.risolmart;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    EditText etKode, etNama, etHarga, etStok;
    Button btnUpdate,btnDelete;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        context=UpdateActivity.this;

        getSupportActionBar().hide();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        requestQueue = Volley.newRequestQueue(this);

        etKode = findViewById(R.id.etKode);
        etNama = findViewById(R.id.etNama);
        etHarga = findViewById(R.id.etHarga);
        etStok = findViewById(R.id.etStok);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        etKode.setText(getIntent().getStringExtra("datakode"));
        etNama.setText(getIntent().getStringExtra("datanama"));
        etHarga.setText(getIntent().getStringExtra("dataharga"));
        etStok.setText(getIntent().getStringExtra("datastok"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateBarang();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteBarang();
            }
        });

    }

    private void UpdateBarang(){
        String kode = etKode.getText().toString().trim();
        String nama = etNama.getText().toString().trim();
        String harga = etHarga.getText().toString().trim();
        String stok = etStok.getText().toString().trim();

        String link_Update = "http://10.0.2.2:8080/uas/updatebarang.php";

        stringRequest = new StringRequest(Request.Method.POST, link_Update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //If we are getting success from server
                if (response.contains("success")) {
                    Toast.makeText(context, "Data barang berhasil diperbarui!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //Displaying an error message on toast
                    Toast.makeText(context, "Data barang gagal diperbarui!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("kode", kode);
                params.put("nama", nama);
                params.put("harga", harga);
                params.put("stok", stok);
                //...
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //Delete barang
    private void DeleteBarang(){
        String kodebrg = etKode.getText().toString().trim();

        String link_Delete = "http://10.0.2.2:8080/uas/deletebarang.php";

        stringRequest = new StringRequest(Request.Method.POST, link_Delete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //If we are getting success from server
                if (response.contains("success")) {
                    Toast.makeText(context, "Data barang berhasil dihapus!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //Displaying an error message on toast
                    Toast.makeText(context, "Data barang gagal dihapus!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("kode", kodebrg);
                //...
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

//info Aplikasi
    public void Info(View view) {
        Intent intent = new Intent(UpdateActivity.this, InfoActivity.class);
        startActivity(intent);
    }

}
