package com.example.braintraining;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    public static final String TEST = "Test";
    public static final String TIME = "time";
    public static final String RESULT_SET = "result_set";
    private SharedPreferences sharedPreferences;
    private TextView tv_res, tv_main;
    private ActionBar actionBar;
    private int number_1, number_2, random_result, correct_result, true_answers;
    private int max_true_answers = 10;
    private int max = 20;
    private int min = 1;
    private long start_time, current_time;
    private float time_result = 0.0f;
    private boolean is_true_answer = false;
    private HashSet<String> resSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        start_time = System.currentTimeMillis();
        sharedPreferences = getSharedPreferences(TEST, MODE_PRIVATE);
        tv_res = findViewById(R.id.tv_res);
        tv_main = findViewById(R.id.tv_main);
        actionBar = getSupportActionBar();
        resSet = new HashSet<>();
        numbers();
    }

    public void onClickCorrect(View view) {
        if (true_answers < max_true_answers) {
            if (is_true_answer) {
                ++true_answers;
            }
            resolveCalculation();
        }
    }

    public void onClickIncorrect(View view) {
        if (true_answers < max_true_answers) {
            if (!is_true_answer) {
                ++true_answers;
            }
            resolveCalculation();
        }
    }

    private void numbers() {
        number_1 = (int) (Math.random() * max - min);
        number_2 = (int) (Math.random() * max - min);
        random_result = (int) (Math.random() * max - min);
        correct_result = number_1 + number_2;
        int random_digit = (int) (Math.random() * (5 - 1));
        if (random_digit == 3 || random_digit == 1) {
            buildString(correct_result);
            is_true_answer = true;
        } else {
            buildString(random_result);
            is_true_answer = false;
        }

        if (true_answers == max_true_answers) {
            Intent intent = new Intent(MainActivity.this, FinalActivity.class);
            intent.putExtra(TIME, time_result);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            resSet.addAll(sharedPreferences.getStringSet(RESULT_SET, new HashSet<>()));
            resSet.add(String.valueOf(time_result));
            editor.putStringSet(RESULT_SET, resSet);
            editor.apply();
            startActivity(intent);
        }
    }

    private void buildString(int res) {
        tv_main.setText(new StringBuilder().append(number_1).append(" + ").append(number_2).append(" = ")
                .append(res));
    }

    private void resolveCalculation() {
        numbers();
        current_time = System.currentTimeMillis();
        time_result = (float)(current_time - start_time) / 1000;
        actionBar.setTitle(new StringBuilder("Time: ").append(time_result));
        tv_res.setText(String.valueOf(true_answers));
    }
}