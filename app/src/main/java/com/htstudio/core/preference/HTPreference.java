package com.htstudio.core.preference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HTPreference {


    private DBHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public HTPreference(Context context) {
        this.context = context;
        helper = DBHelper.getInstance(context);
    }


    public boolean intToBoolean(int i) {
        return i == 1;
    }

    public boolean saveKey(String key, Object value) {
        boolean valid = value instanceof String || value instanceof Integer
                || value instanceof Boolean || value instanceof Long || value instanceof Float;
        if (!valid) throw new RuntimeException("Wrong data type");
        db = helper.getWritableDatabase();
        String[] argument = new String[]{key};
        Cursor checkExitKey = db.query(DBHelper.TB_NAME, null,
                DBHelper.COL_KEY + "= ?", argument, null, null, null);
        if (checkExitKey == null) return false;

        ContentValues valuesData = new ContentValues();

        if (value instanceof String) {
            valuesData.put(DBHelper.COL_DATA, (String) value);
        } else if (value instanceof Integer) {
            valuesData.put(DBHelper.COL_DATA, (Integer) value);
        } else if (value instanceof Long) {
            valuesData.put(DBHelper.COL_DATA, (Long) value);
        } else if (value instanceof Boolean) {
            valuesData.put(DBHelper.COL_DATA, (Boolean) value);
        } else {
            valuesData.put(DBHelper.COL_DATA, (Float) value);
        }

        if (checkExitKey.getCount() <= 0) {
            //create new
            checkExitKey.close();
            valuesData.put(DBHelper.COL_KEY, key);
            valuesData.put(DBHelper.COL_DATA, String.valueOf(value));
            return db.insert(DBHelper.TB_NAME, null, valuesData) == 1;
        } else if (checkExitKey.getCount() == 1) {
            // update
            checkExitKey.close();
            return db.update(DBHelper.TB_NAME, valuesData,
                    DBHelper.COL_KEY + "= ?", new String[]{key}) == 1;
        } else return false;

    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String def) {
        return getData(key, def);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        return getData(key, def);
    }

    public int getInt(String key) {
        return getInt(key, Integer.MIN_VALUE);
    }

    public int getInt(String key, int def) {
        return getData(key, def);
    }
    public long getLong(String key) {
        return getLong(key, Long.MIN_VALUE);
    }

    public long getLong(String key, long def) {
        return getData(key, def);
    }
    public float getFloat(String key) {
        return getFloat(key, Float.MIN_VALUE);
    }

    public float getFloat(String key, float def) {
        return getData(key, def);
    }

    private <DataType> DataType getData(String key, DataType def) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(DBHelper.TB_NAME, null, DBHelper.COL_KEY + "= ?",
                new String[]{key}, null, null, null, null);
        if (cursor == null) return def;
        if (cursor.getCount() <= 0 || cursor.getCount() > 1) return def;
        cursor.moveToFirst();
        String rs = cursor.getString(cursor.getColumnIndex(DBHelper.COL_DATA));
        cursor.close();
        if (def instanceof String) {
            return (DataType) rs;
        } else if (def instanceof Integer) {
            return (DataType) new Integer(Integer.parseInt(rs));
        } else if (def instanceof Float) {
            return (DataType) new Float(Float.parseFloat(rs));
        } else if (def instanceof Long) {
            return (DataType) new Long(Long.parseLong(rs));
        } else {
            return (DataType) new Boolean(intToBoolean(Integer.parseInt(rs)));
        }

    }

}
