package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginFragment extends Fragment {

    static final String TAG = "LoginFragment";

    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.loginNoAccount)
    TextView noAccount;
    @BindView(R.id.loginEmail)
    EditText loginEmail;
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
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
