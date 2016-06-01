package com.comp3617.jinanproject;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise extends Activity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView viewType, timeSpend, viewCalSpent, totalSpendCalorie;
    private int timeSpent;
    private int unitCal;
    private int totalCal;
    private int totalInCal;
    private String exerciseType;

    Button btn;

    String[] data = { "BICYCLE", "JOGGING", "SWIMMING", "YOGA" };
    Integer[] imageId = { R.drawable.bike1, R.drawable.run1, R.drawable.swim1,
            R.drawable.yoga1 };
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        btn = (Button) findViewById(R.id.exercise);
        btn.setBackgroundColor(Color.parseColor("#005C1F"));
        btn.setTextColor(Color.WHITE);

        CustomList adapter = new CustomList(Exercise.this, data, imageId);

        list = (ListView) findViewById(R.id.list);
        list.setBackgroundColor(Color.parseColor("#E6E6E6"));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                if (position == 0)
                    goBicycle(view, data[0]);
                else if (position == 1)
                    goJogging(view, data[1]);
                else if (position == 2)
                    goSwimming(view, data[2]);
                else
                    goYoga(view, data[3]);
            }
        });

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException exception) {
            Toast.makeText(Exercise.this, "Failed to open database!",
                    Toast.LENGTH_LONG).show();
        }

        Intent mIntent = getIntent();
        timeSpent = mIntent.getIntExtra("totalTime", 0);
        unitCal = mIntent.getIntExtra("cal", 0);
        exerciseType = mIntent.getStringExtra("selectedExercise");
        totalCal = mIntent.getIntExtra("newTotalCal", 0) + unitCal * timeSpent;
        totalInCal = mIntent.getIntExtra("intakeCal", 0);

        viewType = (TextView) findViewById(R.id.exeType);
        timeSpend = (TextView) findViewById(R.id.timeSpent);
        viewCalSpent = (TextView) findViewById(R.id.cal);
        totalSpendCalorie = (TextView) findViewById(R.id.totalCal);

        viewType.append(exerciseType + "");
        viewType.append("\n");
        timeSpend.append(timeSpent + "");
        timeSpend.append("\n");
        viewCalSpent.append(unitCal * timeSpent + "");
        viewCalSpent.append("\n");
        totalSpendCalorie.append(totalCal + "");
        totalSpendCalorie.append("\n");

    }

    public void goBicycle(View view, String exeType) {
        Intent bicyclePage = new Intent(getApplicationContext(), Bicycle.class);
        bicyclePage.putExtra("totalCalorie", totalCal);
        bicyclePage.putExtra("exType", exeType);
        bicyclePage.putExtra("tempIntakeCal", totalInCal);
        startActivity(bicyclePage);
    }

    public void goJogging(View view, String exeType) {
        Intent joggingPage = new Intent(getApplicationContext(), Jogging.class);
        joggingPage.putExtra("totalCalorie", totalCal);
        joggingPage.putExtra("exType", exeType);
        joggingPage.putExtra("tempIntakeCal", totalInCal);
        startActivity(joggingPage);
    }

    public void goSwimming(View view, String exeType) {
        Intent swimmingPage = new Intent(getApplicationContext(),
                Swimming.class);
        swimmingPage.putExtra("totalCalorie", totalCal);
        swimmingPage.putExtra("exType", exeType);
        swimmingPage.putExtra("tempIntakeCal", totalInCal);
        startActivity(swimmingPage);
    }

    public void goYoga(View view, String exeType) {
        Intent yogaPage = new Intent(getApplicationContext(), Yoga.class);
        yogaPage.putExtra("totalCalorie", totalCal);
        yogaPage.putExtra("exType", exeType);
        yogaPage.putExtra("tempIntakeCal", totalInCal);
        startActivity(yogaPage);
    }

    public void goIntake(View view) {
        Intent intakePage = new Intent(getApplicationContext(), Calorie.class);
        intakePage.putExtra("totalSpentCal", totalCal);
        intakePage.putExtra("newTotalInCal", totalInCal);
        startActivity(intakePage);
    }

    public void goBalance(View view) {
        Intent balancePage = new Intent(getApplicationContext(), Balance.class);
        balancePage.putExtra("intakeCal", totalInCal);
        balancePage.putExtra("totalSpentCal", totalCal);
        startActivity(balancePage);
    }
}
