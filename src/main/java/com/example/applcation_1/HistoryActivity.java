package com.example.applcation_1;

import android.annotation.SuppressLint;
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

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvHistory;
    private List<HistoryItem> history = new ArrayList<>();
    private HistoryAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);

        // Sample history
        history.add(new HistoryItem("Appointment with Dr. Smith", "21/11/2025, 10:00 AM"));
        history.add(new HistoryItem("Lab Test: Blood Count", "20/11/2025, 9:00 AM"));
        history.add(new HistoryItem("Ordered: Paracetamol x10", "18/11/2025, 7:00 PM"));

        adapter = new HistoryAdapter(history);
        rvHistory.setAdapter(adapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
    }

    static class HistoryItem {
        String title, date;
        HistoryItem(String title, String date) { this.title = title; this.date = date; }
    }

    static class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
        List<HistoryItem> history;
        HistoryAdapter(List<HistoryItem> history) { this.history = history; }
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder h, int i) {
            h.tv1.setText(history.get(i).title);
            h.tv2.setText(history.get(i).date);
        }
        public int getItemCount() { return history.size(); }
        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1, tv2;
            ViewHolder(View v) { super(v); tv1 = v.findViewById(android.R.id.text1); tv2 = v.findViewById(android.R.id.text2); }
        }
    }
}
