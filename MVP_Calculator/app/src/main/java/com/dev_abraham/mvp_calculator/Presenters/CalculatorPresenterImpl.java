package com.dev_abraham.mvp_calculator.Presenters;

import com.dev_abraham.mvp_calculator.Models.Interactors.CalculatorInteractorImpl;
import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorInteractor;
import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorListener;
import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorPresenter;
import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorView;

public class CalculatorPresenterImpl implements CalculatorPresenter , CalculatorListener {


    private CalculatorView view;
    private CalculatorInteractor calculatorInteractor;



    public CalculatorPresenterImpl(CalculatorView view){
        this.view=view;
        calculatorInteractor = new CalculatorInteractorImpl();

    }

    @Override
    public void opcStart(String number1, String number2, int opc) {
        calculatorInteractor.operationInteractor(number1,number2,opc,this);

    }


    @Override
    public void setResult(String result) {
        view.setResult(result);
    }

    @Override
    public void setErrorOpc( int errorOpc) {
        if(errorOpc == 0){
            view.setErrorNum1();
        }else if (errorOpc == 1){
            view.setErrorNum2();
        }else{
            view.showError();
        }

    }


}
