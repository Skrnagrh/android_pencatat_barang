package com.example.risolmart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TambahActivity extends AppCompatActivity {

    Context context;
    EditText etKodeBrg, etNamaBrg, etHargaBrg, etStokBrg;
    Button btnSimpan;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        context=TambahActivity.this;

        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(this);

        etKodeBrg = findViewById(R.id.etKode);
        etNamaBrg = findViewById(R.id.etNama);
        etHargaBrg = findViewById(R.id.etHarga);
        etStokBrg = findViewById(R.id.etStok);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanBarang();
            }
        });

    }

//    Simpan Data Barang
    private void simpanBarang(){
        String KodeBrg = etKodeBrg.getText().toString().trim();
        String NamaBrg = etNamaBrg.getText().toString().trim();
        String HargaBrg = etHargaBrg.getText().toString().trim();
        String StokBrg = etStokBrg.getText().toString().trim();

        String link_Simpan = "http://10.0.2.2:8080/uas/simpanbarang.php";

        stringRequest = new StringRequest(Request.Method.POST, link_Simpan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //If we are getting success from server
                if (response.contains("success")) {
                    Toast.makeText(context, "Data barang berhasil disimpan!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //Displaying an error message on toast
                    Toast.makeText(context, "Data barang gagal disimpan!", Toast.LENGTH_LONG).show();
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
                params.put("kode", KodeBrg);
                params.put("nama", NamaBrg);
                params.put("harga", HargaBrg);
                params.put("stok", StokBrg);
                //...
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void Info(View view) {
        Intent intent = new Intent(TambahActivity.this, InfoActivity.class);
        startActivity(intent);
    }


}