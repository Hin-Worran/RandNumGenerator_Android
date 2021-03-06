package edu.hebust.zxm.randnumgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random r=new Random();
    int minNum;
    int maxNum;
    int amount;
    int[] randNum;
    TextView tvOut;
    Button btGenerate;
    EditText etMin;
    EditText etMax;
    EditText etNum;
    RadioButton reY;
    RadioButton reN;
    int[] Get_RandNum(int n,int min,int max){
        int[] output=new int[n];
        for(int i=0;i<n;i++){
            output[i]=min + r.nextInt(max - min + 1);
        }
        return output;
    }

    int[] Get_NoRepeat(int n,int min,int max){
        int[] output=new int[n];
        for (int i=0;i<n;){
            int rand=min + r.nextInt(max - min + 1);
            boolean rep=false;
            for (int j=0;j<i;j++){
                if(rand==output[j]){
                    rep=true;
                    break;
                }
            }
            if (rep==false){
                output[i]=rand;
                i++;
            }
        }
        return output;
    }

    void Out_RandNum(TextView tv,int[] rand,int n){
        for (int i=0;i<n;i++){
            tv.setText(tv.getText().toString()+rand[i]+"\t;");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut=(TextView)findViewById(R.id.tv_RanNum);
        btGenerate=(Button)findViewById(R.id.btn_generate);
        etMin=(EditText)findViewById(R.id.et_min);
        etMax=(EditText)findViewById(R.id.et_max);
        etNum=(EditText)findViewById(R.id.et_num);
        reY=(RadioButton)findViewById(R.id.rbt_repeatY);
        reN=(RadioButton)findViewById(R.id.rbt_repeatN);
    }

    public void Generate(View view) {
        tvOut.setText("生成的随机数是：\n");
        minNum=Integer.parseInt(etMin.getText().toString());    //获取输入框的值
        maxNum=Integer.parseInt(etMax.getText().toString());
        amount=Integer.parseInt(etNum.getText().toString());
        if(amount<=0){
            tvOut.setText("随机数的数量不能小于零");
            return;
        }
        if(maxNum<=minNum) {     //最大值应大于最小值
            tvOut.setText("最大值必须大于最小值！");
            return;
            }
        if(reY.isChecked())         //生成重复的随机数
            randNum = Get_RandNum(amount,minNum,maxNum);
        else if (reN.isChecked()){      //生成不重复的随机数
            if (amount>maxNum-minNum+1) {  //随机数的数目应小于总数
                tvOut.setText("随机数的数目应小于总数");
                return;
            }
            else
                randNum = Get_NoRepeat(amount, minNum, maxNum);

        }
        Out_RandNum(tvOut,randNum,amount);  //输出随机数
    }
}