package com.example.notestore;
import android.provider.BaseColumns;
public final class NoteContract {

    private NoteContract() {
        throw new AssertionError("No instances for you!");
    }

    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}
