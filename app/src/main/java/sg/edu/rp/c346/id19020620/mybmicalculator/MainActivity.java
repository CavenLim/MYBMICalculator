package sg.edu.rp.c346.id19020620.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button calculateBtn;
    Button resetBtn;
    Button calculateCMBtn;
    TextView tvDate;
    TextView tvBMI;
    EditText etHeight;
    EditText etWeight;
    TextView tvCategory;
    TextView tvTips;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculateBtn=findViewById(R.id.button);
        resetBtn=findViewById(R.id.button2);
        tvDate=findViewById(R.id.displayDate);
        tvBMI=findViewById(R.id.displayBmi);
        etHeight=findViewById(R.id.editTextHt);
        etWeight=findViewById(R.id.editTextWt);
        tvCategory=findViewById(R.id.textViewCategory);
        etWeight.requestFocus();
        calculateCMBtn=findViewById(R.id.buttonCm);
        tvTips=findViewById(R.id.textViewTips);


        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();



            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDate.setText("Last calculated date: ");
                tvBMI.setText("Last calculated Bmi :");
                tvCategory.setText("");
                delete();


            }
        });
        calculateCMBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCm();

            }
        });


    }
    private void calculateCm(){
        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        String category = null;
        String tips=null;
        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        float height=Integer.parseInt(etHeight.getText().toString());
        float weight=Integer.parseInt(etWeight.getText().toString());
        float height2=height/100;
        float weight2=weight/1000;
        float bmi=weight2/(height2*height2);


        if(bmi < 18.5){
            category="you are of unhealthy weight group";
            tips="1. You can try to eat more protein";

        }
        else if (bmi >= 18.5 && bmi <= 24.9){
            category="you are of healthy weight group";
            tips="1. You can try to eat like usual";
        }
        else if(bmi >= 25 && bmi <= 29.9){
            category="you are of overweight group";
            tips="1. You can try to eat in quantity than quality";
        }
        else if(bmi > 30){
            category="you are of a very unhealthy weight group";
            tips="1. You can try to getting a balanced diet";
        }





        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putFloat("BMI",bmi);
        prefEdit.putString("Date",datetime);
        prefEdit.commit();
        tvDate.setText("Last calculated date: "+ datetime);
        tvBMI.setText(String.format("Last calculated bmi is %.2f",bmi));

        tvCategory.setText(category);
        tvTips.setText(tips);



    }



    private void calculate(){
        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        String category = null;
        String tips=null;
        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        float height=Float.parseFloat(etHeight.getText().toString());
        float weight=Float.parseFloat(etWeight.getText().toString());
        float bmi=weight/(height*height);

        if(bmi < 18.5){
            category="you are of unhealthy weight group";
            tips="1. You can try to eat more protein";

        }
        else if (bmi >= 18.5 && bmi <= 24.9){
             category="you are of healthy weight group";
            tips=" 1. You can try to eat like usual";
        }
        else if(bmi >= 25 && bmi <= 29.9){
             category="you are of overweight group";
            tips="1. You can try to eat in quantity than quality";
        }
        else if(bmi > 30){
            category="you are of a very unhealthy weight group";
            tips="1. You can try to getting a balanced diet";
        }



        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putFloat("BMI",bmi);
        prefEdit.putString("Date",datetime);
        prefEdit.commit();
        tvDate.setText("Last calculated date: "+ datetime);
        tvBMI.setText(String.format("Last calculated bmi is %.2f",bmi));

        tvCategory.setText(category);
        tvTips.setText(tips);

    }
    private void delete(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.clear();
        prefEdit.commit();
    }
    @Override
    protected void onPause() {
        super.onPause();
        calculate();


    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String prevDate=prefs.getString("Date","No dates ");
        float bmi=prefs.getFloat("BMI",0);
        String bmiDisplay=Float.toString(bmi);
        etWeight.setText("");
        etHeight.setText("");
        tvDate.setText("Last calculated date: "+prevDate);
        tvBMI.setText(String.format("Last calculated bmi is %.2f",bmi));

    }
}
