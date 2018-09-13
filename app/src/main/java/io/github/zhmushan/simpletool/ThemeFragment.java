package io.github.zhmushan.simpletool;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThemeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MaterialButton) (getActivity().findViewById(Util.themeBtnId))).setIconResource(R.drawable.baseline_done_black_24dp);
        getActivity().findViewById(R.id.red_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.themeId = R.style.AppTheme;
                Util.themeBtnId = R.id.red_theme;
                getActivity().recreate();
                ((MaterialButton) (getActivity().findViewById(R.id.red_theme))).setIconResource(R.drawable.baseline_done_black_24dp);
            }
        });
        getActivity().findViewById(R.id.green_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.themeId = R.style.GreenTheme;
                Util.themeBtnId = R.id.green_theme;
                getActivity().recreate();
                ((MaterialButton) (getActivity().findViewById(R.id.green_theme))).setIconResource(R.drawable.baseline_done_black_24dp);
            }
        });
        getActivity().findViewById(R.id.blue_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.themeId = R.style.BlueTheme;
                Util.themeBtnId = R.id.blue_theme;
                getActivity().recreate();
            }
        });
    }
}
