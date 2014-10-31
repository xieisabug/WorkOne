package com.xjy.work.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xjy.work.utils.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResultService {

    DBHelper dbHelper = null;
    SQLiteDatabase db = null;
    Context mContext;

    public ResultService(Context context) {
        this.mContext = context;
        dbHelper = new DBHelper(mContext, "result");
        db = dbHelper.getWritableDatabase();
    }

    public void insertResult(String text, int image1Res, int image2Res) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("save_text", text);
        contentValues.put("save_image1", image1Res);
        contentValues.put("save_image2", image2Res);
        db.insert("Result", "", contentValues);
    }

    public List<Map<String, Object>> selectAll() {
        Cursor cursor = db.query("Result", new String[]{"_id", "save_text", "save_image1", "save_image2"}, "", new String[]{}, "", "", "");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> r = new HashMap<String, Object>();
                r.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
                r.put("save_text", cursor.getString(cursor.getColumnIndex("save_text")));
                r.put("save_image1", cursor.getInt(cursor.getColumnIndex("save_image1")));
                r.put("save_image2", cursor.getInt(cursor.getColumnIndex("save_image2")));
                result.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public List<Map<String, Object>> selectAllByLast() {
        Cursor cursor = db.query("Result", new String[]{"_id", "save_text", "save_image1", "save_image2"}, "", new String[]{}, "", "", "_id desc");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> r = new HashMap<String, Object>();
                r.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
                r.put("save_text", cursor.getString(cursor.getColumnIndex("save_text")));
                r.put("save_image1", cursor.getInt(cursor.getColumnIndex("save_image1")));
                r.put("save_image2", cursor.getInt(cursor.getColumnIndex("save_image2")));
                result.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public List<Map<String, Object>> selectGroupBySaveText() {
        Cursor cursor = db.query("Result", new String[]{"_id", "save_text", "save_image1", "save_image2"}, "", new String[]{}, "", "", "save_text");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> r = new HashMap<String, Object>();
                r.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
                r.put("save_text", cursor.getString(cursor.getColumnIndex("save_text")));
                r.put("save_image1", cursor.getInt(cursor.getColumnIndex("save_image1")));
                r.put("save_image2", cursor.getInt(cursor.getColumnIndex("save_image2")));
                result.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
    public List<Map<String, Object>> selectGroupBySaveImage1() {
        Cursor cursor = db.query("Result", new String[]{"_id", "save_text", "save_image1", "save_image2"}, "", new String[]{}, "", "", "save_image1");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> r = new HashMap<String, Object>();
                r.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
                r.put("save_text", cursor.getString(cursor.getColumnIndex("save_text")));
                r.put("save_image1", cursor.getInt(cursor.getColumnIndex("save_image1")));
                r.put("save_image2", cursor.getInt(cursor.getColumnIndex("save_image2")));
                result.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
    public List<Map<String, Object>> selectGroupBySaveImage2() {
        Cursor cursor = db.query("Result", new String[]{"_id", "save_text", "save_image1", "save_image2"}, "", new String[]{}, "", "", "save_image2");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (cursor.moveToFirst()) {
            do {
                Map<String, Object> r = new HashMap<String, Object>();
                r.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
                r.put("save_text", cursor.getString(cursor.getColumnIndex("save_text")));
                r.put("save_image1", cursor.getInt(cursor.getColumnIndex("save_image1")));
                r.put("save_image2", cursor.getInt(cursor.getColumnIndex("save_image2")));
                result.add(r);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
