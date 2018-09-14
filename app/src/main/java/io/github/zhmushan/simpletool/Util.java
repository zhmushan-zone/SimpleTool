package io.github.zhmushan.simpletool;

import android.content.Context;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Util {

    static int themeId = R.style.AppTheme;
    static boolean bootstrap = false;
    static int themeBtnId = R.id.red_theme;

    static void disableKeyboard(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
            }
        }
    }

    static List<Button> createUnitBtnList(Context context, ViewGroup layout, String data[]) {
        List btnList = new ArrayList<Button>();
        if (data != null)
            for (String name : data) {
                Button button = new MaterialButton(context, null, R.attr.navButton);
                button.setText(name);
                btnList.add(button);
                layout.addView(button);
            }
        return btnList;
    }

    static void clearChildBtnIcon(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            MaterialButton button = (MaterialButton) viewGroup.getChildAt(i);
            button.setIconResource(0);
        }
    }
}
