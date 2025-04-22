package com.example.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends Activity {

    private Button Converter;
    private Button Scientific;

    private TextView InputText;
    private TextView OutputText;

    private Button Nine;
    private Button Eight;
    private Button Seven;
    private Button Six;
    private Button Five;
    private Button Four;
    private Button Three;
    private Button Two;
    private Button One;
    private Button Zero;

    private Button AllClear;
    private Button Clear;
    private Button BackSpace;

    private Button Mul;
    private Button Add;
    private Button Div;
    private Button Sub;
    private Button Power;
    private Button DecimalPoint;


    private Button Equal;


    private String Input = "";
    private String Output = "";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Converter = findViewById(R.id.converter);
        Scientific = findViewById(R.id.scientific);

        InputText = findViewById(R.id.input);
        OutputText = findViewById(R.id.output);

        Nine = findViewById(R.id.nine);
        Eight = findViewById(R.id.eight);
        Seven = findViewById(R.id.seven);
        Six = findViewById(R.id.six);
        Five = findViewById(R.id.five);
        Four = findViewById(R.id.four);
        Three = findViewById(R.id.three);
        Two = findViewById(R.id.two);
        One = findViewById(R.id.one);
        Zero = findViewById(R.id.zero);

        Mul = findViewById(R.id.multiply);
        Div = findViewById(R.id.divide);
        Add = findViewById(R.id.add);
        Sub = findViewById(R.id.subtract);
        Power = findViewById(R.id.power);
        DecimalPoint = findViewById(R.id.decimal_point);


        AllClear = findViewById(R.id.all_clear);
        Clear = findViewById(R.id.clear);
        BackSpace = findViewById(R.id.back_space);

        Equal = findViewById(R.id.equal);

        Nine.setOnClickListener(v -> clicked("9"));
        Eight.setOnClickListener(v -> clicked("8"));
        Seven.setOnClickListener(v -> clicked("7"));
        Six.setOnClickListener(v -> clicked("6"));
        Five.setOnClickListener(v -> clicked("5"));
        Four.setOnClickListener(v -> clicked("4"));
        Three.setOnClickListener(v -> clicked("3"));
        Two.setOnClickListener(v -> clicked("2"));
        One.setOnClickListener(v -> clicked("1"));
        Zero.setOnClickListener(v -> clicked("0"));

        DecimalPoint.setOnClickListener(v -> clicked("."));
        Power.setOnClickListener(v -> clicked("^"));
        Mul.setOnClickListener(v -> clicked("×"));
        Div.setOnClickListener(v -> clicked("÷"));
        Add.setOnClickListener(v -> clicked("+"));
        Sub.setOnClickListener(v -> clicked("-"));

        AllClear.setOnClickListener(v -> clicked("AC"));
        Clear.setOnClickListener(v -> clicked("C"));
        BackSpace.setOnClickListener(v -> clicked("BS"));

        Equal.setOnClickListener(v -> clicked("="));

        Converter.setOnClickListener(v -> android.widget.Toast.makeText(getBaseContext(), "Coming Soon!", Toast.LENGTH_LONG).show());
        Scientific.setOnClickListener(v -> Toast.makeText(getBaseContext(), "Coming Soon!", Toast.LENGTH_LONG).show());

    }

    private void clicked(String character) {

        OutputText.setTextColor(getResources().getColor(R.color.grey));
        InputText.setTextColor(getResources().getColor(R.color.black));

        if (Objects.equals(character, "AC")) {
            Input = "";
            Output = "";
            OutputText.setText(Output);
        } else if (Objects.equals(character, "C")) {
            Input = "";
        } else if (Objects.equals(character, "BS")) {
            if (Input.length() > 1) {
                Input = Input.substring(0, Input.length() - 1);
            } else {
                Input = "";
                OutputText.setText("");
            }
        } else if (Objects.equals(character, "=")) {
            showOutput(true);
        } else {
            Input = Input + character;
        }

        InputText.setText(Input);

        showOutput(false);

    }

    private void showOutput(Boolean isWanted) {

        if (isWanted) {
            OutputText.setTextColor(getResources().getColor(R.color.black));
            InputText.setTextColor(getResources().getColor(R.color.grey));
        }

        Expression expression;

        try {
            expression = new ExpressionBuilder(Input.replaceAll("×", "*").
                    replaceAll("÷", "/")).build();
            try {
                OutputText.setText(new DecimalFormat().format(expression.evaluate()));
            } catch (Exception e) {
                if (!Input.isEmpty()) {
                    InputText.setTextColor(getResources().getColor(R.color.red));
                }
            }
        } catch (Exception e) {
            if (!Input.isEmpty()) {
                InputText.setTextColor(getResources().getColor(R.color.red));
            }
        }
    }
}