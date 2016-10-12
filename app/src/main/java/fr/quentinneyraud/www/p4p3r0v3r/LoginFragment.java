package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.quentinneyraud.www.p4p3r0v3r.utils.DeviceInfo;


public class LoginFragment extends Fragment {

    static final String TAG = "LoginFragment";

    private OnLoginListener listener;

    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.loginNoAccount)
    TextView noAccount;
    @BindView(R.id.loginEmail)
    AutoCompleteTextView loginEmail;
    @BindView(R.id.loginPassword)
    EditText loginPassword;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        DeviceInfo deviceInfo = DeviceInfo.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_dropdown_item_1line, deviceInfo.getAccounts( this.getContext()));

        loginEmail.setAdapter(adapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnLoginListener) context;
        } catch (ClassCastException exception) {
            Log.d(TAG, "Cannot cast context to OnLoginListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.loginButton)
    void onSignInClick() {

        if (listener != null) {
            listener.onSignInClick(loginEmail.getText().toString(), loginPassword.getText().toString());
        }
    }

    @OnClick(R.id.loginNoAccount)
    void onNoAccountClick() {
        if (listener != null) {
            listener.onNoAccountClick();
        }
    }

    public interface OnLoginListener {
        void onSignInClick(String email, String password);

        void onNoAccountClick();
    }

}
