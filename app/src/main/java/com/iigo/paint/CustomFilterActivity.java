package com.iigo.paint;

import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/22 0022 10:13
 */
public class CustomFilterActivity extends AppCompatActivity {
    /**
     * <pre>
     *  [ a, b, c, d, e,
     *    f, g, h, i, j,
     *    k, l, m, n, o,
     *    p, q, r, s, t ]</pre>
     *
     */
    private EditText et11;
    private EditText et12;
    private EditText et13;
    private EditText et14;
    private EditText et15;

    private EditText et21;
    private EditText et22;
    private EditText et23;
    private EditText et24;
    private EditText et25;

    private EditText et31;
    private EditText et32;
    private EditText et33;
    private EditText et34;
    private EditText et35;

    private EditText et41;
    private EditText et42;
    private EditText et43;
    private EditText et44;
    private EditText et45;

    private FilterView filterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        initViews();
    }

    private void initViews() {
        et11 = findViewById(R.id.et_11);
        et12 = findViewById(R.id.et_12);
        et13 = findViewById(R.id.et_13);
        et14 = findViewById(R.id.et_14);
        et15 = findViewById(R.id.et_15);

        et21 = findViewById(R.id.et_21);
        et22 = findViewById(R.id.et_22);
        et23 = findViewById(R.id.et_23);
        et24 = findViewById(R.id.et_24);
        et25 = findViewById(R.id.et_25);

        et31 = findViewById(R.id.et_31);
        et32 = findViewById(R.id.et_32);
        et33 = findViewById(R.id.et_33);
        et34 = findViewById(R.id.et_34);
        et35 = findViewById(R.id.et_35);

        et41 = findViewById(R.id.et_41);
        et42 = findViewById(R.id.et_42);
        et43 = findViewById(R.id.et_43);
        et44 = findViewById(R.id.et_44);
        et45 = findViewById(R.id.et_45);

        et11.addTextChangedListener(new MyTextWatcher(et11, "1"));
        et12.addTextChangedListener(new MyTextWatcher(et12, "0"));
        et13.addTextChangedListener(new MyTextWatcher(et13, "0"));
        et14.addTextChangedListener(new MyTextWatcher(et14, "0"));
        et15.addTextChangedListener(new MyTextWatcher(et15, "0"));

        et21.addTextChangedListener(new MyTextWatcher(et21, "0"));
        et22.addTextChangedListener(new MyTextWatcher(et22, "1"));
        et23.addTextChangedListener(new MyTextWatcher(et23, "0"));
        et24.addTextChangedListener(new MyTextWatcher(et24, "0"));
        et25.addTextChangedListener(new MyTextWatcher(et25, "0"));

        et31.addTextChangedListener(new MyTextWatcher(et31, "0"));
        et32.addTextChangedListener(new MyTextWatcher(et32, "0"));
        et33.addTextChangedListener(new MyTextWatcher(et33, "1"));
        et34.addTextChangedListener(new MyTextWatcher(et34, "0"));
        et35.addTextChangedListener(new MyTextWatcher(et35, "0"));

        et41.addTextChangedListener(new MyTextWatcher(et41, "0"));
        et42.addTextChangedListener(new MyTextWatcher(et42, "0"));
        et43.addTextChangedListener(new MyTextWatcher(et43, "0"));
        et44.addTextChangedListener(new MyTextWatcher(et44, "1"));
        et45.addTextChangedListener(new MyTextWatcher(et45, "0"));

        filterView = findViewById(R.id.fv_filter);
    }

    public void onRecover(View view){
        et11.setText("1");
        et12.setText("0");
        et13.setText("0");
        et14.setText("0");
        et15.setText("0");

        et21.setText("0");
        et22.setText("1");
        et23.setText("0");
        et24.setText("0");
        et25.setText("0");

        et31.setText("0");
        et32.setText("0");
        et33.setText("1");
        et34.setText("0");
        et35.setText("0");

        et41.setText("0");
        et42.setText("0");
        et43.setText("0");
        et44.setText("1");
        et45.setText("0");
    }

    public void onDraw(View view){
        //若EditText为空，默认值为0

        int  n11 = Integer.valueOf(et11.getText().toString());
        int  n12 = Integer.valueOf(et12.getText().toString());
        int  n13 = Integer.valueOf(et13.getText().toString());
        int  n14 = Integer.valueOf(et14.getText().toString());
        int  n15 = Integer.valueOf(et15.getText().toString());

        int  n21 = Integer.valueOf(et21.getText().toString());
        int  n22 = Integer.valueOf(et22.getText().toString());
        int  n23 = Integer.valueOf(et23.getText().toString());
        int  n24 = Integer.valueOf(et24.getText().toString());
        int  n25 = Integer.valueOf(et25.getText().toString());

        int  n31 = Integer.valueOf(et31.getText().toString());
        int  n32 = Integer.valueOf(et32.getText().toString());
        int  n33 = Integer.valueOf(et33.getText().toString());
        int  n34 = Integer.valueOf(et34.getText().toString());
        int  n35 = Integer.valueOf(et35.getText().toString());

        int  n41 = Integer.valueOf(et41.getText().toString());
        int  n42 = Integer.valueOf(et42.getText().toString());
        int  n43 = Integer.valueOf(et43.getText().toString());
        int  n44 = Integer.valueOf(et44.getText().toString());
        int  n45 = Integer.valueOf(et45.getText().toString());

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                n11, n12, n13, n14, n15,
                n21, n22, n23, n24, n25,
                n31, n32, n33, n34, n35,
                n41, n42, n43, n44, n45,
        });

        filterView.drawCustom(colorMatrix);
    }

    private class MyTextWatcher implements TextWatcher {
        private EditText editText;
        private String defaultValue;

        public MyTextWatcher(EditText editText, String defaultValue){
            this.editText = editText;
            this.defaultValue = defaultValue;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (TextUtils.isEmpty(s.toString())){
                editText.setText(defaultValue);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
