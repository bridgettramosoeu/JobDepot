package com.example.ankit.job_depot.employer.view.LoginAuthentication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ankit.job_depot.R;
import com.example.ankit.job_depot.employer.view.LoginAuthentication.EmployerLogin;
import com.parse.ParseObject;


public class EmployerSignUp extends ActionBarActivity {

    Button btnSignUp;
    EditText textBoxUserName, textBoxPassword, textBoxConfirmPassword, textBoxEmail;
    TextView signInLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_employer_sign_up);

        btnSignUp = (Button) findViewById(R.id.buttonEmployerSignUp);
        textBoxPassword = (EditText) findViewById(R.id.textBoxPassword);
        textBoxUserName = (EditText) findViewById(R.id.textBoxEmployerName);
        textBoxConfirmPassword = (EditText) findViewById(R.id.textBoxConfirmPassword);
        textBoxEmail = (EditText) findViewById(R.id.textBoxEmail);
        signInLink = (TextView) findViewById(R.id.signInLink);

        signInLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EmployerLogin.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(textBoxPassword.getText().toString(), " "+textBoxConfirmPassword.getText().toString());
                if ((textBoxUserName.getText().toString().equals("")) || textBoxPassword.getText().toString().equals("") ||
                        textBoxEmail.getText().toString().equals("") || textBoxConfirmPassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "All fields must be filled",
                            Toast.LENGTH_LONG).show();
                }


                else if(!textBoxPassword.getText().toString().equals( textBoxConfirmPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(),
                            "Password are not same",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    ParseObject testObject = new ParseObject("EmployerCredentials");
                    testObject.put("EmployerName", textBoxUserName.getText().toString());
                    testObject.put("EmployerEmail", textBoxEmail.getText().toString());
                    testObject.put("EmployerPassword", textBoxPassword.getText().toString());
                    testObject.saveInBackground();

                    Intent intent = new Intent(getApplicationContext(), EmployerLogin.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employer_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
