package com.gautam.projectsdl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public EditText Email,password;
    Button signup;

    TextView signIn;
    FirebaseAuth mFirebaseAuth;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FirebaseApp.initializeApp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth=FirebaseAuth.getInstance();

        Email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        signup=(Button)findViewById(R.id.signin);
        signIn=findViewById(R.id.signup);
//        FirebaseApp.initializeApp(this);

        mFirebaseAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=Email.getText().toString();
                String pwd=password.getText().toString();

                //if empty email
                if(email.isEmpty())
                {
                    Email.setError("Please enter Email id");
                    Email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    password.setError("Please enter ur password");
                    password.requestFocus();
                }

                else if(email.isEmpty() && pwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Enter Email and Password",Toast.LENGTH_SHORT).show();
                }
                //both email and pass are validly entered
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,"Sign up Unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent gotoLogin=new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(gotoLogin);
                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(MainActivity.this,"Error in SignUp",Toast.LENGTH_SHORT).show();

                }


            }
        });


        //if already registered go to loginactivity

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(gotoLogin);
            }
        });
    }
}
