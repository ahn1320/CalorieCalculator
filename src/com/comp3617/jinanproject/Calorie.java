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

public class Calorie extends Activity {
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView viewFood;
    TextView intakeFoodNumber,viewIntakeCal,totalIntakeCalorie;
    String foodCategory;
    private int totalIntakeCal;
    private int totalSpentCal;
    private int unitIntakeCal = 0;
    private String foodSelectName;
    Button btn;

    String[] data = {"SANDWITCH", "BEVERGE", "DONUT", "MUFFIN"};

    Integer[] imageId = {
            R.drawable.sandwich123,
            R.drawable.beverage1234,
            R.drawable.donut1234,
            R.drawable.muffin123
    };
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        btn = (Button)findViewById(R.id.intake);
        btn.setBackgroundColor(Color.parseColor("#1F5C99"));
        btn.setTextColor(Color.WHITE);

        CustomList adapter = new
                CustomList(Calorie.this, data, imageId);

        list=(ListView)findViewById(R.id.list);
        list.setBackgroundColor(Color.parseColor("#E6E6E6"));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Calorie.this, "You Clicked at " +data[+ position], Toast.LENGTH_SHORT).show();
                if(position == 0)
                    goSandwich(view);
                else if(position == 1)
                    goBeverage(view);
                else if(position == 2)
                    goDonut(view);
                else
                    goMuffin(view);
            }
        });

        Intent mIntent = getIntent();
        foodCategory = mIntent.getStringExtra("categorySelected");
        int itemNo = mIntent.getIntExtra("foodSelected", 0);
        int intakeNumber = mIntent.getIntExtra("intakeNo", 0);
        totalSpentCal = mIntent.getIntExtra("totalSpentCal", 0);
        foodSelectName = mIntent.getStringExtra("foodIntakeName");

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException exception) {
            Toast.makeText(Calorie.this, "Failed to open database!",
                    Toast.LENGTH_LONG).show();
        }

        if(foodCategory == null || itemNo == 0){}
        else {
            unitIntakeCal = loginDataBaseAdapter.getIntakeCal(foodCategory, itemNo);
        }

        totalIntakeCal = mIntent.getIntExtra("newTotalInCal", 0) + intakeNumber * unitIntakeCal;


        viewFood =  (TextView) findViewById(R.id.foodName);
        intakeFoodNumber = (TextView)findViewById(R.id.intakeNo);
        viewIntakeCal = (TextView)findViewById(R.id.intakeCalorie);
        totalIntakeCalorie = (TextView)findViewById(R.id.totalInCal);

        viewFood.append(foodSelectName + "");
        viewFood.append("\n");
        intakeFoodNumber.append(intakeNumber + "");
        intakeFoodNumber.append("\n");
        viewIntakeCal.append(intakeNumber * unitIntakeCal + "");
        viewIntakeCal.append("\n");
        totalIntakeCalorie.append(totalIntakeCal + "");
        totalIntakeCalorie.append("\n");
    }



    public void goSandwich(View view) {
        foodCategory = "Sandwich";
        Intent sandwichPage=new Intent(getApplicationContext(),Sandwich.class);
        sandwichPage.putExtra("categoryChosen",foodCategory);
        sandwichPage.putExtra("totalInCal",totalIntakeCal);
        sandwichPage.putExtra("tempSpentCal",totalSpentCal);
        startActivity(sandwichPage);
    }

    public void goBeverage(View view) {
        foodCategory = "Beverage";
        Intent beveragePage=new Intent(getApplicationContext(),Beverage.class);
        beveragePage.putExtra("categoryChosen",foodCategory);
        beveragePage.putExtra("totalInCal",totalIntakeCal);
        beveragePage.putExtra("tempSpentCal",totalSpentCal);
        startActivity(beveragePage);
    }

    public void goDonut(View view) {
        foodCategory = "Donut";
        Intent donutPage=new Intent(getApplicationContext(),Donut.class);
        donutPage.putExtra("categoryChosen",foodCategory);
        donutPage.putExtra("totalInCal",totalIntakeCal);
        donutPage.putExtra("tempSpentCal",totalSpentCal);
        startActivity(donutPage);
    }

    public void goMuffin(View view) {
        foodCategory = "Muffin";
        Intent muffinPage=new Intent(getApplicationContext(),Muffin.class);
        muffinPage.putExtra("categoryChosen",foodCategory);
        muffinPage.putExtra("totalInCal",totalIntakeCal);
        muffinPage.putExtra("tempSpentCal",totalSpentCal);
        startActivity(muffinPage);
    }

    public void goExercise(View view){
        Intent exercisePage=new Intent(getApplicationContext(),Exercise.class);
        exercisePage.putExtra("intakeCal",totalIntakeCal);
        exercisePage.putExtra("newTotalCal",totalSpentCal);
        startActivity(exercisePage);
    }

    public void goBalance(View view){
        Intent balancePage=new Intent(getApplicationContext(),Balance.class);
        balancePage.putExtra("intakeCal",totalIntakeCal);
        balancePage.putExtra("totalSpentCal",totalSpentCal);

        startActivity(balancePage);
    }
}
