package com.example.notestore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import androidx.test.core.app.ApplicationProvider;

public class NoteStoreUnitTests {

    private NoteDb dbHelper;
    private SQLiteDatabase db;

    @Before
    public void setUp() {
        dbHelper = new NoteDb(ApplicationProvider.getApplicationContext());
        db = dbHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    @Test
    public void testDatabaseCreation() {
        assertTrue(db.isOpen());
    }

    @Test
    public void testInsertNote() {
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE, "Test Note");
        values.put(NoteContract.NoteEntry.COLUMN_NAME_CONTENT, "This is a test note content");

        long newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);

        assertTrue(newRowId != -1);
    }

    @Test
    public void testQueryNotes() {

        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE, "Test Note");
        values.put(NoteContract.NoteEntry.COLUMN_NAME_CONTENT, "This is a test note content");
        long newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);

        String[] projection = {
                NoteContract.NoteEntry._ID,
                NoteContract.NoteEntry.COLUMN_NAME_TITLE,
                NoteContract.NoteEntry.COLUMN_NAME_CONTENT
        };
        Cursor cursor = db.query(
                NoteContract.NoteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );


        assertTrue(cursor.moveToFirst());
        int titleIndex = cursor.getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_NAME_TITLE);
        int contentIndex = cursor.getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_NAME_CONTENT);
        assertEquals("Test Note", cursor.getString(titleIndex));
        assertEquals("This is a test note content", cursor.getString(contentIndex));
        cursor.close();
    }

    @Test
    public void testDeleteNote() {
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE, "Test Note");
        values.put(NoteContract.NoteEntry.COLUMN_NAME_CONTENT, "This is a test note content");
        long newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);
        String selection = NoteContract.NoteEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = {"Test Note"};
        int deletedRows = db.delete(NoteContract.NoteEntry.TABLE_NAME, selection, selectionArgs);
        assertTrue(deletedRows > 0);
    }
}
