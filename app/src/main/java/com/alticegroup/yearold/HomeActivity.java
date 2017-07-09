package com.alticegroup.yearold;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etxtBirthday;
    private TextView txtResult;
    private DatePickerDialog dpdBirthday;
    private SimpleDateFormat sdfDateformatter;
    private Button btnCalculate;
    private Button btnClear;
    private Calculation calculation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sdfDateformatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViews();
        setDateTimeField();

        calculation = new Calculation();
    }

    private void findViews() {
        etxtBirthday = (EditText) findViewById(R.id.etxtBirthday);
        etxtBirthday.setInputType(InputType.TYPE_NULL);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnClear = (Button) findViewById(R.id.btnClear);

        txtResult = (TextView) findViewById(R.id.txtResult);

        etxtBirthday.requestFocus();
    }

    private void setDateTimeField() {
        etxtBirthday.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        dpdBirthday = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                calculation.setDayOfBirth(year, month, dayOfMonth);
                etxtBirthday.setText(sdfDateformatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void CalculateAge()
    {
        calculation.getCurrentDate();
        calculation.calculateYear();
        calculation.calculateMonth();
        calculation.calculateDay();
        txtResult.setText(calculation.getResult());
    }

    private void Clear() {
        etxtBirthday.setText("");
        txtResult.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.etxtBirthday:
                dpdBirthday.show();
                break;
            case R.id.btnCalculate:
                CalculateAge();
                break;
            case R.id.btnClear:
                Clear();
                break;
            default:
                break;
        }

    }
}
