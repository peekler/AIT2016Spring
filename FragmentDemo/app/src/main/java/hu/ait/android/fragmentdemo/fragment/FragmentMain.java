package hu.ait.android.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import hu.ait.android.fragmentdemo.R;


public class FragmentMain extends Fragment {

    public static final String TAG = "FragmentMain";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null, false);

        Button btnDemo = (Button) rootView.findViewById(R.id.btnFragmentDemo);
        btnDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Button works on fragment",
                        Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}
