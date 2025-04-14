package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputValue = findViewById(R.id.inputValue);
        Spinner fromUnit = findViewById(R.id.fromUnit);
        Spinner toUnit = findViewById(R.id.toUnit);
        Button convertBtn = findViewById(R.id.convertBtn);
        TextView resultView = findViewById(R.id.resultView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = inputValue.getText().toString().trim();
                if (input.isEmpty()) {
                    resultView.setText("Please enter a number.");
                    return;
                }

                double value = Double.parseDouble(input);
                String from = fromUnit.getSelectedItem().toString();
                String to = toUnit.getSelectedItem().toString();

                double meters = convertToMeters(value, from);
                double converted = convertFromMeters(meters, to);

                resultView.setText(String.format("%.4f %s", converted, to));
            }
        });
    }

    private double convertToMeters(double value, String unit) {
        switch (unit) {
            case "Feet": return value * 0.3048;
            case "Inches": return value * 0.0254;
            case "Centimeters": return value * 0.01;
            case "Meters": return value;
            case "Yards": return value * 0.9144;
            default: return 0;
        }
    }

    private double convertFromMeters(double meters, String unit) {
        switch (unit) {
            case "Feet": return meters / 0.3048;
            case "Inches": return meters / 0.0254;
            case "Centimeters": return meters / 0.01;
            case "Meters": return meters;
            case "Yards": return meters / 0.9144;
            default: return 0;
        }
    }
}
