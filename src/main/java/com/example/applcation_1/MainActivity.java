package com.example.applcation_1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAppointments).setOnClickListener(v ->
                startActivity(new Intent(this, AppointmentActivity.class)));
        findViewById(R.id.btnMedicines).setOnClickListener(v ->
                startActivity(new Intent(this, MedicineActivity.class)));
        findViewById(R.id.btnLabTests).setOnClickListener(v ->
                startActivity(new Intent(this, LabTestActivity.class)));
        findViewById(R.id.btnArticles).setOnClickListener(v ->
                startActivity(new Intent(this, ArticleActivity.class)));
        findViewById(R.id.btnPrescriptions).setOnClickListener(v ->
                startActivity(new Intent(this, PrescriptionActivity.class)));
        findViewById(R.id.btnHistory).setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));
    }
}
