package com.example.loginsignapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    // variable declaration to fetch ui element of the login App.
    Button loginBtn;
    EditText emailIdView, passwordView;
    TextView forgetBtn, registerBtn;

    // function to  fetch UI elements of the app.
    public void initializeView()
    {
        loginBtn = findViewById(R.id.loginBtn);
        emailIdView = findViewById(R.id.loginEmailId);
        passwordView = findViewById(R.id.loginPasswordId);
        forgetBtn = findViewById(R.id.forgetBtnId);
        registerBtn = findViewById(R.id.registerBtn);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        // calling initializeView method to get the UI element of the app.
        this.initializeView();

        // STEP_01: OnClickListener is added to login button: to fetch user details.
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // fetching user's email Id and password.
                String emailId = emailIdView.getText().toString();
                String password = passwordView.getText().toString();


                // Login form validation.

                // edge_case_01: Email Validation : if user doesn't enter email id.
                if(emailId.trim().equals("")){
                    Toast.makeText(LoginActivity.this, "Please Enter Your Email Id ⚠️", Toast.LENGTH_SHORT).show();
                    emailIdView.setError("Email is Required⚠️");
                }
                // edge_case_02: Email Validation : if user doesn't enter a valid email id.
                else if(!UtilityClass.isValidEmail(emailId)){
                    Toast.makeText(LoginActivity.this, "Please Enter Valid Email Id ⚠️", Toast.LENGTH_SHORT).show();
                    emailIdView.setError("Valid Email is Required⚠️");
                }
                // edge_case_03: if the user doesn't enter the password:
                else if(password.trim().equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter your password ⚠️", Toast.LENGTH_SHORT).show();
                    passwordView.setError("Password is Required⚠️");
                }
               else{
                    Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                    emailIdView.getText().clear();
                    passwordView.getText().clear();
                }
            }
        });

        //STEP_02: OnClickListener is added to forget button to send the password recovery link to tha mail.
        forgetBtn.setOnClickListener(view->{
            Toast.makeText(LoginActivity.this, "Recovery Link is sent to the registerd Email", Toast.LENGTH_SHORT).show();
        });

        // STEP_03: If the user is not registered yet => let the user move to register page.
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
