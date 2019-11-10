package com.gautam.projectsdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText Email,password;
    Button signin;

    TextView signUp;
    private CardView about,contact,info;
    FirebaseAuth mFirebaseAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(LoginActivity.this);

        mFirebaseAuth=FirebaseAuth.getInstance();

        Email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        signin=(Button)findViewById(R.id.signin);
        signUp=findViewById(R.id.signup);

        about=(CardView)findViewById(R.id.about);
        info=(CardView)findViewById(R.id.inform);
        contact=(CardView)findViewById(R.id.contact);

//        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
//            {
//                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
//                //for checking if email and pass exists
//
//                if(mFirebaseUser!=null)
//                {
//                    Toast.makeText(LoginActivity.this,"You are Logged in!!",Toast.LENGTH_SHORT).show();
//                    Intent i1=new Intent(LoginActivity.this,FunctionalActivity.class);
//                    startActivity(i1);
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this,"Please Login!!",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Check email and Password id=f entered validly or not
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
                    Toast.makeText(LoginActivity.this,"Enter Email and Password",Toast.LENGTH_SHORT).show();
                }
                //both email and pass are validly entered
                else if(!(email.isEmpty() && pwd.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this,"Login Error Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent gotoLogin=new Intent(LoginActivity.this,FunctionalActivity.class);
                                Email.setText( "" );
                                password.setText( "" );
                                String useremail=mFirebaseAuth.getCurrentUser().getEmail();

                                gotoLogin.putExtras( gotoLogin );
                                startActivity(gotoLogin);
                            }


                        }
                    });

                }

                else
                {
                    Toast.makeText(LoginActivity.this,"Error in SignUp",Toast.LENGTH_SHORT).show();

                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignup=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(gotosignup);
            }
        });


        contact.setOnClickListener(this );
        about.setOnClickListener(this);
        info.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){

            case R.id.about : i=new Intent(this,Aboutus.class); startActivity(i); finish(); break;
            case R.id.inform : i=new Intent(this,Information.class);startActivity(i);finish(); break;
            case R.id.contact : i=new Intent(this,ContactUs.class);startActivity(i); finish();break;

            default:break;

        }
    }

}
