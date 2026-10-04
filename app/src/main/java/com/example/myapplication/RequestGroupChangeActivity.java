package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestGroupChangeActivity extends AppCompatActivity {

    private TextView tvCurrentGroups;
    private Spinner spinnerRequestedGroup;
    private EditText etReason;
    private TextView tvCharCount;
    private Button btnSubmitRequest;

    private static final int MAX_REASON_LENGTH = 200;

    private final List<String> currentGroups = new ArrayList<>(Arrays.asList("G02", "G03", "G05"));

    private final List<String> availableGroups = new ArrayList<>(Arrays.asList(
            "Select group", "G01", "G02", "G03", "G04", "G05"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_group_change);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        tvCurrentGroups = findViewById(R.id.tvCurrentGroups);
        spinnerRequestedGroup = findViewById(R.id.spinnerRequestedGroup);
        etReason = findViewById(R.id.etReason);
        tvCharCount = findViewById(R.id.tvCharCount);
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest);

        setUpCurrentGroups();
        setUpGroupSpinner();
        setUpReasonCounter();
        setUpSubmitButton();
    }

    private void setUpCurrentGroups() {
        tvCurrentGroups.setText(String.join(", ", currentGroups));
    }

    private void setUpGroupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, availableGroups);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRequestedGroup.setAdapter(adapter);
    }

    private void setUpReasonCounter() {
        etReason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCharCount.setText(s.length() + "/" + MAX_REASON_LENGTH);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    private void setUpSubmitButton() {
        btnSubmitRequest.setOnClickListener(v -> submitRequest());
    }

    private void submitRequest() {
        String requestedGroup = (String) spinnerRequestedGroup.getSelectedItem();
        String reason = etReason.getText().toString().trim();

        if (requestedGroup == null || requestedGroup.equals("Select group")) {
            Toast.makeText(this, "Please choose the group you want to move to", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentGroups.contains(requestedGroup)) {
            Toast.makeText(this, "You are already in " + requestedGroup, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Request to move to " + requestedGroup + " submitted", Toast.LENGTH_LONG).show();
        finish();
    }
}