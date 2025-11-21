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

public class PrescriptionActivity extends AppCompatActivity {
    private EditText etPrescription;
    private Button btnAddPrescription;
    private RecyclerView rvPrescriptions;
    private List<String> prescriptions = new ArrayList<>();
    private PrescriptionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        etPrescription = findViewById(R.id.etPrescription);
        btnAddPrescription = findViewById(R.id.btnAddPrescription);
        rvPrescriptions = findViewById(R.id.rvPrescriptions);

        adapter = new PrescriptionAdapter(prescriptions);
        rvPrescriptions.setAdapter(adapter);
        rvPrescriptions.setLayoutManager(new LinearLayoutManager(this));

        btnAddPrescription.setOnClickListener(v -> {
            String text = etPrescription.getText().toString();
            if (!text.isEmpty()) {
                prescriptions.add(text);
                adapter.notifyItemInserted(prescriptions.size() - 1);
                etPrescription.setText("");
                Toast.makeText(this, "Prescription saved!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Enter prescription details.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.ViewHolder> {
        List<String> prescriptions;
        PrescriptionAdapter(List<String> prescriptions) { this.prescriptions = prescriptions; }
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder h, int i) {
            h.tv.setText(prescriptions.get(i));
        }
        public int getItemCount() { return prescriptions.size(); }
        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv;
            ViewHolder(View v) { super(v); tv = v.findViewById(android.R.id.text1); }
        }
    }
}
