package com.android_academy_msk.businesscard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NewsListActivity extends AppCompatActivity {

    private final NewsAdapter.OnItemClickListener clickListener = news_item -> {
        String imageUrl = news_item.getImageUrl();
        String titleText = news_item.getTitle();
        String fullText = news_item.getFullText();
        String dateText = news_item.getNormalDate();
        String category = news_item.getCategory().getName();
        openDetails(imageUrl, titleText, fullText, dateText, category);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new NewsAdapter(DataUtils.generateNews(), this, clickListener));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.menu_about){
            openAbout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDetails(String img, String title, String fullText, String date, String category){
        NewsDetailsActivity.start(NewsListActivity.this, img, title, fullText, date, category);
    }

    private void openAbout(){
        AboutActivity.start(NewsListActivity.this);
    }
}
