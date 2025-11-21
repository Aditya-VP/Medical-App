package com.example.applcation_1;

import android.os.Bundle;
import android.os.Handler;
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

public class AppointmentActivity extends AppCompatActivity {
    private EditText etDoctorName, etDate, etTime;
    private Button btnBook;
    private RecyclerView rvAppointments;
    private List<Appointment> appointmentList = new ArrayList<>();
    private AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        etDoctorName = findViewById(R.id.etDoctorName);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnBook = findViewById(R.id.btnBook);
        rvAppointments = findViewById(R.id.rvAppointments);

        adapter = new AppointmentAdapter(appointmentList);
        rvAppointments.setAdapter(adapter);
        rvAppointments.setLayoutManager(new LinearLayoutManager(this));

        btnBook.setOnClickListener(v -> {
            String doctor = etDoctorName.getText().toString();
            String date = etDate.getText().toString();
            String time = etTime.getText().toString();
            if (!doctor.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                Appointment appt = new Appointment(doctor, date, time);
                appointmentList.add(appt);
                adapter.notifyItemInserted(appointmentList.size() - 1);
                Toast.makeText(this, "Appointment booked!", Toast.LENGTH_SHORT).show();
                etDoctorName.setText(""); etDate.setText(""); etTime.setText("");
                new Handler().postDelayed(() ->
                                NotificationUtils.showNotification(
                                        AppointmentActivity.this,
                                        "Appointment Reminder",
                                        "You have an appointment with Dr. " + doctor + " at " + time + " on " + date
                                ),
                        5000 // 5000 ms = 5 seconds
                );
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class Appointment {
        String doctor, date, time;
        Appointment(String doctor, String date, String time) {
            this.doctor = doctor; this.date = date; this.time = time;
        }
    }

    static class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
        List<Appointment> appointments;
        AppointmentAdapter(List<Appointment> appointments) { this.appointments = appointments; }
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder h, int i) {
            h.tv1.setText("Doctor: " + appointments.get(i).doctor);
            h.tv2.setText(appointments.get(i).date + " @ " + appointments.get(i).time);
        }
        public int getItemCount() { return appointments.size(); }
        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1, tv2;
            ViewHolder(View v) { super(v); tv1 = v.findViewById(android.R.id.text1); tv2 = v.findViewById(android.R.id.text2); }
        }
    }
}
