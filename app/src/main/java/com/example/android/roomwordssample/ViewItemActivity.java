package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class ViewItemActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_NEW_ITEM = "com.example.android.itemListSQL.REPLY.NEW_ITEM";
    private EditText mEditItemName, mEditItemDesc, mEditItemLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        setTitle("Edit Item");

        fieldsInEditText();

        final Button button = findViewById(R.id.button_save);

        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (checkIfTextFieldsEmpty()) {
                setResult(RESULT_CANCELED, replyIntent);
            }
            else {
                String Name = getStringOfTextView(mEditItemName);
                String Location = getStringOfTextView(mEditItemLoc);
                String Description = getStringOfTextView(mEditItemDesc);

                Item newItem = new Item(Name,Location,Description);

                replyIntent.putExtra( EXTRA_REPLY_NEW_ITEM, newItem);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }

    private void fieldsInEditText() {
        mEditItemName = findViewById(R.id.edit_Name);
        mEditItemDesc = findViewById(R.id.edit_Description);
        mEditItemLoc = findViewById(R.id.edit_Location);

        Intent intent = getIntent();
        Item oldItem = (Item) intent.getSerializableExtra("test");

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