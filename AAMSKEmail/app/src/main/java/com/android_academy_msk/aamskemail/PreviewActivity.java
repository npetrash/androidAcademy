package com.android_academy_msk.aamskemail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    public static final String KEY_EMAIL = "KEY_EMAIL";
    private TextView tvPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        tvPreview = findViewById(R.id.tvPreview);
        tvPreview.setText(getIntent().getStringExtra(KEY_EMAIL));
    }
}
