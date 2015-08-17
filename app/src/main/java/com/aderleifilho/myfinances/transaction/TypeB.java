package com.aderleifilho.myfinances.transaction;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by aderleifilho on 8/13/15.
 *
 * This class is used to deal with transactions type B
 *
 */
public class TypeB implements TransactionType{

    private static final String TAG = "MyFinances";



    public double calculate (double value, Date date) {

        Log.i(TAG, "TypeD :: calculate(double " + value + ", Date " + date);

        /**
         *
         * B: Operações do tipo B tem uma taxa de:
         *      - $10 para pedidos com agendamento até 30 dias da data de cadastro
         *      - $8 para os demais
         *
         */

        double standardTaxAmountUpTo30 = 10.0;
        double standardTaxAmountAfter30 = 8.0;
        double newValue;
        Date today = new Date();

        long daysDiff = TimeUnit.DAYS.convert((date.getTime() - today.getTime()), TimeUnit.MILLISECONDS);

        if (daysDiff >= 0 && daysDiff <= 30) {
            newValue = value + standardTaxAmountUpTo30;
        } else if (daysDiff > 30) {
            newValue = value + standardTaxAmountAfter30;
        } else {
            newValue = value;
        }
        return newValue;
    }
}