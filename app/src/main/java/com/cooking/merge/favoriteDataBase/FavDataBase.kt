package com.cooking.merge.favoriteDataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

// Constructor
//context=內容物件；name=傳入資料庫名稱；factory=複雜查詢時使用；version=資料庫版本
private val DATABASE_VERSION = 1
private val DATABASE_NAME = "FavDataBase"
private val TABLE_NAME = "FavoriteTable"

class FavDataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val FAVORITE_STATUS: String = "fStatus"
    var KEY_ID = "id"
    var ITEM_TITLE = "itemTitle"
    var ITEM_IMAGE = "itemImage"

    // Don't forget write this spaces
    private val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " TEXT," + ITEM_TITLE + " TEXT,"
            + ITEM_IMAGE + " TEXT," + FAVORITE_STATUS + " TEXT)")

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, i: Int, i1: Int) {}

    // create empty table
    fun insertEmpty() {
        val db = this.writableDatabase
        val cv = ContentValues()
        // enter your value
        for (x in 0..100) {
            cv.put(KEY_ID, x)
            cv.put(FAVORITE_STATUS, "0")
            db.insert(TABLE_NAME, null, cv)
        }
    }

    // insert data into database
    fun insertIntoTheDatabase(
        item_title: String,
        item_image: Int,
        id: String?,
        fav_status: String
    ) {
        val db: SQLiteDatabase
        val cv = ContentValues()
        db = this.writableDatabase
        cv.put(ITEM_TITLE, item_title)
        cv.put(ITEM_IMAGE, item_image)
        cv.put(KEY_ID, id)
        cv.put(FAVORITE_STATUS, fav_status)
        db.insert(TABLE_NAME, null, cv)
        Log.d("FavDB Status", "$item_title, favstatus - $fav_status - . $cv")
    }

    // read all data
    fun read_all_data(id: String): Cursor? {
        val db = this.readableDatabase
        val sql = "select * from $TABLE_NAME where $KEY_ID=$id"
        return db.rawQuery(sql, null, null)
    }

    // remove line from database
    fun remove_fav(id: String) {
        val db = this.writableDatabase
        val sql = "UPDATE $TABLE_NAME SET  $FAVORITE_STATUS ='0' WHERE $KEY_ID=$id"
        db.execSQL(sql)
        Log.d("remove", id)
    }

    // select all favorite list
    fun select_all_favorite_list(): Cursor? {
        val db = this.readableDatabase
        val sql = "SELECT * FROM $TABLE_NAME WHERE $FAVORITE_STATUS ='1'"
        return db.rawQuery(sql, null, null)
    }
}