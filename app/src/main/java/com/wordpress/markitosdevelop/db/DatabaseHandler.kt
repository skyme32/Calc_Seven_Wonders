package com.wordpress.markitosdevelop.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.wordpress.markitosdevelop.Score


/***
 * author skyme32
 */
class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, " +
                "$PLAYER TEXT, " +
                "$DATE TEXT" +
                "$TOTAL_SCORE Integer, " +
                "$MILITARY_CONFLICTS Integer" +
                "$TREASURY_CONTENTS Integer, " +
                "$WONDER TEXT" +
                "$CIVILIAN_STRUCTURES Integer, " +
                "$SCIENTIFIC_STRUCTURES Integer" +
                "$COMMERCIAL_STRUCTURES Integer" +
                "$GUILDS Integer" +
                ")"

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Called when the database needs to be upgraded
    }

    //Inserting (Creating) data
    fun addScore(score: Score): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(PLAYER, score.player)
        values.put(DATE, score.date)
        values.put(TOTAL_SCORE, score.totalScore)
        values.put(MILITARY_CONFLICTS, score.military_conflicts)
        values.put(TREASURY_CONTENTS, score.treasury_contents)
        values.put(WONDER, score.wonder)
        values.put(CIVILIAN_STRUCTURES, score.civilian_structures)
        values.put(SCIENTIFIC_STRUCTURES, score.scientific_structures)
        values.put(COMMERCIAL_STRUCTURES, score.commercial_structures)
        values.put(GUILDS, score.guilds)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("Inserted_ID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    //get all users
    fun getAllScores() {

        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var id = cursor.getString(cursor.getColumnIndex(ID))


                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
    }

    companion object {
        private val DB_NAME = "ScoreDB"
        private val DB_VERSION = 1;
        private val TABLE_NAME = "scores"
        private val ID = "id"
        private val PLAYER = "player"
        private val DATE = "date"
        private val TOTAL_SCORE = "totalScore"
        private val MILITARY_CONFLICTS = "militaryConflicts"
        private val TREASURY_CONTENTS = "treasury_contents"
        private val WONDER = "wonder"
        private val CIVILIAN_STRUCTURES = "civilian_structures"
        private val SCIENTIFIC_STRUCTURES = "scientific_structures"
        private val COMMERCIAL_STRUCTURES = "commercialStructures"
        private val GUILDS = "guilds"
    }
}