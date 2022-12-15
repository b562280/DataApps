package com.example.android.roomwordssample;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView ItemViewName;
    private final TextView ItemViewDesc;
    private final TextView ItemViewLoc;
    private Item itemOfPosition;


    private ItemViewHolder(View itemView) {
        super(itemView);
        ItemViewName = itemView.findViewById(R.id.textView_Name);
        ItemViewDesc = itemView.findViewById(R.id.textView_Description);
        ItemViewLoc = itemView.findViewById(R.id.textView_location);
        itemView.setOnClickListener(this::onClick);
    }

    public void bind(Item item) {
        ItemViewDesc.setText(item.getDescription());
        ItemViewName.setText(item.getName());
        ItemViewLoc.setText(item.getLocation());
        itemOfPosition = item;
    }

    static ItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ViewItemActivity.class);
        intent.putExtra("test", itemOfPosition);
        ((Activity) view.getContext()).startActivityForResult(intent,1102);

    }
}
