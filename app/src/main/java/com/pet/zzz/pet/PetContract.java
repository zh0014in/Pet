package com.pet.zzz.pet;

import android.provider.BaseColumns;

/**
 * Created by zzz on 2016/7/30.
 */
public final class PetContract {
    public PetContract(){}
    public  static abstract class PetEntry implements BaseColumns{
        public static final String TABLE_NAME = "pet";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_NAME = "name";
    }

}
