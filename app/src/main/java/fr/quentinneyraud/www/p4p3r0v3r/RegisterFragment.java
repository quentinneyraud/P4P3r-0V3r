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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.quentinneyraud.www.p4p3r0v3r.utils.DeviceInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    static final String TAG = "RegisterFragment";

    private OnRegisterListener listener;

    @BindView(R.id.registerButton)
    Button registerButton;
    @BindView(R.id.registerEmail)
    AutoCompleteTextView registerEmail;
    @BindView(R.id.registerPassword)
    EditText registerPassword;
    @BindView(R.id.registerRepeatPassword)
    EditText registerRepeatPassword;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        DeviceInfo deviceInfo = DeviceInfo.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_dropdown_item_1line, deviceInfo.getAccounts( this.getContext()));

        registerEmail.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnRegisterListener) context;
        } catch (ClassCastException exception) {
            Log.d(TAG, "Cannot cast context to OnRegisterListener");
        }
    }

    @OnClick(R.id.registerButton)
    void onRegisterClick() {
        if (listener != null) {
            listener.onRegisterClick(registerEmail.getText().toString(), registerPassword.getText().toString(), registerRepeatPassword.getText().toString());
        }
    }

    public interface OnRegisterListener {
        void onRegisterClick(String email, String password, String repeatPassword);
    }

}
