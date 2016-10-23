package fr.quentinneyraud.www.p4p3r0v3r.User.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

public class ProfileFragment extends Fragment {


    @BindView(R.id.profile_fragment_email)
    TextView email;
    @BindView(R.id.profile_fragment_pseudo)
    TextView pseudo;

    @BindView(R.id.old_password)
    TextInputEditText oldPassword;
    @BindView(R.id.new_password)
    TextInputEditText newPassword;
    @BindView(R.id.validate_profile)
    Button validateBtn;

    String emailString;
    String pseudoString;

    private ProfileFragmentListener profileFragmentListener;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        User currentUser = AccountService.getInstance().getCurrentUser();
        pseudoString = currentUser.getPseudo();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            emailString = user.getEmail();
        }

        email.setText(emailString);
        pseudo.setText(pseudoString);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            profileFragmentListener = (ProfileFragmentListener) context;
        } catch (ClassCastException exception) {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.validate_profile)
    public void onClickProfileButton() {
        if (!newPassword.getText().toString().equals("")) {
            profileFragmentListener.onClickProfileButton(oldPassword.getText().toString(), newPassword.getText().toString());
        }
    }


    public interface ProfileFragmentListener {
        void onClickProfileButton(String oldPassword, String newPassword);
    }

}
