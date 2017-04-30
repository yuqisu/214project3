package project3.csc214.myapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import project3.csc214.myapp.login_register.FoodPreference;
import project3.csc214.myapp.login_register.Register;

public class Login_Register extends AppCompatActivity {
    FoodPreference preference = new FoodPreference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register);
        Register register = new Register();
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.login_register_page,register)
                .commit();

    }
    public void onCheckBoxClikced(View v){
        preference.onCheckBoxClikced(v);
    }
}
