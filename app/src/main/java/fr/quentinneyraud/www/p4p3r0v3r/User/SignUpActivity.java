package fr.quentinneyraud.www.p4p3r0v3r.User;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.utils.DeviceInfo;
import fr.quentinneyraud.www.p4p3r0v3r.utils.Firebase;

public class SignUpActivity extends AppCompatActivity implements OnCompleteListener<AuthResult> {

    @BindView(R.id.sign_up_email_input) AutoCompleteTextView emailInput;
    @BindView(R.id.sign_up_password_input) EditText passwordInput;
    @BindView(R.id.sign_up_password_repeat_input) EditText passwordRepeatInput;
    @BindView(R.id.sign_up_button) Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        DeviceInfo deviceInfo = DeviceInfo.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, deviceInfo.getAccounts(getBaseContext()));

        emailInput.setAdapter(adapter);
    }

    @OnClick(R.id.sign_up_button)
    public void onSignUpButtonClick() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String passwordRepeat = passwordRepeatInput.getText().toString();

        Firebase firebase = Firebase.getInstance();

        firebase.createAccount(email, password, this);
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if(!task.isSuccessful()) {
            Log.d("TEST", "ERREUR SIGNUP");
        }
    }
}
