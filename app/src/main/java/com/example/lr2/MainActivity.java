package com.example.lr2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double amount = 0.0; // Сумма счёта
    private double percent = 0.15; // Процент чаевых по умолчанию.
    private EditText et_amount; // Поле для ввода суммы счёта
    private SeekBar sb_percent; // Ползунок для процентов
    private TextView tv_percent; // Поле для значения процента
    private TextView tv_tip; // Поле для суммы чаевых
    private TextView tv_total; // Поле для итоговой суммы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_amount = findViewById(R.id.et_amount);
        sb_percent = findViewById(R.id.sb_percent);
        tv_percent = findViewById(R.id.tv_percent);
        tv_tip = findViewById(R.id.tv_tip);
        tv_total = findViewById(R.id.tv_total);

        tv_tip.setText("0.0");
        tv_total.setText("0.0");

        et_amount.addTextChangedListener(amountTextWatcher);
        sb_percent.setOnSeekBarChangeListener(sbListener);

    }

    // Интерфейс слушателя изменений текста в EditText
    private final TextWatcher amountTextWatcher = new TextWatcher() {
        // Вызывается при изменении пользователем величины счета
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            amount = Double.parseDouble(String.valueOf(s));
            // Обновление полей с чаевыми и общей суммой
            tv_tip.setText(Double.toString(Calc.calculateTip(amount,percent)));
            tv_total.setText(Double.toString(Calc.calculateTotal(amount, percent)));
        }
        @Override
        public void afterTextChanged(Editable s) { }
        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}
