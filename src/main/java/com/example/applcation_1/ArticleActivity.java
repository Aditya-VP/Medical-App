package com.example.applcation_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArticleActivity extends AppCompatActivity {
    private RecyclerView rvArticles;
    private List<Article> articles = new ArrayList<>();
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        rvArticles = findViewById(R.id.rvArticles);

        // Sample articles
        articles.add(new Article("Why Sleep Matters", "Learn about health effects of sleep. Sleep improves immunity and mental health..."));
        articles.add(new Article("Healthy Eating", "Tips to eat better for improved focus and heart health."));
        articles.add(new Article("Exercise for Life", "Simple daily exercises for a healthy body and mind."));

        adapter = new ArticleAdapter(articles);
        rvArticles.setAdapter(adapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));
    }

    static class Article {
        String title, body;
        Article(String title, String body) { this.title = title; this.body = body; }
    }

    static class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
        List<Article> articles;
        ArticleAdapter(List<Article> articles) { this.articles = articles; }
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder h, int i) {
            h.tv1.setText(articles.get(i).title);
            h.tv2.setText(articles.get(i).body);
        }
        public int getItemCount() { return articles.size(); }
        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1, tv2;
            ViewHolder(View v) { super(v); tv1 = v.findViewById(android.R.id.text1); tv2 = v.findViewById(android.R.id.text2); }
        }
    }
}
