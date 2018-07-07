package com.mdove.passwordguard.home.main.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class HomeMessFragment extends Fragment{

    public static HomeMessFragment newInstance() {

        Bundle args = new Bundle();

        HomeMessFragment fragment = new HomeMessFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
