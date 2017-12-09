package com.fibonaccinumber.bldev;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TestContract.View {
    EditText et;
    TextView tv, dbResultTv;
    Button inputBtn, clearBtn, resetBtn;
    TestPresenter presenter;
    DBHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDB = new DBHelper(this);
        presenter = new TestPresenter(this, mDB);

        et = findViewById(R.id.input_number_et);
        tv = findViewById(R.id.output_number_tv);
        dbResultTv = findViewById(R.id.db_result_tv);
        inputBtn = findViewById(R.id.input_number_btn);

        inputBtn.setOnClickListener((View v) -> {
            if (!et.getText().toString().equals("")) {
                Integer etTemp = Integer.parseInt(et.getText().toString());
                Integer resultOfFib = presenter.inputTestNumberToModel(etTemp);

                tv.setText(String.valueOf(resultOfFib));

                dbResultTv.setText(presenter.insertResultToModelDB(etTemp, resultOfFib).toString());

                Context context = getApplicationContext();
                String msgTemp = presenter.sendErrorMsgToView();
                if (!msgTemp.equals("")) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, msgTemp, duration);
                    toast.show();
                }
            }
        });

        clearBtn = findViewById(R.id.clear_number_btn);
        clearBtn.setOnClickListener((View v) -> tv.setText("0"));

        resetBtn = findViewById(R.id.db_reset_btn);
        resetBtn.setOnClickListener((View v) -> {
            presenter.resetViewDB();
            dbResultTv.setText("");
        });
    }
}
