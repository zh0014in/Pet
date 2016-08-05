package com.pet.zzz.pet;

import android.provider.BaseColumns;
import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by zzz on 2016/7/30.
 */
public final class EventContract {
    public EventContract(){}
    public static abstract class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "event";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_PET_ID = "petid";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_LENGTH_BACK = "length_back";
        public static final String COLUMN_NAME_LENGTH_FRONT = "length_front";
    }

}
