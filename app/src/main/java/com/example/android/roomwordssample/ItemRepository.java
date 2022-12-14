package com.example.android.roomwordssample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private ItemDao mItemDao;
    private LiveData<List<Item>> mAllItems;

    // Note that in order to unit test the ItemRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ItemRepository(Application application) {
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAlphabetizedItems();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Item>> getAllItems() {
        return mAllItems;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Item item) {
        ItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.insert(item);
        });
    }

    void deleteAll() {
        ItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.deleteAll();
        });
    }

    void update(Item newItem) {
        ItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.update(newItem);
        });
    }
}
