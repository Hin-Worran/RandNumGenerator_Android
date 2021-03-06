package edu.hebust.zxm.randnumgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random r=new Random();
    int minNum;
    int maxNum;
    int randNum;
    TextView tvOut;
    Button btGenerate;
    EditText etMin;
    EditText etMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut=(TextView)findViewById(R.id.tv_RanNum);
        btGenerate=(Button)findViewById(R.id.btn_generate);
        etMin=(EditText)findViewById(R.id.et_min);
        etMax=(EditText)findViewById(R.id.et_max);
    }

    public void Generate(View view) {
        minNum=Integer.parseInt(etMin.getText().toString());
        maxNum=Integer.parseInt(etMax.getText().toString());
        if(maxNum>minNum) {
            randNum = minNum + r.nextInt(maxNum - minNum + 1);
            tvOut.setText("随机数是"+randNum);
        }
        else tvOut.setText("最大值必须大于最小值！");

    }
}