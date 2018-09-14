package io.github.zhmushan.simpletool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConverterFragment extends Fragment implements View.OnClickListener  {

    private MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn_del,btn_weight,btn_temp,btn_area,btn_len,btn_dot,btn_convert1,btn_convert2;
    private TextInputEditText editInput1,editOutput1;
    boolean clear_flag;
    String unitSelected;
    Double first,second,return_unit,plus_d;
    String[] unit = {"cm","dm", "m", "mm", "km","um","nm","pm"};

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
        btn_convert1 = kingDom.findViewById(R.id.converter_btn_1);
        btn_convert2 = kingDom.findViewById(R.id.converter_btn_2);

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
                getVal(); //获取第一个数
                plus_d = trans_unit();
                second = mul(first,plus_d);
//                second = first * plus_d;
                editOutput1.setText(String.format("%s",second));
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
                unit = new String[]{"KM²","ha", "are", "M²", "DM²","CM²","MM²"};
                break;
            case R.id.btn_temp:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                unit = new String[]{"℉","℃","°Re","°R","K"};
                break;
            case R.id.btn_weight:
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                unit = new String[]{"kg","t","g","mg","μg","ct","q"};
                break;
            default:
//                if (editInput1!=null){
//                    btn_convert1.getText().toString();
////                    ConverterDialog find = new ConverterDialog();
////                    unitSelected = find.findSelect();
////                    Double uniChange = 0.0;
////
//                }

                break;

        }

    }
    public static double mul(double a1, double b1) {
        BigDecimal a2 = new BigDecimal(Double.toString(a1));
        BigDecimal b2 = new BigDecimal(Double.toString(b1));
        return a2.multiply(b2).doubleValue();
    }
    public Double getVal() {
        if (!editInput1.getText().toString().equals("")&&!editInput1.getText().toString().equals(".")) {
            first = Double.parseDouble(editInput1.getText().toString());
            return first;
        }
        return null;
    }

    public Double trans_unit() {
        String ori_unit = btn_convert1.getText().toString();
        String second_unit = btn_convert2.getText().toString();
        Double d2 = 0.0;
        if ("cm".equals(ori_unit) || "dm".equals(ori_unit) || "m".equals(ori_unit) ||  "mm".equals(ori_unit)|| "km".equals(ori_unit) || "um".equals(ori_unit) || "pm".equals(ori_unit)||"nm".equals(ori_unit)){
            if ("cm".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 0.1;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 0.01;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 10.0;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 10000.0;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 10000000000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 10000000.0;
                    return d2;
                }
            }else if ("dm".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 10.0;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 0.1;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 100.0;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 100000000.0;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 100000000000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 100000000.0;
                    return d2;
                }
            }else if ("m".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 100.0;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 10.0;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 1000.0;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 1000000.0;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 1000000000000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 1000000000.0;
                    return d2;
                }
            }else if ("mm".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 0.1;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 0.01;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 1000.0;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 1000000000000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 1000000000.0;
                    return d2;
                }
            }else if ("km".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 100000.0;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 10000.0;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 1000.0;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 1000000.0;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 1000000000.0;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 10000000000000000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 1000000000000.0;
                    return d2;
                }
            }else if ("um".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 10000.0;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.000000001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 1000000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 1000.0;
                    return d2;
                }
            }else if ("nm".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 0.0000001;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 0.00000001;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 0.000000001;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 1000.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("pm".equals(ori_unit)){
                if ("cm".equals(second_unit)){
                    d2 = 0.0000000001;
                    return d2;
                }else if ("dm".equals(second_unit)){
                    d2 = 0.000000000001;
                    return d2;
                }else if ("m".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("mm".equals(second_unit)){
                    d2 = 0.0000000001;
                    return d2;
                }else if ("km".equals(second_unit)){
                    d2 = 0.000000000000001;
                    return d2;
                }else if ("um".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("pm".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("nm".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }
            }
        }else if ("KM²".equals(ori_unit) || "ha".equals(ori_unit) || "are".equals(ori_unit) ||  "M²".equals(ori_unit)|| "DM²".equals(ori_unit) || "CM²".equals(ori_unit) || "MM²".equals(ori_unit)) {
            if ("KM²".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("ha".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("are".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("M²".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("DM²".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("CM²".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }else if ("MM²".equals(ori_unit)){
                if ("KM²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("ha".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("are".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("M²".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("DM²".equals(second_unit)){
                    d2 = 0.00000000001;
                    return d2;
                }else if ("CM²".equals(second_unit)){
                    d2 = 0.001;
                    return d2;
                }else if ("MM²".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }
        }else if ("℉".equals(ori_unit) || "℃".equals(ori_unit) || "°Re".equals(ori_unit) ||  "°R".equals(ori_unit)|| "K".equals(ori_unit)){
            if ("℉".equals(ori_unit)){
                if ("℉".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("℃".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("°Re".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("°R".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("K".equals(second_unit)){
                    d2 = 0.00000001;
                    return d2;
                }
            }else if ("℃".equals(ori_unit)){
                if ("℉".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("℃".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("°Re".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("°R".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("K".equals(second_unit)){
                    d2 = 0.00000001;
                    return d2;
                }
            }else if ("°Re".equals(ori_unit)){
                if ("℉".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("℃".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("°Re".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("°R".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("K".equals(second_unit)){
                    d2 = 0.00000001;
                    return d2;
                }
            }else if ("°R".equals(ori_unit)){
                if ("℉".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("℃".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("°Re".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("°R".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("K".equals(second_unit)){
                    d2 = 0.00000001;
                    return d2;
                }
            }else if ("K".equals(ori_unit)){
                if ("℉".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("℃".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("°Re".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("°R".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("K".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }
            }
        }else if ("kg".equals(ori_unit) || "t".equals(ori_unit) || "g".equals(ori_unit) || "mg".equals(ori_unit)|| "μg".equals(ori_unit) ||"ct".equals(ori_unit) ||"q".equals(ori_unit)){
            if ("kg".equals(ori_unit)){
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }else if ( "t".equals(ori_unit)) {
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }else if ("g".equals(ori_unit)){
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }else if ("mg".equals(ori_unit)){
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }else if ( "μg".equals(ori_unit) ){
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }else if ("ct".equals(ori_unit)){
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }else if ("q".equals(ori_unit)) {
                if ("kg".equals(second_unit)){
                    d2 = 0.00001;
                    return d2;
                }else if ("t".equals(second_unit)){
                    d2 = 0.000001;
                    return d2;
                }else if ("g".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("mg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("μg".equals(second_unit)){
                    d2 = 1.0;
                    return d2;
                }else if ("ct".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }else if ("q".equals(second_unit)){
                    d2 = 0.0001;
                    return d2;
                }
            }
        }
        return null;
    }
    public String show1(int f,int s){
        Double d1 = Double.parseDouble(editInput1.getText().toString());
        Double d2 = 0.0;
        if(f == 0){
            if(s == 0){
                d2 = 1.0;
            }
            else if(s == 1){
                d2 = 10.0;
            }
            else {
                d2 = 100.0;
            }
        }
        else if(f == 1){
            if(s == 0){
                d2 = 0.1;
            }
            else if(s == 1){
                d2 = 1.0;
            }
            else {
                d2 = 10.0;
            }
        }
        else {
            if(s == 0){
                d2 = 0.01;
            }
            else if(s == 1){
                d2 = 0.1;
            }
            else {
                d2 = 1.0;
            }
        }
        return (d1 * d2 ) + "";
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
