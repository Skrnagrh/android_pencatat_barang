package com.example.risolmart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProcess extends RecyclerView.Adapter<AdapterProcess.ViewProcessHolder> {

    Context context;
    private ArrayList<ModelData> item; //memanggil modelData

    public AdapterProcess(Context context, ArrayList<ModelData> item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public ViewProcessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false); //memanggil layout list recyclerview
        ViewProcessHolder processHolder = new ViewProcessHolder(view);
        return processHolder;
    }


    @Override
    public void onBindViewHolder(ViewProcessHolder holder, int position) {

        final ModelData data = item.get(position);
        final int panjang = data.getNama().length();
        if (panjang >= 25){
            final String mIsi = data.getNama().substring(0,25);
            holder.txtNama.setText(mIsi.concat("..."));
        } else {
            final String mIsi = data.getNama();
            holder.txtNama.setText(mIsi);
        }
        holder.txtKode.setText(data.getKode());
        holder.txtHarga.setText(data.getHarga());
        holder.txtStok.setText(data.getStok());

        holder.viewBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("datakode",data.getKode());
                intent.putExtra("datanama",data.getNama());
                intent.putExtra("dataharga",data.getHarga());
                intent.putExtra("datastok",data.getStok());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewProcessHolder extends RecyclerView.ViewHolder {

        TextView txtKode;
        TextView txtNama;
        TextView txtHarga;
        TextView txtStok;
        CardView viewBarang;

        public ViewProcessHolder(View itemView) {
            super(itemView);

            txtKode = itemView.findViewById(R.id.txtKode);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            txtStok = itemView.findViewById(R.id.txtStok);
            viewBarang = itemView.findViewById(R.id.viewBarang);

        }
    }
}
