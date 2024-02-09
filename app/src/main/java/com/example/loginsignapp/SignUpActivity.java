package com.example.loginsignapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class SignUpActivity extends AppCompatActivity {

    // variable declaration to fetch ui element of the SignUpapp.
    EditText signUpEmailView, signUpPasswordView, userNameView, datePicker;
    Button signUpBtn;
    TextView loginText;
    AutoCompleteTextView companyNameView;
    Spinner designationView;
    public void initialize()
    {
        signUpEmailView = findViewById(R.id.signUpEmailId);
        signUpPasswordView = findViewById(R.id.signPasswordId);
        signUpBtn = findViewById(R.id.signUpBtnId);
        loginText = findViewById(R.id.loginTextId);
        userNameView = findViewById(R.id.userNameId);
        companyNameView = findViewById(R.id.companyNameId);
        designationView = findViewById(R.id.spinnerId);
        datePicker = findViewById(R.id.datePicker);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        // calling initializeView method to get the UI element of the app.
        this.initialize();


        // STEP_04: signUp button clicked.
        signUpBtn.setOnClickListener(view->{

            //fetching the details of user.
            String userEmailId = signUpEmailView.getText().toString();
            String userPassword = signUpPasswordView.getText().toString();
            String userName = userNameView.getText().toString();
            String companyName = companyNameView.getText().toString();
            String designation = designationView.toString();
            String dateOfJoining = datePicker.getText().toString();


                //edge_case_04: Email Validation : if user doesn't enter email id.
                if(userEmailId.trim().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please Enter a email Id ⚠️", Toast.LENGTH_SHORT).show();
                    signUpEmailView.setError("Email is Required⚠️");
                }
                // edge_case_05: Email Validation : if user doesn't enter valid email id.
                else if(!UtilityClass.isValidEmail(userEmailId)){
                    Toast.makeText(SignUpActivity.this, "Please Enter a valid Email Id ⚠️", Toast.LENGTH_SHORT).show();
                    signUpEmailView.setError("Valid Email is Required⚠️");
                }
                //edge_case_06: Password Validation.
                else if(userPassword.trim().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please Enter Your Password 🔒", Toast.LENGTH_SHORT).show();
                }
                else if(!UtilityClass.isValidPassword(userPassword)){
                    Toast.makeText(SignUpActivity.this, "Min 1 UpperCase, 1 LowerCase , 1 Digit, 1 Special Character and Min 8 character required 🔒", Toast.LENGTH_SHORT).show();
                }

                // edge_case_07: userName can't be blank.
                else if(userName.trim().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please Enter Your Name ⚠️", Toast.LENGTH_SHORT).show();
                    userNameView.setError("Name is Required⚠️");
                }

                // edge_case_08: companyName can't be blank.
                else if(companyName.trim().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please select company Name ⚠️", Toast.LENGTH_SHORT).show();
                    companyNameView.setError("Company name is Required⚠️");
                }

                // edge_case_09: designation can't be blank.
                else if(designation.trim().equals("")){
                    Toast.makeText(SignUpActivity.this, "Please select your designation⚠️",Toast.LENGTH_SHORT).show();
                }
                // edge_case_10: dateOfJoining can't be blank.
                else if(dateOfJoining.equals("")){
                    Toast.makeText(SignUpActivity.this, "Please select date of joining ⚠️", Toast.LENGTH_SHORT).show();
                    datePicker.setError("Joining Date is Required⚠️");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sign Up Successful",Toast.LENGTH_LONG).show();
                    signUpEmailView.getText().clear();
                    signUpPasswordView.getText().clear();
                    userNameView.getText().clear();
                    companyNameView.getText().clear();
                    datePicker.getText().clear();
                }
        });

        //STEP_05: login Button to back to the login page.
        loginText.setOnClickListener(view ->{
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        //Feature_01: Spinner is implemented as a Drop Down to fetch the designation data.
        // Array to store the designation of the employee.
        String[] DesignationData ={
                " ","Android Developer","FullStack Developer","Devops Engineer","Tech Lead","Product Manger","React Developer"
        };
        // adapter use spinner to populate data.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,DesignationData);
        designationView.setAdapter(arrayAdapter);


        //Feature_02: AutoComplete Text feature in company Name text field:
        String[] companyArr ={
                "Promact Infotech","Amazon","Google","Facebook","Netflix","TowerResearch","Twitter","Adobe","Apple"
        };
        ArrayAdapter<String> companyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,companyArr);
        companyNameView.setAdapter(companyAdapter);
        companyNameView.setThreshold(1);

        //Feature_03: Adding Date Picker functionalities:
        datePicker.setOnClickListener(view->{
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view1, selectedYear, selectedMonth,selectedDayOfMonth)->{
                        datePicker.setText(selectedDayOfMonth + "/" + (selectedMonth+1) + "/" + selectedYear);
                    },
                    year,
                    month,
                    dayOfMonth
            );
            datePickerDialog.show();
        });
    }
}
