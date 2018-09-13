package io.github.zhmushan.simpletool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

public class ConverterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageView bottomSheetArrow = getActivity().findViewById(R.id.bottom_sheet_arrow);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(getActivity().findViewById(R.id.bottom_sheet_nav));
        //调用底部拉动栏
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (behavior.getState()) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        bottomSheetArrow.setImageResource(R.drawable.collapse_arrow_24);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        bottomSheetArrow.setImageResource(R.drawable.expand_arrow_24);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
            }
        });

        final EditText converterInput1 = getActivity().findViewById(R.id.converter_input_1);
        final EditText converterInput2 = getActivity().findViewById(R.id.converter_input_2);
        Util.disableKeyboard(converterInput1);
        Util.disableKeyboard(converterInput2);

        final ConverterDialog dialog = new ConverterDialog();
        Button converterBtn1 = getActivity().findViewById(R.id.converter_btn_1);
        Button converterBtn2 = getActivity().findViewById(R.id.converter_btn_2);
        converterBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(), "Dialog");
            }
        });
        converterBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(), "Dialog");
            }
        });
    }
}
