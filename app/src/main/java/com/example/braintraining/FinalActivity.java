package com.example.braintraining;

import static com.example.braintraining.MainActivity.RESULT_SET;
import static com.example.braintraining.MainActivity.TEST;
import static com.example.braintraining.MainActivity.TIME;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class FinalActivity extends Activity {
    private TextView tv_res, tv_best_res;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);
        tv_res = findViewById(R.id.tv_result);
        tv_best_res = findViewById(R.id.tv_best_result);
        tv_res.setText(String.valueOf(getIntent().getFloatExtra(TIME, 0.0f)));
        sharedPreferences = getSharedPreferences(TEST, MODE_PRIVATE);
        tv_best_res.setText(Collections.min(sharedPreferences.getStringSet(RESULT_SET, new HashSet<>())));
    }

    public void onClickBack(View view) {
        startActivity(new Intent(FinalActivity.this, StartActivity.class));
    }
}
