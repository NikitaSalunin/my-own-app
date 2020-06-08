package com.example.myownapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {
    private TextView num1; // первое случайное число от 0 до 10
    private TextView num2; // второе случайное число от 0 до 10
    private TextView num3;
    private TextView operator; // оператор (может быть +, -, *)
    private TextView operator2;
    private EditText result;   // ответ, введенный пользователем
    private Button check;      // кнопка проверки ответа
    private TextView attempts;
    private Integer attemptsCount;
    private TextView timeout;
    private ProgressBar prbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        // подготовим данные для подсчета
        // сгенерируем случайные числа для вычислений от 0 до 10
        Random generator = new Random(new Date().getTime());
        Integer n1 = generator.nextInt(50);
        Integer n2 = generator.nextInt(50);
        Integer n3 = generator.nextInt(50);
        // если n1 < n2, меняем их местами
        if (n1 < n2) swap(n1, n2);
        // сгенерируем случайный оператор
        String[] operators = new String[] {"+", "-", "*"};
        Integer index = new Random(new Date().getTime()).nextInt(3);
        String oper = operators[index];

        // Заполняем TextView для отображения пользователю
        num1 = findViewById(R.id.num1);
        num1.setText(n1.toString());

        num2 = findViewById(R.id.num2);
        num2.setText(n2.toString());

        num3 = findViewById(R.id.num3);
        num3.setText(n3.toString());

        operator = findViewById(R.id.operator);
        operator.setText(oper);

        operator2 = findViewById(R.id.operator);
        operator2.setText(oper);

        check = findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                checkResult();
            }
        });
        timeout = findViewById(R.id.timeout);
        timeout.setText("");
        attempts = findViewById(R.id.attempts);
        if (savedInstanceState != null)
            attemptsCount =  savedInstanceState.getInt("attemptsCount", 3);
        else
            attemptsCount = 3;
        attempts.setText("Осталось попыток: " + attemptsCount.toString());
        prbar =  findViewById(R.id.progressBar2);
        new CountDownTimer(40000, 1000) {
            @Override
            public void onTick(long l) {
                timeout.setText("" + l/1000);
                prbar.setProgress((int) (l/1000));

            }
            @Override
            public void onFinish() {
                // сбрасываем счетчик
                attemptsCount = 3;
                // возвращаемся в основное меню
                Toast.makeText(getApplicationContext(), "Время закончилось", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        }.start();
    }

    // меняем числа местами
    private void swap(Integer n1, Integer n2) {
        Integer tmp = n1;

        n1 = n2;
        n2 = tmp;
    }

    // сохраняем счетчик попыток до перезагрузки
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("attemptsCount", attemptsCount);
    }

    // восстанавливаем счетчик попыток после перезагрузки
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attemptsCount = savedInstanceState.getInt("attemptsCount", 3);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void checkResult() {
        result = findViewById(R.id.result);
        Integer actual;
        try {
            actual = Integer.valueOf(result.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Введите ответ", Toast.LENGTH_LONG).show();
            return;
        }
        Integer expected;
        Integer expected2;
        // считываем случайные числа из TextView
        Integer n1 = Integer.valueOf(num1.getText().toString());
        Integer n2 = Integer.valueOf(num2.getText().toString());
        Integer n3 = Integer.valueOf(num1.getText().toString());
        // считываем случайный оператор из TextView
        String op = operator.getText().toString();
        String op2 = operator2.getText().toString();
        // вычисляем ожидаемое значение
        switch (op) {
            case "+":
                expected = n1 + n2;
                expected2 = n2 + n3;
                break;
            case "-":
                expected = n1 - n2;
                expected2 = n2 - n3;
                break;
            case "*":
                expected = n1 * n2;
                expected2 = n2 * n3;
                    break;

            default:
                expected = Integer.MAX_VALUE;
                expected2 = Integer.MAX_VALUE;
        }
        // сравниваем ожидаемое значение и введенный пользователем ответ
        if (Objects.equals(expected, actual)) {
            Toast.makeText(this, "Поздравляем! Ваш ответ правильный", Toast.LENGTH_LONG).show();
            // возвращаемся в основное меню
            //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            //startActivity(intent);
            attemptsCount = 3;
            recreate();
        } else {
            Toast.makeText(this, "Вы ввели неверный ответ", Toast.LENGTH_LONG).show();
            attemptsCount = attemptsCount - 1;
            // пересоздаем активити, чтобы обновить вопрос
            recreate();
        }
        if (attemptsCount == 0) {
            Toast.makeText(this, "Игра окончена", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }


}

