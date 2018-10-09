package com.android_academy_msk.businesscard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "IMAGE_URL";
    private static final String TITLE_TEXT = "TITLE_TEXT";
    private static final String FULL_TEXT = "FULL_TEXT";
    private static final String DATE_TEXT = "DATE_TEXT";
    private static final String CATEGORY = "CATEGORY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        String imageUrl = getIntent().getStringExtra(IMAGE_URL);
        String titleText = getIntent().getStringExtra(TITLE_TEXT);
        String fullText = getIntent().getStringExtra(FULL_TEXT);
        String dateText = getIntent().getStringExtra(DATE_TEXT);
        String category = getIntent().getStringExtra(CATEGORY);

        ImageView ivToolbar = findViewById(R.id.app_bar_image);
        TextView tvTitle = findViewById(R.id.tvTitle_detail);
        TextView tvFullText = findViewById(R.id.tvFullText_detail);
        TextView tvDateText = findViewById(R.id.tvDate_detail);
        CollapsingToolbarLayout ctl = findViewById(R.id.ctblMain);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTitle.setText(titleText);
        tvFullText.setText(fullText);
        tvDateText.setText(dateText);
        ctl.setTitle(category);
        Glide.with(this).load(imageUrl).into(ivToolbar);
    }

    public static void start(Activity activity, String imageUrl, String titleText, String fullText, String dateText, String category){
        final Intent intent = new Intent(activity, NewsDetailsActivity.class);
        intent.putExtra(IMAGE_URL, imageUrl);
        intent.putExtra(TITLE_TEXT, titleText);
        intent.putExtra(FULL_TEXT, fullText);
        intent.putExtra(DATE_TEXT, dateText);
        intent.putExtra(CATEGORY, category);
        activity.startActivity(intent);
    }
}
