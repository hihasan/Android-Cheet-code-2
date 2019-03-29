package com.hihasan.recycleview.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hihasan.recycleview.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {
    public String[] s;

    public TestAdapter(String[]s){
        this.s=s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.activity_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String title= s[i];
        myViewHolder.text.setText(title);

    }

    @Override
    public int getItemCount() {
        return s.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        AppCompatTextView text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.image);
            text=(AppCompatTextView) itemView.findViewById(R.id.text);
        }
    }
}
