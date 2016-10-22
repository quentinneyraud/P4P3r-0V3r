package fr.quentinneyraud.www.p4p3r0v3r.User.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.quentinneyraud.www.p4p3r0v3r.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatternFragment extends Fragment {


    @BindView(R.id.first)
    RelativeLayout first;
    @BindView(R.id.second)
    RelativeLayout second;
    @BindView(R.id.third)
    RelativeLayout third;
    @BindView(R.id.fourth)
    RelativeLayout fourth;
    @BindView(R.id.fifth)
    RelativeLayout fifth;
    @BindView(R.id.sixth)
    RelativeLayout sixth;
    @BindView(R.id.seventh)
    RelativeLayout seventh;
    @BindView(R.id.eighth)
    RelativeLayout eighth;
    @BindView(R.id.ninth)
    RelativeLayout ninth;

    @BindView(R.id.pattern_fragment_title)
    TextView patternTitle;
    @BindView(R.id.pattern_fragment_subtitle)
    TextView patternSubtitle;


    static final String TAG = "PatternFragment";

    private PatternFragmentListener patternFragmentListener;

    ArrayList<String> colorArray = new ArrayList<String>();
    private String[] colorArrayString = {"aqua", "fuchsia", "lime", "red", "navy", "yellow", "purple", "silver", "teal"};

    public PatternFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pattern, container, false);

        ButterKnife.bind(this, view);

        initializePattern();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            patternFragmentListener = (PatternFragmentListener) context;
        } catch (ClassCastException exception) {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void initializePattern() {

        shuffleArray(colorArrayString);

        for (int i = 0; i < colorArrayString.length; i++) {
            colorArray.add(colorArrayString[i]);
        }

        first.setBackgroundColor(Color.parseColor(colorArrayString[0]));
        first.setTag(colorArrayString[0]);
        second.setBackgroundColor(Color.parseColor(colorArrayString[1]));
        second.setTag(colorArrayString[1]);
        third.setBackgroundColor(Color.parseColor(colorArrayString[2]));
        third.setTag(colorArrayString[2]);
        fourth.setBackgroundColor(Color.parseColor(colorArrayString[3]));
        fourth.setTag(colorArrayString[3]);
        fifth.setBackgroundColor(Color.parseColor(colorArrayString[4]));
        fifth.setTag(colorArrayString[4]);
        sixth.setBackgroundColor(Color.parseColor(colorArrayString[5]));
        sixth.setTag(colorArrayString[5]);
        seventh.setBackgroundColor(Color.parseColor(colorArrayString[6]));
        seventh.setTag(colorArrayString[6]);
        eighth.setBackgroundColor(Color.parseColor(colorArrayString[7]));
        eighth.setTag(colorArrayString[7]);
        ninth.setBackgroundColor(Color.parseColor(colorArrayString[8]));
        ninth.setTag(colorArrayString[8]);

    }


    static void shuffleArray(String[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void changeText() {
        patternTitle.setText(R.string.set_your_pattern);
        patternSubtitle.setText("");
    }

    @OnClick({R.id.first, R.id.second, R.id.third, R.id.fourth, R.id.fifth, R.id.sixth, R.id.seventh, R.id.eighth, R.id.ninth})
    void onPatternClick(View v) {
        patternFragmentListener.onPatternClick(v);
    }


    public interface PatternFragmentListener {
        void onPatternClick(View v);
    }


}
