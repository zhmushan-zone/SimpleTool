package io.github.zhmushan.simpletool;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConverterDialog extends BottomSheetDialogFragment {
    private String data[];
    private String selected;
    private View view;

    public ConverterDialog() {
    }

    @SuppressLint("ValidFragment")
    public ConverterDialog(String arr[], View v) {
        data = arr;
        view = v;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.converter_dialog, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Button> btnList = Util.createUnitBtnList(
                getActivity(),
                (LinearLayout) getView().findViewById(R.id.converter_dialog_list),
                data
        );
        for (Button btn : btnList) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = ((Button) v).getText().toString();
                    dismiss();
                }
            });
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (selected != null) {
            ((Button) view).setText(selected);
        }
    }

//    public String findSelect() {
//        return selected;
//    }
}
