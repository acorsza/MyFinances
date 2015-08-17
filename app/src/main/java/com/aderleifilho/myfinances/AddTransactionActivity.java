package com.aderleifilho.myfinances;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aderleifilho.myfinances.transaction.TypeA;
import com.aderleifilho.myfinances.transaction.TypeB;
import com.aderleifilho.myfinances.transaction.TypeC;
import com.aderleifilho.myfinances.transaction.TypeD;

public class AddTransactionActivity extends Activity {

    private static final String TAG = "MyFinances";

    private static String dataDaTransferenciaString;
    private static TextView dataDaTransferenciaTextView;
    private static TextView totalTextView;
    private static TextView taxaTextView;

    private EditText contaOrigem;
    private EditText contaDestino;
    private EditText valorDaTransferencia;
    private RadioGroup tipoDeTransferenciaRadioGroup;
    private static Date dataDaTransferencia;

    private double valorDaTransferenciaDouble;
    private double totalCalculado;
    private double taxaCalculada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        contaOrigem = (EditText) findViewById(R.id.editTextContaOrigem);
        contaDestino = (EditText) findViewById(R.id.editTextContaDestino);
        valorDaTransferencia = (EditText) findViewById(R.id.editTextValorDaTransferencia);
        tipoDeTransferenciaRadioGroup = (RadioGroup) findViewById(R.id.radioGroupTipoDeTransferencia);
        dataDaTransferenciaTextView = (TextView) findViewById(R.id.textViewData);
        totalTextView = (TextView) findViewById(R.id.textViewTotal);
        taxaTextView = (TextView) findViewById(R.id.textViewTaxa);

        final Button datePickerButton = (Button) findViewById(R.id.buttonData);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Botão Data - Selecionar Data");
                exibirDatePicker();
            }
        });

        final Button cancelButton = (Button) findViewById(R.id.buttonCancelar);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Botão Cancelar - Transferência Cancelada");
                finish();
            }
        });

        valorDaTransferencia.setOnKeyListener(new EditText.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(!valorDaTransferencia.getText().toString().equals("")) {
                    valorDaTransferenciaDouble = Double.parseDouble(valorDaTransferencia.getText().toString());
                }
                return false;
            }
        });

        valorDaTransferencia.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!valorDaTransferencia.getText().toString().equals("")) {
                    valorDaTransferenciaDouble = Double.parseDouble(valorDaTransferencia.getText().toString());
                }
            }
        });

        tipoDeTransferenciaRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (tipoDeTransferenciaRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_tipo_a: {
                        if(dataDaTransferencia != null) {
                            Log.i(TAG, "Tipo de Transferência selecionado - Tipo A");
                            TypeA tipoA = new TypeA();
                            totalCalculado = tipoA.calculate(valorDaTransferenciaDouble, dataDaTransferencia);
                            taxaCalculada = totalCalculado - valorDaTransferenciaDouble;
                            calcularTaxa(String.valueOf(String.format("%.2f", taxaCalculada)));
                            calcularTotal(String.valueOf(totalCalculado));
                        }
                        break;
                    }
                    case R.id.radio_tipo_b: {
                        if(dataDaTransferencia != null) {
                            Log.i(TAG, "Tipo de Transferência selecionado - Tipo B");
                            TypeB tipoB = new TypeB();
                            totalCalculado = tipoB.calculate(valorDaTransferenciaDouble, dataDaTransferencia);
                            taxaCalculada = totalCalculado - valorDaTransferenciaDouble;
                            calcularTaxa(String.valueOf(String.format("%.2f", taxaCalculada)));
                            calcularTotal(String.valueOf(totalCalculado));
                        }
                        break;
                    }
                    case R.id.radio_tipo_c: {
                        if(dataDaTransferencia != null) {
                            Log.i(TAG, "Tipo de Transferência selecionado - Tipo C");
                            TypeC tipoC = new TypeC();
                            totalCalculado = tipoC.calculate(valorDaTransferenciaDouble, dataDaTransferencia);
                            taxaCalculada = totalCalculado - valorDaTransferenciaDouble;
                            calcularTaxa(String.valueOf(String.format("%.2f", taxaCalculada)));
                            calcularTotal(String.valueOf(totalCalculado));
                        }
                        break;
                    }
                    case R.id.radio_tipo_d: {
                        if(dataDaTransferencia != null) {
                            Log.i(TAG, "Tipo de Transferência selecionado - Tipo D");
                            TypeD tipoD = new TypeD();
                            totalCalculado = tipoD.calculate(valorDaTransferenciaDouble, dataDaTransferencia);
                            taxaCalculada = totalCalculado - valorDaTransferenciaDouble;
                            calcularTaxa(String.valueOf(String.format("%.2f", taxaCalculada)));
                            calcularTotal(String.valueOf(totalCalculado));
                        }
                        break;
                    }
                    default: {
                        Log.i(TAG, "Tipo de Transferência selecionado - Default");
                        break;
                    }
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            monthOfYear++;
            String month = "" + monthOfYear;
            String day = "" + dayOfMonth;

            if (monthOfYear < 10) month = "0" + monthOfYear;
            if (dayOfMonth < 10) day = "0" + dayOfMonth;

            dataDaTransferenciaString = day + "/" + month + "/" + year;
            dataDaTransferenciaTextView.setText(dataDaTransferenciaString);
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dataDaTransferencia = format.parse(dataDaTransferenciaString);
            } catch (Exception e) {
                Log.i(TAG, e.getMessage());
            }

        }
    }

    private void exibirDatePicker() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    private void calcularTaxa(String taxa) {
        taxaTextView.setText(taxa);
    }

    private void calcularTotal(String total) {
        totalTextView.setText(total);
    }
}
