package com.example.textcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textcounter.utils.TextUtils;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    TextView tvInputText;
    Spinner calculateOption;
    String[] calculateOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvOutput = findViewById(R.id.tvOutput);
        this.tvInputText = findViewById(R.id.tvInputText);
        this.calculateOption = findViewById(R.id.calculateOption);
        this.calculateOptions = getResources().getStringArray(R.array.count_options);
    }

    @SuppressLint("ResourceType")
    public void onBtnCalculateClick(View view) {
        String input = this.tvInputText.getText().toString();
        input = input.trim();
        if(input.isEmpty()) {
            Toast.makeText(MainActivity.this, getString(R.string.input_not_filled), Toast.LENGTH_SHORT).show();
            return;
        }
        String calculateOption = this.calculateOption.getSelectedItem().toString();
        if(calculateOption.equalsIgnoreCase(this.calculateOptions[0])) {
            this.tvOutput.setText(String.format(getString(R.string.output_words_count), TextUtils.getWordCount(input)));
        } else {
            this.tvOutput.setText(String.format(getString(R.string.output_char_count), TextUtils.getCharCount(input)));
        }
    }
}