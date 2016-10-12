package fr.quentinneyraud.www.p4p3r0v3r.User;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.utils.DeviceInfo;
import fr.quentinneyraud.www.p4p3r0v3r.utils.SessionManager;

public class SignInActivity extends AppCompatActivity {

    static final String TAG = "== Sign in Activity ==";

    SessionManager sessionManager;

    @BindView(R.id.sign_in_button)
    Button loginButton;
    @BindView(R.id.sign_in_email_input)
    AutoCompleteTextView emailInput;
    @BindView(R.id.sign_in_password_value)
    TextInputEditText passwordInput;
    @BindView(R.id.sign_in_no_account)
    TextView noAccountTextView;
/**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);

        sessionManager = SessionManager.getInstance();

        DeviceInfo deviceInfo = DeviceInfo.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, deviceInfo.getAccounts(getBaseContext()));

        emailInput.setAdapter(adapter);
    }
**/
    /**
     * DONE
     *
     * @OnClick(R.id.sign_in_no_account) public void onNoAccountClick() {
     * Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
     * startActivity(i);
     * finish();
     * }
     **/
    /**
     * @OnClick(R.id.sign_in_button) public void onSignInButtonClick() {
     * String email = emailInput.getText().toString();
     * String password = passwordInput.getText().toString();
     * <p>
     * Firebase.getInstance().logUser(email, password, this);
     * }
     **/
    /**
     @Override public void onComplete(@NonNull Task<AuthResult> task) {
     if (task.isSuccessful()) {
     //TODO : Redirect to conversation activity
     } else {
     Toast.makeText(this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
     }
     }
     **/
}
