package fr.quentinneyraud.www.p4p3r0v3r.User.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.quentinneyraud.www.p4p3r0v3r.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSearchFragment extends Fragment {


    public UserSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_search, container, false);
    }

}
