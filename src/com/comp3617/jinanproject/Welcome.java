package com.comp3617.jinanproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Welcome extends Activity {
    TextView instruction;
    private static final String instructions = "Welcome!\n\n Please follow the instruction"
            + "  in order to use the app to calculate your daily calorie intake and balance "
            + "your life.\n\n "
            + "1.1 Click INTAKE button will navigate to the intake page\n"
            + "1.2 Click one of the four categories will navigate to the food page\n"
            + "1.3 Choose one of the food from the drop down list, and select"
            + " number of food that user intake, then click submit, navigate back to intake page\n"
            + "1.4 The selected information and total intake calorie will be displayed!\n\n"
            + "2.1 Click EXERCISE button will navigate to the exercise page\n"
            + "2.2 Click one of the four types will navigate to the exercise page\n"
            + "2.3 pick up the exercise time and click submit will navigate back to the "
            + "exercise page.\n"
            + "2.4 The selected information and total intake calorie will be displayed!\n\n"
            + "3.1 Click the BALANCE button will navigate to the Balance page\n"
            + "3.2 All the calorie information include balance will be updated!\n\n"
            + "4 All the navigation bars will be clickable during the run time of the app.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        instruction = (TextView) findViewById(R.id.instruct);
        instruction.setText(instructions);
    }

    public void goIntake(View view) {
        Intent intakePage = new Intent(getApplicationContext(), Calorie.class);
        startActivity(intakePage);
    }

    public void goExercise(View view) {
        Intent exercisePage = new Intent(getApplicationContext(),
                Exercise.class);
        startActivity(exercisePage);
    }

    public void goBalance(View view) {
        Intent balancePage = new Intent(getApplicationContext(), Balance.class);
        startActivity(balancePage);
    }
}
