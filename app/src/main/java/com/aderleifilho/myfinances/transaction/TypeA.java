package com.aderleifilho.myfinances.transaction;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Created by aderleifilho on 8/13/15.
 *
 * This class is used to deal with transactions type A
 *
 */
public class TypeA implements TransactionType {

    private static final String TAG = "MyFinances";

    public double calculate (double value, Date date) {

        Log.i(TAG, "TypeD :: calculate(double " + value + ", Date " + date);

        /**
         *
         * A: Operações do tipo A tem uma taxa de $2 mais 3% do valor da transferência
         *
         */

        double standardTaxAmount = 2.00;
        double standardTaxPercentage = 1.03;
        double newValue;

        newValue = (value + standardTaxAmount) * standardTaxPercentage;

        return newValue;
    }

}
