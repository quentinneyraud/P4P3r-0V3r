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

public class SignInFragment extends Fragment {

    private SignInFragmentListener signInFragmentListener;

    @BindView(R.id.fragment_signin_button)
    Button signInButton;
    @BindView(R.id.fragment_signin_noaccount)
    TextView noAccountButton;
    @BindView(R.id.fragment_signin_email)
    AutoCompleteTextView emailAutocompleteTextView;
    @BindView(R.id.fragment_signin_password)
    EditText passwordEditText;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
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
            signInFragmentListener = (SignInFragmentListener) context;
        } catch (ClassCastException exception) {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.fragment_signin_button)
    public void onSignInClick() {
        if (signInFragmentListener != null) {
            signInFragmentListener.onSignInButtonClick(emailAutocompleteTextView.getText().toString(), passwordEditText.getText().toString());
        }
    }

    @OnClick(R.id.fragment_signin_noaccount)
    public void onNoAccountClick() {
        if (signInFragmentListener != null) {
            signInFragmentListener.onNoAccountTextViewClick();
        }
    }

    public interface SignInFragmentListener {
        void onSignInButtonClick(String email, String password);

        void onNoAccountTextViewClick();
    }

}
