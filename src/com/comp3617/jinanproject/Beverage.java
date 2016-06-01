package com.comp3617.jinanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class Beverage extends Activity implements OnSeekBarChangeListener {
    String[] presidents;

    private String selectedCategory;
    private int position;
    private int intakeNum;
    private int tempInTotalCal;
    private SeekBar bar;
    private TextView textProgress;
    private int totalSpentCal;
    private String foodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage);

        Intent mIntent = getIntent();
        selectedCategory = mIntent.getStringExtra("categoryChosen");
        tempInTotalCal = mIntent.getIntExtra("totalInCal", 0);
        totalSpentCal = mIntent.getIntExtra("tempSpentCal", 0);

        presidents = getResources().getStringArray(R.array.beverage_array);

        Spinner s1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, presidents);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                    int arg2, long arg3) {
                position = arg0.getSelectedItemPosition();
                foodName = presidents[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        bar = (SeekBar) findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(this);

        textProgress = (TextView) findViewById(R.id.textViewProgress);

    }

    public void backToCalorie(View view) {
        Intent caloriePage = new Intent(getApplicationContext(), Calorie.class);
        caloriePage.putExtra("newTotalInCal", tempInTotalCal);
        caloriePage.putExtra("totalSpentCal", totalSpentCal);
        startActivity(caloriePage);
    }

    public void submitToCalorie(View view) {
        Intent caloriePage = new Intent(Beverage.this, Calorie.class);
        caloriePage.putExtra("categorySelected", selectedCategory);
        caloriePage.putExtra("foodSelected", position + 1);
        caloriePage.putExtra("intakeNo", intakeNum);
        caloriePage.putExtra("newTotalInCal", tempInTotalCal);
        caloriePage.putExtra("totalSpentCal", totalSpentCal);
        caloriePage.putExtra("foodIntakeName", foodName);
        startActivity(caloriePage);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromUser) {
        textProgress.setText("The value is: " + progress);
        intakeNum = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        seekBar.setSecondaryProgress(seekBar.getProgress());
    }
}