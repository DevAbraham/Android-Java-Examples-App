package com.dev_abraham.mvp_calculator.Models.Interactors;

import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorInteractor;
import com.dev_abraham.mvp_calculator.Models.Interfaces.CalculatorListener;

public class CalculatorInteractorImpl implements CalculatorInteractor {


    @Override
    public void operationInteractor(String num1, String num2, int opc, CalculatorListener listener) {
        num1  = num1.replaceAll("\u00a0", "");
        num2  = num2.replaceAll("\u00a0", "");
        if(num1.equals("")){
            listener.setErrorOpc(0);
            return;
        }else if (num2.equals("")){
            listener.setErrorOpc(1);
            return;
        }
        double n1,n2;
        try {
            n1 = Double.parseDouble(num1);
            n2 = Double.parseDouble(num2);
        }catch(Exception e){
            listener.setErrorOpc(2);
            return;
        }
        //1-sum 2-rest 3-Mult 4-div
        switch (opc){
            case 1 :
                listener.setResult((n1 + n2)+"");
                return;
            case 2 :
                listener.setResult((n1 - n2)+"");
                return;
            case 3 :
                try {
                    listener.setResult((n1 * n2)+"");
                    return;
                }catch (Exception e ){
                    listener.setErrorOpc(2);
                    return;
                }
            case 4 :
                try {
                    listener.setResult((n1 / n2)+"");
                    return;
                }catch (Exception e ){
                    listener.setErrorOpc(2);
                    return;
                }
            default:
                listener.setResult("");
                return;
        }
    }
}
