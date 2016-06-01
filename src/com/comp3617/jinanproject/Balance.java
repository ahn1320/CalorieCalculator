package com.comp3617.jinanproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Balance extends Activity {
    private int totalIntakeCal;
    private int totalSpentCal;
    private int balance;
    private TextView intakeView, spentView, balanceView, statusView;
    private static final String NOTENOUGH = "You haven`t eaten enough!"
            + " Don`t try hard to lose your weight!";
    private static final String TOOMUCH = "You ate too much!"
            + " Please look after your health!";
    private static final String OKAY = "Calorie intake and spend well balanced!";

    RelativeLayout la;
    PieGraph piView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        btn = (Button) findViewById(R.id.balance);
        btn.setBackgroundColor(Color.parseColor("#663300"));
        btn.setTextColor(Color.WHITE);

        Intent mIntent = getIntent();
        totalIntakeCal = mIntent.getIntExtra("intakeCal", 0);
        totalSpentCal = mIntent.getIntExtra("totalSpentCal", 0);
        balance = totalIntakeCal - totalSpentCal;

        intakeView = (TextView) findViewById(R.id.intakeCal);
        spentView = (TextView) findViewById(R.id.spentCal);
        balanceView = (TextView) findViewById(R.id.balanceCal);
        statusView = (TextView) findViewById(R.id.status);

        intakeView.setText(totalIntakeCal + "");
        spentView.setText(totalSpentCal + "");
        balanceView.setText(balance + "");

        if (balance < 0) {
            statusView.setText(NOTENOUGH);
        }
        else if (balance == 0) {
            statusView.setText(OKAY);
        }
        else if (balance > 0) {
            statusView.setText(TOOMUCH);
        }

        int[] vals = { totalIntakeCal, totalSpentCal };
        la = (RelativeLayout) findViewById(R.id.pie_lay);
        getPieChart(vals);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getData(View view) {

    }

    public void goIntake(View view) {
        Intent intakePage = new Intent(getApplicationContext(), Calorie.class);
        intakePage.putExtra("newTotalInCal", totalIntakeCal);
        intakePage.putExtra("totalSpentCal", totalSpentCal);
        startActivity(intakePage);
    }

    public void goExercise(View view) {
        Intent exercisePage = new Intent(getApplicationContext(),
                Exercise.class);
        exercisePage.putExtra("intakeCal", totalIntakeCal);
        exercisePage.putExtra("newTotalCal", totalSpentCal);
        startActivity(exercisePage);
    }

    public void getPieChart(int[] values) {

        piView = new PieGraph(Balance.this, values);

        la.removeAllViews();
        la.addView(piView);
    }
}
