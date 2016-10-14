package fr.quentinneyraud.www.p4p3r0v3r.Account.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.utils.DeviceInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    static final String TAG = "=== SignUpFragment ===";

    private SignUpFragmentListener signUpFragmentListener;

    @BindView(R.id.fragment_signup_button)
    Button signUpButton;
    @BindView(R.id.fragment_signup_email)
    AutoCompleteTextView emailAutocompleteTextView;
    @BindView(R.id.fragment_signup_pseudo)
    EditText pseudoEditText;
    @BindView(R.id.fragment_signup_password)
    EditText passwordEditText;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);

        DeviceInfo deviceInfo = DeviceInfo.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_dropdown_item_1line, deviceInfo.getAccounts( this.getContext()));

        emailAutocompleteTextView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            signUpFragmentListener = (SignUpFragmentListener) context;
        } catch (ClassCastException exception) {
        }
    }

    @OnClick(R.id.fragment_signup_button)
    void onSignUpButtonClick() {
        if (signUpFragmentListener != null) {
            signUpFragmentListener.onSignUpButtonClick(emailAutocompleteTextView.getText().toString(), passwordEditText.getText().toString(), pseudoEditText.getText().toString());
        }
    }

    public interface SignUpFragmentListener {
        void onSignUpButtonClick(String email, String password, String pseudo);
    }

}
