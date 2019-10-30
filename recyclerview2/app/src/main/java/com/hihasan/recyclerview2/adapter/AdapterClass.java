package com.hihasan.recyclerview2.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.hihasan.recyclerview2.R;
import com.hihasan.recyclerview2.model.ModelClass;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    public ModelClass modelclass;

    public AdapterClass(ModelClass modelclass){
        this.modelclass=modelclass;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        //return modelclass;
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView msg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById (R.id.msg_txt);
        }
    }
}
