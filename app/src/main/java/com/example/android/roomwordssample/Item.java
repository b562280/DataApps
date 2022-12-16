package com.example.android.roomwordssample;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "item_table")


public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    int ID;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "location")
    private String mLocation;

    @NonNull
    @ColumnInfo(name = "description")
    private String mDescription;

    public Item(@NonNull String mName, @NonNull String mLocation, @NonNull String mDescription) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mDescription = mDescription;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getLocation() {
        return mLocation;
    }

    @NonNull
    public String getDescription() {
        return mDescription;
    }


    public int getID() {return ID;}

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setLocation(@NonNull String mLocation) {
        this.mLocation = mLocation;
    }

    public void setDescription(@NonNull String mDescription) {
        this.mDescription = mDescription;
    }
}
