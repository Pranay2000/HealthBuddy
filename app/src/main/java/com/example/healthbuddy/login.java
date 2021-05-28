package com.example.healthbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    TextView btn, btnpassword;
    EditText inputEmail,inputPassword;
    Spinner mySpinner;
    Button btnLogin;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    boolean isDoc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.textViewSignUp);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        mySpinner = findViewById(R.id.spinner1);
        btnLogin = findViewById(R.id.login_button);
        btnpassword = findViewById(R.id.forgotPassword);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(login.this);

        ArrayAdapter <CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.spinner, R.layout.support_simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
              /*  String item = mySpinner.getSelectedItem().toString();
                if (item.equals("Doctor")){
                    Intent intent = new Intent(login.this,Doctor_board.class);
                    startActivity(intent);
                }else if(item.equals("Patient")){
                    Intent intent = new Intent(login.this,patient_dashboard.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                }

               */
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, registerpage.class));
            }
        });

        btnpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, forgotpassword.class));
            }
        });
    }
    private void checkCredentials() {
        String email = inputEmail.getText().toString();
        String Password = inputPassword.getText().toString();

        if(email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Email is not valid!");
        }
        else if(Password.isEmpty() || Password.length()<8)
        {
            showError(inputPassword,"Password must contain 8 characters");
        }
        else
        {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait while checking your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        String item = mySpinner.getSelectedItem().toString();
                        if (item.equals("Doctor"))
                        {
                            mLoadingBar.dismiss();
                            Intent intent = new Intent(login.this, Doctor_board.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else if(item.equals("Patient"))
                        {
                            mLoadingBar.dismiss();
                            Intent intent = new Intent(login.this, patient_dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void showError(EditText input, String s){
        input.setError(s);
        input.requestFocus();
    }
}
