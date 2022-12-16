package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_NEW_ITEM = "NEW_ITEM";
    public static final String EXTRA_REPLY_ITEM = "REPLY.ITEM";

    public static final int NEW_Item_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_Item_ACTIVITY_REQUEST_CODE = 2;

    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ItemListAdapter adapter = new ItemListAdapter(new ItemListAdapter.ItemDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mMainActivityViewModel.getAllItems().observe(this, Items -> {
            // Update the cached copy of the Items in the adapter.
            adapter.submitList(Items);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
//        mItemViewModel.deleteAll(); Delete all data

//        createMultipleItems(10);

        Intent intent = new Intent(this, NewItemActivity.class);
        startActivityForResult(intent, NEW_Item_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(resultCode);

        int ErrCode = data.getExtras().getInt("ErrorCode");

        Item item = (Item) data.getSerializableExtra(EXTRA_REPLY_ITEM);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case NEW_Item_ACTIVITY_REQUEST_CODE:
                    mMainActivityViewModel.insert(item);
                    break;

                case UPDATE_Item_ACTIVITY_REQUEST_CODE:
                    mMainActivityViewModel.update(item);
                    break;
            }
        }
        else {
            CheckErrCode(ErrCode);
        }

    }

    private void CheckErrCode(int ErrCode) {
        switch (ErrCode) {
            case 1:
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
                break;

            case 2:
                Toast.makeText(
                        getApplicationContext(),
                        "Save Button Was not pressed",
                        Toast.LENGTH_LONG).show();
                break;

            case 3:
                Toast.makeText(
                        getApplicationContext(),
                        "Add Button Was not pressed",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void createMultipleItems(int amountToCreate) {
        for (int i = 0; i < amountToCreate; i++) {
            String Input = String.valueOf(i);
            Item item = new Item("Name "+ Input,
                    "Location " + Input,
                    "Description " + Input);
            mMainActivityViewModel.insert(item);
        }
    }
}


