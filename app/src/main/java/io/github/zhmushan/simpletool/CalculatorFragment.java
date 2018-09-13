package io.github.zhmushan.simpletool;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Method;

import androidx.annotation.Nullable;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn_add,btn_minus,btn_mult,btn_division,button_c,btn_equal,btn_dot;
    private TextInputEditText edtInput,edtAnswer;
//    private String inputString1,inputString2,outputString,bufString;
//    private int number1,number2,numberOutput;
//    private int state,symbol,count;
    boolean clear_flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View square = inflater.inflate(R.layout.fragment_calculator, container, false);

        btn0 = square.findViewById(R.id.btn0);
        btn1 = square.findViewById(R.id.btn1);
        btn2 = square.findViewById(R.id.btn2);
        btn3 = square.findViewById(R.id.btn3);
        btn4 = square.findViewById(R.id.btn4);
        btn5 = square.findViewById(R.id.btn5);
        btn6 = square.findViewById(R.id.btn6);
        btn7 = square.findViewById(R.id.btn7);
        btn8 = square.findViewById(R.id.btn8);
        btn9 = square.findViewById(R.id.btn9);
        btn_add = square.findViewById(R.id.btn_add);
        btn_minus = square.findViewById(R.id.btn_minus);
        btn_mult = square.findViewById(R.id.btn_mult);
        btn_division = square.findViewById(R.id.btn_division);
        button_c = square.findViewById(R.id.button_c);
        btn_equal = square.findViewById(R.id.btn_equal);
        edtInput = square.findViewById(R.id.inputTextField);
        edtAnswer = square.findViewById(R.id.answerTextField);
        btn_dot = square.findViewById(R.id.btn_dot);

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
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mult.setOnClickListener(this);
        btn_division.setOnClickListener(this);
        button_c.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_division.setOnClickListener(this);

        clear_flag = false;






//        return inflater.inflate(R.layout.fragment_calculator, container, false);
        return square;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText editText = getActivity().findViewById(R.id.inputTextField);
        Util.disableKeyboard(editText);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btn_minus:
            case R.id.btn_mult:
            case R.id.btn_division:
            case R.id.btn_dot:
            case R.id.btn_add:
                if (clear_flag) {
                    edtInput.setText("");
//                    edtAnswer.setText("");
                }
                clear_flag = false;

                edtInput.setText(String.format("%s%s",edtInput.getText(),((Button)v).getText()));
                break;
            case R.id.button_c:
                edtInput.setText("");
                edtAnswer.setText("");
                clear_flag = false;
                break;
            case R.id.btn_equal:
                Double ret = 0.0;
                CalTree cal = new CalTree();
                ret = cal.start(edtInput.getText().toString());
                edtAnswer.setText(ret.toString());
                clear_flag = true;
                break;
            default:
                break;

        }
    }
}
