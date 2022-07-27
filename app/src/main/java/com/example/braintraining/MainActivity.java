package com.example.braintraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SAVE_KEY = "save_key";
    public static final String TEST = "Test";
    private SharedPreferences sharedPreferences;
    private EditText editTextSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences(TEST, MODE_PRIVATE);
        editTextSave = findViewById(R.id.editTextSave);
        editTextSave.setText(sharedPreferences.getString(SAVE_KEY, getResources().getString(R.string.empty)));
    }

    public void onClickSave(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_KEY, editTextSave.getText().toString());
        editor.apply();
    }

    public void onClickGet(View view) {
        editTextSave.setText(sharedPreferences.getString(SAVE_KEY, getResources().getString(R.string.empty)));
    }
}