package com.example.notestore;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testMainActivityUI() {

        Espresso.onView(ViewMatchers.withId(R.id.menu_add_note)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.noteNameEditText)).perform(ViewActions.typeText("Test Note"));
        Espresso.onView(ViewMatchers.withId(R.id.noteContentEditText)).perform(ViewActions.typeText("This is a test note content"));
        Espresso.onView(ViewMatchers.withId(R.id.saveNoteButton)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.deleteNotesListView)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.menu_delete_note)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.deleteNotesListView)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.deleteNotesListView)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.deleteNoteButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.menu_add_note)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.noteNameEditText)).perform(ViewActions.typeText("Test Note"));
        Espresso.onView(ViewMatchers.withId(R.id.noteContentEditText)).perform(ViewActions.typeText("This is a test note content"));
        Espresso.onView(ViewMatchers.withId(R.id.saveNoteButton)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.deleteNotesListView)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.menu_delete_note)).perform(ViewActions.click());


        Espresso.onView(ViewMatchers.withId(R.id.notesListView)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

    }
}
