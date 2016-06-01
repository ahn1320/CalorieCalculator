package com.comp3617.jinanproject;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Swimming extends Activity {
    String[] presidents;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView text;
    private int timeSpent;
    private final int swimmingCal = 8;
    private int tempTotalCal;
    private String exerciseTYPE;
    private int totalIntakeCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swimming);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException exception) {
            Toast.makeText(Swimming.this, "Failed to open database!",
                    Toast.LENGTH_LONG).show();
        }

        Intent mIntent = getIntent();
        tempTotalCal = mIntent.getIntExtra("totalCalorie", 0);
        exerciseTYPE = mIntent.getStringExtra("exType");
        totalIntakeCal = mIntent.getIntExtra("tempIntakeCal", 0);

        presidents = getResources().getStringArray(R.array.exercise_time_array);

        Spinner s1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, presidents);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                int position = arg0.getSelectedItemPosition();
                switch (position) {
                case 0:
                    timeSpent = 30;
                    break;
                case 1:
                    timeSpent = 60;
                    break;
                case 2:
                    timeSpent = 90;
                    break;
                case 3:
                    timeSpent = 120;
                    break;
                case 4:
                    timeSpent = 150;
                    break;
                case 5:
                    timeSpent = 180;
                    break;
                case 6:
                    timeSpent = 210;
                    break;
                case 7:
                    timeSpent = 240;
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    public void backToExercise(View view) {
        Intent execisePage = new Intent(getApplicationContext(), Exercise.class);
        execisePage.putExtra("newTotalCal", tempTotalCal);
        execisePage.putExtra("intakeCal", totalIntakeCal);
        startActivity(execisePage);
    }

    public void submitToExercise(View view) {
        Intent execisePage = new Intent(Swimming.this, Exercise.class);
        execisePage.putExtra("totalTime", timeSpent);
        execisePage.putExtra("cal", swimmingCal);
        execisePage.putExtra("newTotalCal", tempTotalCal);
        execisePage.putExtra("selectedExercise", exerciseTYPE);
        execisePage.putExtra("intakeCal", totalIntakeCal);
        startActivity(execisePage);
    }
}
