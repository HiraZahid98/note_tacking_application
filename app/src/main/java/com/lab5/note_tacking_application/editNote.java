package com.lab5.note_tacking_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class editNote extends AppCompatActivity {
int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        EditText editText = (EditText) findViewById(R.id.EditText);
        EditText header = (EditText) findViewById(R.id.header_of_message);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        MainActivity.arrayAdapter.notifyDataSetChanged();
         if(noteId != -1){
             editText.setText(MainActivity.notes.get(noteId));
         }
         else{
             MainActivity.notes.add("");
             noteId = MainActivity.notes.size() - 1;
         }
         editText.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                 SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.robpercival.notes", Context.MODE_PRIVATE);

                 HashSet<String> set = new HashSet(MainActivity.notes);

                 sharedPreferences.edit().putStringSet("notes", set).apply();
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
    }
}
