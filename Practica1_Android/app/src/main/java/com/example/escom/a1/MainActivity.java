package com.example.escom.a1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bmult,bres,bmas,bdiv,bigual,bp,bc;
    ToggleButton bd;
    TextView salida;
    String display = "";
    String operator = "";
    String result = "";
    DecimalFormat df = new DecimalFormat("#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.salida = (TextView)findViewById(R.id.salida);
        this.b0 = (Button)findViewById(R.id.btn0);
        this.b1 = (Button)findViewById(R.id.btn1);
        this.b2 = (Button)findViewById(R.id.btn2);
        this.b3 = (Button)findViewById(R.id.btn3);
        this.b4 = (Button)findViewById(R.id.btn4);
        this.b5 = (Button)findViewById(R.id.btn5);
        this.b6 = (Button)findViewById(R.id.btn6);
        this.b7 = (Button)findViewById(R.id.btn7);
        this.b8 = (Button)findViewById(R.id.btn8);
        this.b9 = (Button)findViewById(R.id.btn9);
        this.bmult = (Button)findViewById(R.id.btnMult);
        this.bres = (Button)findViewById(R.id.btnRest);
        this.bmas = (Button)findViewById(R.id.btnSum);
        this.bdiv = (Button)findViewById(R.id.btnDiv);
        this.bigual = (Button)findViewById(R.id.btnEqual);
        this.bp = (Button)findViewById(R.id.btnP);
        this.bd = (ToggleButton)findViewById(R.id.btnBD);
        this.bc = (Button)findViewById(R.id.btnC);

        this.salida.setText(this.display);

        View.OnClickListener DibujaLetra = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(result != ""){
                    clear();
                    updateDisplay();
                }
                int i = view.getId();
                Button aux = (Button)findViewById(i);
                String x = aux.getText().toString();
                display += x;
                updateDisplay();
            }
        };

        View.OnClickListener DibujarOperador = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(display == "") return;

                Button b = (Button)view;

                if(result != ""){
                    String _display = result;
                    clear();
                    if(bd.isChecked()){
                        display = getBin(_display);
                    }
                    else{
                        display = _display;
                    }

                }

                if(operator != ""){
                    Log.d("CalcX", ""+display.charAt(display.length()-1));
                    if(isOperator(display.charAt(display.length()-1))){
                        display = display.replace(display.charAt(display.length()-1), b.getText().charAt(0));
                        updateDisplay();
                        return;
                    }else{
                        getResult();
                        display = result;
                        result = "";
                    }
                    operator = b.getText().toString();
                }
                display += b.getText();
                operator = b.getText().toString();
                updateDisplay();
            }
        };

        this.bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                updateDisplay();
            }
        });

        this.bigual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(display == "") return;
                if(!getResult()) return;
                if(bd.isChecked()){
                    String r = getBin(result);
                    salida.setText(display + "\n" + r);
                }
                else {
                    salida.setText(display + "\n" + String.valueOf(result));
                }
            }
        });

        this.bd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    b2.setEnabled(false);
                    b3.setEnabled(false);
                    b4.setEnabled(false);
                    b5.setEnabled(false);
                    b6.setEnabled(false);
                    b7.setEnabled(false);
                    b8.setEnabled(false);
                    b9.setEnabled(false);
                    bp.setEnabled(false);
                    bres.setEnabled(false);
                    bdiv.setEnabled(false);
                    clear();
                    updateDisplay();
                }
                else{
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    b5.setEnabled(true);
                    b6.setEnabled(true);
                    b7.setEnabled(true);
                    b8.setEnabled(true);
                    b9.setEnabled(true);
                    bp.setEnabled(true);
                    bres.setEnabled(true);
                    bdiv.setEnabled(true);
                    clear();
                    updateDisplay();
                }
            }
        });

        this.b0.setOnClickListener(DibujaLetra);
        this.b1.setOnClickListener(DibujaLetra);
        this.b2.setOnClickListener(DibujaLetra);
        this.b3.setOnClickListener(DibujaLetra);
        this.b4.setOnClickListener(DibujaLetra);
        this.b5.setOnClickListener(DibujaLetra);
        this.b6.setOnClickListener(DibujaLetra);
        this.b7.setOnClickListener(DibujaLetra);
        this.b8.setOnClickListener(DibujaLetra);
        this.b9.setOnClickListener(DibujaLetra);
        this.bp.setOnClickListener(DibujaLetra);

        this.bmas.setOnClickListener(DibujarOperador);
        this.bres.setOnClickListener(DibujarOperador);
        this.bdiv.setOnClickListener(DibujarOperador);
        this.bmult.setOnClickListener(DibujarOperador);




    }

    public String getBin(String i){
        String v = String.valueOf(i);
        if(v.contains(".")){
            v = v.substring(0, v.length() - 2);
        }
        return v;
    }

    public int BtoD(String n){
        int l = n.length();
        int res = 0;
        int p = l - 1;
        for(int i = 0;i < l;i++){
            if(n.charAt(i) == '1'){
                res += Math.pow(2,p);
            }
            p --;
        }
        return res;
    }

    public int DtoB(int n){
        int temp = n;
        String res = "";
        while (temp != 0){
            if(temp % 2 == 0){
                res = "0" + res;
            }else{
                res = "1" + res;
            }
            temp = temp / 2;
        }
        return parseInt(res);
    }

    public void updateDisplay(){
        this.salida.setText(this.display);
    }

    public void clear(){
        display = "";
        operator = "";
        result = "";
    }

    public boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '/':return true;
            default: return false;
        }
    }

    public boolean getResult(){
        if(operator == "") return false;
        String[] operation = display.split(Pattern.quote(operator));
        if(operation.length < 2) return false;
        result = String.valueOf(operate(operation[0], operation[1], operator));
        return true;
    }

    public double operate(String a, String b, String op){
        if(!this.bd.isChecked()){
            switch (op){
                case "+": return Double.valueOf(a) + Double.valueOf(b);
                case "-": return Double.valueOf(a) - Double.valueOf(b);
                case "x": return Double.valueOf(a) * Double.valueOf(b);
                case "/": try{
                    return Double.valueOf(a) / Double.valueOf(b);
                }catch (Exception e){
                    Log.d("Calc", e.getMessage());
                }
                default: return -1;
            }
        }
        else{
            switch (op){
                case "+": return this.sumaB(a,b);
                case "x": return this.multB(a,b);
                default: return -1;
            }
        }

    }

    public double sumaB(String a, String b){
        String aux = a;
        if(a.contains(".")){
            aux = aux.substring(0,aux.length()-2);
        }
        int x = this.BtoD(aux);
        int y = this.BtoD(b);
        int z = x+y;
        double d = this.DtoB(z);
        return d;
    }

    public double multB(String a, String b){
        String aux = a;
        if(a.contains(".")){
            aux = aux.substring(0,aux.length()-2);
        }
        int x = this.BtoD(aux);
        int y = this.BtoD(b);
        int z = x*y;
        double d = this.DtoB(z);
        return d;
    }

    public boolean isBinary(String r){
        if(r.contains("2")||r.contains("3")||r.contains("4")||r.contains("5")||r.contains("6")||r.contains("7")||r.contains("8")||r.contains("9")){
            return true;
        }
        else{
            return false;
        }
    }
}
