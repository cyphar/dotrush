package com.jtdev.dotrush.db;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.jtdev.dotrush.Constants;

public class ScoreDatabaseManager implements IScoreDatabaseManager {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public ScoreDatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
        db = databaseHelper.getWritableDatabase();
    }

    @Override
    public int getHighScore() {
        Cursor cur = db.rawQuery("SELECT score FROM tbl_scores ORDER BY score DESC LIMIT 1", null);

        int highscore = -1;
        if(cur.moveToFirst())
            highscore = cur.getInt(0);

        cur.close();
        return highscore;
    }

    @Override
    public void addScore(int score) {
        Cursor cur = db.rawQuery("SELECT count(*) FROM tbl_scores WHERE score >= ?", new String[]{String.valueOf(score)});

        if(!cur.moveToFirst())
            return;

        int numscores = cur.getInt(0);
        cur.close();

        if(numscores >= Constants.HIGHSCORE_SIZE)
            return;

        SQLiteStatement stmt = db.compileStatement("INSERT INTO tbl_scores (score) VALUES (?)");

        if(stmt == null)
            return;

        stmt.bindLong(1, score);
        stmt.execute();
        stmt.close();
    }

    public void closeDB() {
        db.close();
    }
}
