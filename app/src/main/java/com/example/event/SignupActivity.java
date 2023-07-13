package com.example.event;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.event.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity{
    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;
   @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       binding=ActivitySignupBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());

      databaseHelper=new DatabaseHelper(this);
      binding.signupButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String email=binding.signupEmail.getText().toString();
              String password=binding.signupPassword.getText().toString();
              String confirmPassword=binding.signupConfirm.getText().toString();

              if(email.equals("")||password.equals("")|| confirmPassword.equals("") )
                  Toast.makeText(SignupActivity.this, "All fields are mandatory ", Toast.LENGTH_SHORT).show();
              else {
                  if(password.equals(confirmPassword)){
                      Boolean checkUsersEmail = databaseHelper.checkEmail(email);
                      if(checkUsersEmail==false){
                          Boolean insert=databaseHelper.insertData(email,password);
                          if(insert==true){
                              Toast.makeText(SignupActivity.this, "signup Successfully", Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(getApplicationContext(),login.class);
                              startActivity(intent);
                          }else {
                              Toast.makeText(SignupActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                              
                          }
                          
                      }else {
                          Toast.makeText(SignupActivity.this, "User already exists, Please login", Toast.LENGTH_SHORT).show();
                      }
                  }else {
                      Toast.makeText(SignupActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                  }
              }
          }
      });
      binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(getApplicationContext(),login.class);
              startActivity(intent);

          }
      });
   }
}