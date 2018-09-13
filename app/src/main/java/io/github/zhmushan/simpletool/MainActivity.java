package io.github.zhmushan.simpletool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        registerPages();
        if (!Util.bootstrap) {
            Util.bootstrap = true;
            Snackbar.make(findViewById(R.id.main_layout), R.string.author, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
        }
    }

    void initTheme() {
        setTheme(Util.themeId);
    }

    void registerPages() {
        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabs = findViewById(R.id.tabs);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case Page.Calculator:
                        return new CalculatorFragment();
                    case Page.Converter:
                        return new ConverterFragment();
                    case Page.Theme:
                        return new ThemeFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case Page.Calculator:
                        return getString(R.string.calculator);
                    case Page.Converter:
                        return getString(R.string.converter);
                    case Page.Theme:
                        return getString(R.string.theme);
                }
                return null;
            }
        });
        tabs.setupWithViewPager(pager);
    }
}
