package com.example.android.roomwordssample;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView ItemViewName, ItemViewDesc, ItemViewLoc;
    private Item CurrentItem;
    public static final String ITEM_TO_UPDATE = "UPDATE_ITEM";


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
        CurrentItem = item;
    }

    static ItemViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ViewItemActivity.class);
        intent.putExtra(ITEM_TO_UPDATE, CurrentItem);
        ((Activity) view.getContext()).startActivityForResult(intent,2);
    }
}
