package com.aderleifilho.myfinances.transaction;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by aderleifilho on 8/13/15.
 *
 * This class is used to deal with transactions type C
 *
 */
public class TypeC implements TransactionType {

    private static final String TAG = "MyFinances";


    public double calculate (double value, Date date) {

        Log.i(TAG, "TypeD :: calculate(double " + value + ", Date " + date);

        /**
         *
         * C: Operações do tipo C tem uma taxa regressiva conforme a data de agendamento:
         *       - maior que 30 dias da data de cadastro - 1.2%
         *       - até 30 dias da data de cadastro - 2.1%
         *       - até 25 dias da data de cadastro - 4.3%
         *       - até 20 dias da data de cadastro - 5.4%
         *       - até 15 dias da data de cadastro - 6.7%
         *       - até 10 dias da data de cadastro - 7.4%
         *       - até  5 dias da data de cadastro - 8.3%
         *
         */

        double standardTaxAmountUpTo5 = 1.083;
        double standardTaxAmountUpTo10 = 1.074;
        double standardTaxAmountUpTo15 = 1.067;
        double standardTaxAmountUpTo20 = 1.054;
        double standardTaxAmountUpTo25 = 1.043;
        double standardTaxAmountUpTo30 = 1.021;
        double standardTaxAmountAfter30 = 1.012;
        double newValue;
        Date today = new Date();

        long daysDiff = TimeUnit.DAYS.convert((date.getTime() - today.getTime()), TimeUnit.MILLISECONDS);

        if (daysDiff >= 0 && daysDiff < 5) {
            newValue = value * standardTaxAmountUpTo5;
        } else if (daysDiff > 5 && daysDiff < 10) {
            newValue = value * standardTaxAmountUpTo10;
        } else if (daysDiff > 10 && daysDiff < 15) {
            newValue = value * standardTaxAmountUpTo15;
        } else if (daysDiff > 15 && daysDiff < 20) {
            newValue = value * standardTaxAmountUpTo20;
        } else if (daysDiff > 20 && daysDiff < 25) {
            newValue = value * standardTaxAmountUpTo25;
        } else if (daysDiff > 25 && daysDiff < 30) {
            newValue = value * standardTaxAmountUpTo30;
        } else if (daysDiff > 30) {
            newValue = value * standardTaxAmountAfter30;
        } else {
            newValue = value;
        }

        return newValue;
    }
}
