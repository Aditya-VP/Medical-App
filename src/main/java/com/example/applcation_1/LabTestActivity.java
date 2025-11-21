package com.example.applcation_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class LabTestActivity extends AppCompatActivity {
    private EditText etTestName, etScheduledDate;
    private Button btnSchedule;
    private RecyclerView rvLabTests;
    private List<LabTest> labTestList = new ArrayList<>();
    private LabTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        etTestName = findViewById(R.id.etTestName);
        etScheduledDate = findViewById(R.id.etScheduledDate);
        btnSchedule = findViewById(R.id.btnSchedule);
        rvLabTests = findViewById(R.id.rvLabTests);

        adapter = new LabTestAdapter(labTestList);
        rvLabTests.setAdapter(adapter);
        rvLabTests.setLayoutManager(new LinearLayoutManager(this));

        btnSchedule.setOnClickListener(v -> {
            String name = etTestName.getText().toString();
            String date = etScheduledDate.getText().toString();
            if (!name.isEmpty() && !date.isEmpty()) {
                LabTest labTest = new LabTest(name, date);
                labTestList.add(labTest);
                adapter.notifyItemInserted(labTestList.size() - 1);
                Toast.makeText(this, "Lab Test Scheduled!", Toast.LENGTH_SHORT).show();
                etTestName.setText(""); etScheduledDate.setText("");
            } else {
                Toast.makeText(this, "Fill test name and date.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class LabTest {
        String name, date;
        LabTest(String name, String date) { this.name = name; this.date = date; }
    }

    static class LabTestAdapter extends RecyclerView.Adapter<LabTestAdapter.ViewHolder> {
        List<LabTest> tests;
        LabTestAdapter(List<LabTest> tests) { this.tests = tests; }
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder h, int i) {
            h.tv1.setText("Test: " + tests.get(i).name);
            h.tv2.setText("Date: " + tests.get(i).date);
        }
        public int getItemCount() { return tests.size(); }
        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1, tv2;
            ViewHolder(View v) { super(v); tv1 = v.findViewById(android.R.id.text1); tv2 = v.findViewById(android.R.id.text2); }
        }
    }
}
