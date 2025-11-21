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

public class MedicineActivity extends AppCompatActivity {
    private EditText etMedicine, etQuantity;
    private Button btnOrder;
    private RecyclerView rvOrders;
    private List<Order> orderList = new ArrayList<>();
    private MedicineOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        etMedicine = findViewById(R.id.etMedicine);
        etQuantity = findViewById(R.id.etQuantity);
        btnOrder = findViewById(R.id.btnOrder);
        rvOrders = findViewById(R.id.rvOrders);

        adapter = new MedicineOrderAdapter(orderList);
        rvOrders.setAdapter(adapter);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));

        btnOrder.setOnClickListener(v -> {
            String name = etMedicine.getText().toString();
            String qty = etQuantity.getText().toString();
            if (!name.isEmpty() && !qty.isEmpty()) {
                Order order = new Order(name, qty);
                orderList.add(order);
                adapter.notifyItemInserted(orderList.size() - 1);
                Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
                etMedicine.setText(""); etQuantity.setText("");
            } else {
                Toast.makeText(this, "Enter medicine and quantity.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class Order {
        String name, qty;
        Order(String name, String qty) { this.name = name; this.qty = qty; }
    }

    static class MedicineOrderAdapter extends RecyclerView.Adapter<MedicineOrderAdapter.ViewHolder> {
        List<Order> orders;
        MedicineOrderAdapter(List<Order> orders) { this.orders = orders; }
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder h, int i) {
            h.tv1.setText("Medicine: " + orders.get(i).name);
            h.tv2.setText("Quantity: " + orders.get(i).qty);
        }
        public int getItemCount() { return orders.size(); }
        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv1, tv2;
            ViewHolder(View v) { super(v); tv1 = v.findViewById(android.R.id.text1); tv2 = v.findViewById(android.R.id.text2); }
        }
    }
}