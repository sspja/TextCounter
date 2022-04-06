package com.example.textcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.textcounter.utils.TextUtils;

public class MainActivity extends AppCompatActivity {

    TextView tvOutput;
    TextView tvInputText;
    Spinner calculateOption;
    String[] calculateOptions;
    Button btnShow;
    ImageView imageView;
    TextView tvInputTicker;
    String ticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvOutput = findViewById(R.id.tvOutput);
        this.tvInputText = findViewById(R.id.tvInputText);
        this.calculateOption = findViewById(R.id.calculateOption);
        this.calculateOptions = getResources().getStringArray(R.array.count_options);
        this.btnShow = (Button)findViewById(R.id.btnShow);
        this.imageView = (ImageView)findViewById(R.id.imageView);
        this.tvInputTicker = findViewById(R.id.tvInputTicker);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        final Python py = Python.getInstance();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticker = tvInputTicker.getText().toString().toUpperCase();
                if(!ticker.isEmpty()) {
                    PyObject pyObj = py.getModule("myscript");
                    PyObject obj = pyObj.callAttr("main", ticker);

                    String imgStr = obj.toString();
                    byte data[] = android.util.Base64.decode(imgStr, Base64.DEFAULT);
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    imageView.setImageBitmap(bmp);
                }
            }
        });
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