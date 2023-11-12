package com.example.notestore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {

    private NoteDb dbHelper;
    private ListView deleteNotesListView;
    private ArrayAdapter<String> deleteNotesAdapter;
    private ArrayList<String> deleteNotesList;
    private Button deleteNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        dbHelper = new NoteDb(this);

        deleteNotesListView = findViewById(R.id.deleteNotesListView);
        deleteNoteButton = findViewById(R.id.deleteNoteButton);
        deleteNotesList = getNotesFromDatabase();
        deleteNotesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, deleteNotesList);
        deleteNotesListView.setAdapter(deleteNotesAdapter);
        deleteNotesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        deleteNotesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        deleteNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = deleteNotesListView.getCheckedItemPosition();
                if (selectedPosition != ListView.INVALID_POSITION) {
                    String selectedNote = deleteNotesList.get(selectedPosition);
                    deleteNoteFromDatabase(selectedNote);
                    finish();
                } else {
                    showToast("Please select a note to delete.");
                }
            }
        });
    }

    private ArrayList<String> getNotesFromDatabase() {
        ArrayList<String> notesList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                NoteContract.NoteEntry.COLUMN_NAME_TITLE
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

        while (cursor.moveToNext()) {
            String noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.NoteEntry.COLUMN_NAME_TITLE));
            notesList.add(noteTitle);
        }

        cursor.close();
        return notesList;
    }

    private void deleteNoteFromDatabase(String noteTitle) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = NoteContract.NoteEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = { noteTitle };

        db.delete(NoteContract.NoteEntry.TABLE_NAME, selection, selectionArgs);

        showToast("Note deleted successfully.");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
