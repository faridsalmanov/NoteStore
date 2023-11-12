package com.example.notestore;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;


public class AddNoteActivity extends AppCompatActivity {

    private NoteDb dbHelper;
    private EditText noteNameEditText, noteContentEditText;
    private Button saveNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        dbHelper = new NoteDb(this);

        noteNameEditText = findViewById(R.id.noteNameEditText);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        saveNoteButton = findViewById(R.id.saveNoteButton);

        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteName = noteNameEditText.getText().toString().trim();
                String noteContent = noteContentEditText.getText().toString().trim();

                if (noteName.isEmpty()) {
                    showToast("Note name cannot be empty.");
                } else {
                    saveNoteToDatabase(noteName, noteContent);
                    finish();
                }
            }
        });
    }

    private void saveNoteToDatabase(String noteName, String noteContent) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COLUMN_NAME_TITLE, noteName);
        values.put(NoteContract.NoteEntry.COLUMN_NAME_CONTENT, noteContent);

        long newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            showToast("Note saved successfully.");
        } else {
            showToast("Error saving note.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
