package com.example.ssw_322_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.example.ssw_322_project.ClassesAndInterfaces.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    MaterialEditText editCreateName, editCreateUsername, editCreatePassword, editCreateEmail; //Registration Info
    MaterialEditText editUsername, editPassword; //Login Info

    Button btnSignup, btnLogin;

    FirebaseDatabase database;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase setup
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        //Login page
        editUsername = (MaterialEditText)findViewById(R.id.editUsername);
        editPassword = (MaterialEditText)findViewById(R.id.editPassword);
        btnSignup = (Button)findViewById(R.id.btn_signup);
        btnLogin = (Button)findViewById(R.id.btn_login);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignUpPage();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editUsername.getText().toString(), editPassword.getText().toString());

            }
        });

    }

    private void login(final String user, final String password){
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).exists()){
                    if (!user.isEmpty()){
                        User loginInfo = dataSnapshot.child(user).getValue(User.class);
                        if(loginInfo.getPassword().equals(password)){
                            Toast.makeText(MainActivity.this, "Success.", Toast.LENGTH_SHORT).show();
                            showHomePage();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect Username/Password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Username does not exist.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showSignUpPage(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);


        LayoutInflater inflater = this.getLayoutInflater();
        View create_account_layout = inflater.inflate(R.layout.create_account, null);

        editCreateName = (MaterialEditText)create_account_layout.findViewById(R.id.editCreateName);
        editCreateUsername = (MaterialEditText)create_account_layout.findViewById(R.id.editCreateUsername);
        editCreatePassword = (MaterialEditText)create_account_layout.findViewById(R.id.editCreatePassword);
        editCreateEmail = (MaterialEditText)create_account_layout.findViewById(R.id.editCreateEmail);

        alertDialog.setView(create_account_layout);
        alertDialog.setTitle("Sign Up");

        //Canceling signup
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //Submitting Info
        alertDialog.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final User user = new User(editCreateUsername.getText().toString(),
                        editCreatePassword.getText().toString(),
                        editCreateName.getText().toString(),
                        editCreateEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists()){
                            Toast.makeText(MainActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();

                        } else {
                            users.child(user.getUsername()).setValue(user);
                            Toast.makeText(MainActivity.this, "Success.", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void showHomePage(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


}


