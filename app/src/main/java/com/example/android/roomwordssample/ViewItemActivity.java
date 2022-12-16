package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class ViewItemActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_ITEM = "REPLY.ITEM";
    public static final String ITEM_TO_UPDATE = "UPDATE_ITEM";
    private EditText mEditItemName, mEditItemDesc, mEditItemLoc;
    private Item oldItem;
    boolean Clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        setTitle("Edit Item");

        Intent intent = getIntent();
        oldItem = (Item) intent.getSerializableExtra(ITEM_TO_UPDATE);

        fieldsInEditText();

        final Button button = findViewById(R.id.button_save);

        Intent replyIntent = new Intent();
        setResult(RESULT_CANCELED, replyIntent);


        button.setOnClickListener(view -> {
            Clicked = true;
            if (checkIfTextFieldsEmpty()) {
                replyIntent.putExtra("ErrorCode", 1);
                setResult(RESULT_CANCELED, replyIntent);
            }
            else {
                setNewItemVariables();
                replyIntent.putExtra(EXTRA_REPLY_ITEM, oldItem);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

        if (!Clicked) {
            replyIntent.putExtra("ErrorCode", 2);
        }
    }

    private void setNewItemVariables() {
        String Name = getStringOfTextView(mEditItemName);
        String Location = getStringOfTextView(mEditItemLoc);
        String Description = getStringOfTextView(mEditItemDesc);

        oldItem.setName(Name);
        oldItem.setDescription(Description);
        oldItem.setLocation(Location);
    }

    private void fieldsInEditText() {
        mEditItemName = findViewById(R.id.edit_Name);
        mEditItemDesc = findViewById(R.id.edit_Description);
        mEditItemLoc = findViewById(R.id.edit_Location);

        mEditItemName.setText(oldItem.getName());
        mEditItemDesc.setText(oldItem.getDescription());
        mEditItemLoc.setText(oldItem.getLocation());
    }

    private String getStringOfTextView(EditText editText) {
        return editText.getText().toString();
    }

    private boolean checkIfTextFieldsEmpty() {
        return (
                TextUtils.isEmpty(mEditItemName.getText())
                        || TextUtils.isEmpty(mEditItemDesc.getText())
                        || TextUtils.isEmpty(mEditItemLoc.getText())
        );
    }
}