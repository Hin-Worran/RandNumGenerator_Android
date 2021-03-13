package edu.hebust.zxm.randnumgenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //定义变量
    Random r=new Random();      //定义生成随机数的方法
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
    RadioButton sortUp;
    RadioButton sortDown;
    RadioButton sortNo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find Views
        tvOut=findViewById(R.id.tv_RanNum);
        btGenerate=findViewById(R.id.btn_generate);
        etMin=findViewById(R.id.et_min);
        etMax=findViewById(R.id.et_max);
        etNum=findViewById(R.id.et_num);
        reY=findViewById(R.id.rbt_repeatY);
        reN=findViewById(R.id.rbt_repeatN);
        sortUp=findViewById(R.id.rbt_sortUp);
        sortDown=findViewById(R.id.rbt_sortDown);
        sortNo=findViewById(R.id.rbt_sortNo);

        //创建开始弹出对话框
        AlertDialog.Builder myDialog=new AlertDialog.Builder(MainActivity.this);
        //创建AlertDialog.Builder对象
        myDialog.setTitle("Hello! My friend!");
        //说明文本
        myDialog.setMessage(R.string.说明);
        myDialog.setPositiveButton("知道了！",null);
        AlertDialog alertDialog=myDialog.create();
        alertDialog.show();
    }

    //生成随机数的主程序
    public void Generate(View view) {
        tvOut.setText("生成的随机数是：\n");
        minNum=Integer.parseInt(etMin.getText().toString());    //获取输入框的值
        maxNum=Integer.parseInt(etMax.getText().toString());
        amount=Integer.parseInt(etNum.getText().toString());
        //判断逻辑
        if(amount<=0){
            tvOut.setText("随机数的数量不能小于零");
            return;
        }
        if(maxNum<=minNum) {                                    //最大值应大于最小值
            tvOut.setText("最大值必须大于最小值！");
            return;
            }
        //生成随机数
        if(reY.isChecked())                                      //生成重复的随机数
            randNum = Get_RandNum(amount,minNum,maxNum);
        else if (reN.isChecked()){                                 //生成不重复的随机数
            if (amount>maxNum-minNum+1) {                          //随机数的数目应小于总数
                tvOut.setText("随机数的数目应小于总数");
                return;
            }
            else
                randNum = Get_NoRepeat(amount, minNum, maxNum);
        }
        //给随机数排序
        if(sortUp.isChecked())          //升序
            Arrays.sort(randNum);
        else if (sortDown.isChecked()){         //降序
            Arrays.sort(randNum);
            for (int i=0;i<(int)(amount/2);i++){
                int x=randNum[i];
                randNum[i]=randNum[amount-i-1];
                randNum[amount-i-1]=x;
            }
        }
        //输出随机数
        Out_RandNum(tvOut,randNum,amount);
    }

    //获取随机数（重复的）
    int[] Get_RandNum(int n,int min,int max){
        int[] output=new int[n];
        for(int i=0;i<n;i++){
            output[i]=min + r.nextInt(max - min + 1);
        }
        return output;
    }

    //获取随机数（不重复的）
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
            if (!rep){
                output[i]=rand;
                i++;
            }
        }
        return output;
    }

    //通过TextView输出随机数
    void Out_RandNum(TextView tv,int[] rand,int n){
        for (int i=0;i<n;i++){
            tv.setText(tv.getText().toString()+rand[i]+"\t;");
        }
    }



}