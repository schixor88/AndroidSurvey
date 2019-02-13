package com.kushagra.kushagra.test;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kushagra.kushagra.test.model.User;
import com.rey.material.widget.CheckBox;

import java.io.File;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    EditText login_phone,login_pass;
    Button login_btn;
    CheckBox checkBox;

//    FirebaseAuth mAuth;
    FirebaseDatabase database;

    DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);








        getSupportActionBar().setTitle("Login");



        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            Toast.makeText(LoginActivity.this, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(info.isConnected())
            {
                Toast.makeText(LoginActivity.this, " Internet Connection!", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(LoginActivity.this, "No Internet!", Toast.LENGTH_SHORT).show();
            }

        }


        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");



       login_phone = (EditText)findViewById(R.id.login_phone);
       login_pass = (EditText)findViewById(R.id.login_password);
       login_btn = (Button)findViewById(R.id.btn_login);
       checkBox = (CheckBox)findViewById(R.id.checkRemember);

        Paper.init(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please Wait!");
                mDialog.show();
                mDialog.setCanceledOnTouchOutside(false);


                String hello="Hello";


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(login_phone.getText().toString()).exists()) {
                            mDialog.dismiss();



                             User user = dataSnapshot.child(login_phone.getText().toString()).getValue(User.class);

                            if (!TextUtils.isEmpty(user.getPassword()) && user.getPassword().equals(login_pass.getText().toString())) {
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "No User Found!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        }




    }



