package io.github.zhmushan.simpletool;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

public class ConverterFragment extends Fragment implements View.OnClickListener {

    private MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn_del,btn_weight,btn_temp,btn_area,btn_len,btn_equal,btn_dot;
    private TextInputEditText editInput1,editOutput1;
    boolean clear_flag;
    int firstChoice1,secondChoice1=0;
    int CBotton1=0;
    String[] unit = {};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View kingDom = inflater.inflate(R.layout.fragment_converter, container, false);

        btn0 = kingDom.findViewById(R.id.zero);
        btn1 = kingDom.findViewById(R.id.one);
        btn2 = kingDom.findViewById(R.id.two);
        btn3 = kingDom.findViewById(R.id.three);
        btn4 = kingDom.findViewById(R.id.four);
        btn5 = kingDom.findViewById(R.id.five);
        btn6 = kingDom.findViewById(R.id.six);
        btn7 = kingDom.findViewById(R.id.seven);
        btn8 = kingDom.findViewById(R.id.eight);
        btn9 = kingDom.findViewById(R.id.nine);
        btn_del = kingDom.findViewById(R.id.del);
        btn_dot = kingDom.findViewById(R.id.btn_dot1);
        editInput1 = kingDom.findViewById(R.id.converter_input_1);
        editOutput1 = kingDom.findViewById(R.id.converter_input_2);
        btn_weight = kingDom.findViewById(R.id.btn_weight);
        btn_area = kingDom.findViewById(R.id.btn_area);
        btn_len = kingDom.findViewById(R.id.btn_len);
        btn_temp = kingDom.findViewById(R.id.btn_temp);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_dot.setOnClickListener(this);

        btn_weight.setOnClickListener(this);
        btn_area.setOnClickListener(this);
        btn_len.setOnClickListener(this);
        btn_temp.setOnClickListener(this);
        clear_flag = false;

        return kingDom;
//        return inflater.inflate(R.layout.fragment_converter, container, false);
    }

    @Override
    public void onClick(View v) {
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(getActivity().findViewById(R.id.bottom_sheet_nav));
        switch (v.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.btn_dot1:
                if (clear_flag) {
                    editOutput1.setText("");
                    editInput1.setText("");
                }
                clear_flag = false;
                editInput1.setText(String.format("%s%s",editInput1.getText(),((Button)v).getText()));
                editInput1.setSelection(editInput1.getText().length()); //修改光标位置
                break;
            case R.id.del:
                char[] cc = editInput1.getText().toString().toCharArray();
                String ss = "";
                for (int i=0;i<cc.length-1;i++){
                    ss = ss + cc[i];
                }
                editInput1.setText(ss);
                editInput1.setSelection(editInput1.getText().length()); //修改光标位置
                editOutput1.setText("");
                break;
            case R.id.btn_len:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                unit = new String[]{"cm","dm", "m", "mm", "km","um","nm","pm"};
                break;
            case R.id.btn_area:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                unit = new String[]{"k㎡","DM", "M", "MM", "KM","UM","NM","PM"};
                break;
//            case R.id.btn_equal:
//                Double ret = 0.0;
//                CalTree cal = new CalTree();
//                ret = cal.start(edtInput.getText().toString());
//                edtAnswer.setText(ret.toString());
//                clear_flag = true;
//                break;
            default:
                break;

        }

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

        final Button converterBtn1 = getActivity().findViewById(R.id.converter_btn_1);
        final Button converterBtn2 = getActivity().findViewById(R.id.converter_btn_2);
        converterBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ConverterDialog dialog = new ConverterDialog(new String[]{"a", "b","c"}, converterBtn1);
                ConverterDialog dialog = new ConverterDialog(unit,converterBtn1);
                dialog.show(getFragmentManager(), "Dialog");
            }
        });
        converterBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConverterDialog(unit, converterBtn2).show(getFragmentManager(), "Dialog");
            }
        });
    }
}
