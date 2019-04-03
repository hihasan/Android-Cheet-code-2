package com.hihasan.volley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context context;
    //private User[] data;

    public DataAdapter(){

    }


    @NonNull
    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataViewHolder dataViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        AppCompatTextView name;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img);
            name=(AppCompatTextView) itemView.findViewById(R.id.name);
        }
    }
}
