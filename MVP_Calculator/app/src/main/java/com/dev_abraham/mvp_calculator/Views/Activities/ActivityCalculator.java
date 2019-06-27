package com.dev_abraham.mvp_calculator.Views.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorPresenter;
import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorView;
import com.dev_abraham.mvp_calculator.Presenters.CalculatorPresenterImpl;
import com.dev_abraham.mvp_calculator.R;

public class ActivityCalculator extends AppCompatActivity implements CalculatorView {


    private EditText etNum1,etNum2;
    private Button btnSum,btnMult,btnDiv,btnRest;
    private TextView tvResult;

    private CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etNum1 = findViewById(R.id.etNumberOne);
        etNum2 = findViewById(R.id.etNumberTwo);

        btnSum = findViewById(R.id.btnSum);
        btnSum.setOnClickListener(btnSumClick);

        btnDiv = findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(btnDivClick);

        btnMult = findViewById(R.id.btnMult);
        btnMult.setOnClickListener(btnMultClick);

        btnRest = findViewById(R.id.btnRest);
        btnRest.setOnClickListener(btnRestClick);


        tvResult = findViewById(R.id.tvResult);

        calculatorPresenter = new CalculatorPresenterImpl(this);

    }

    private View.OnClickListener btnSumClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorPresenter.opcStart(etNum1.getText().toString(),etNum2.getText().toString(),1);         }
    };

    private View.OnClickListener btnRestClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorPresenter.opcStart(etNum1.getText().toString(),etNum2.getText().toString(),2);         }
    };

    private View.OnClickListener btnMultClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorPresenter.opcStart(etNum1.getText().toString(),etNum2.getText().toString(),3);         }
    };

    private View.OnClickListener btnDivClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorPresenter.opcStart(etNum1.getText().toString(),etNum2.getText().toString(),4);         }
    };



    @Override
    public void setResult(String result) {
        tvResult.setText("Resultado: "+result);
    }

    @Override
    public void setErrorNum1() {

        etNum1.setError("Campo Obligatorio");
    }

    @Override
    public void setErrorNum2() {
        etNum2.setError("Campo Obligatorio");
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Operacion No Valida", Toast.LENGTH_SHORT).show();
    }
}
