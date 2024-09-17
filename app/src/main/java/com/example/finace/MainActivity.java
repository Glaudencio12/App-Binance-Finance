package com.example.finace;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private EditText valorInicial, taxaJuros, periodoMeses, valorTotal, jurosGanhos;
    private RadioButton jurosSimples, jurosCompostos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.valorInicial = findViewById(R.id.valorinicial);
        this.taxaJuros = findViewById(R.id.taxa);
        this.periodoMeses = findViewById(R.id.tempo);
        this.valorTotal = findViewById(R.id.valortotal);
        this.jurosGanhos = findViewById(R.id.jganhos);
        this.jurosSimples = findViewById(R.id.jsimples);
        this.jurosCompostos = findViewById(R.id.jcomp);
    }

    public void calcularInvestimento(View v){
        String c = this.valorInicial.getText().toString();
        String tx = this.taxaJuros.getText().toString();
        String t = this.periodoMeses.getText().toString();

        if (c.isEmpty() || tx.isEmpty() || t.isEmpty()) {
            mensagem("INFORME OS VALORES NO CAMPO");
        }
        else {
            double capital = Double.parseDouble(c);
            double taxaJuros = Double.parseDouble(tx);
            double tempo = Double.parseDouble(t);

            if (capital == 0 || taxaJuros == 0 || tempo == 0) {
                mensagem("O VALOR INFORMADO NÃO PODE SER ZERO");
            }
            else if (capital >= 50) {
                if (jurosSimples.isChecked()) {
                    double juros = capital * (taxaJuros / 100) * tempo;
                    double montante = juros + capital;
                    DecimalFormat df1 = new DecimalFormat("#.##");
                    String montanteFormatado = df1.format(montante);
                    String jurosFormatado = df1.format(juros);
                    this.valorTotal.setText(montanteFormatado + "");
                    this.jurosGanhos.setText(jurosFormatado + "");
                }
                else if (jurosCompostos.isChecked()) {
                    double taxaPorcent = taxaJuros / 100;
                    double montante = capital * Math.pow((1 + taxaPorcent), tempo);
                    double juros = montante - capital;
                    DecimalFormat df2 = new DecimalFormat("#.##");
                    String montanteFormatado = df2.format(montante);
                    String jurosFormatado = df2.format(juros);
                    this.valorTotal.setText(montanteFormatado + "");
                    this.jurosGanhos.setText(jurosFormatado + "");
                }
                else if (!jurosSimples.isChecked() || !jurosCompostos.isChecked()) {
                    mensagem("SELECIONE O TIPO DE JUROS");
                }
            }
            else{
                mensagem("O VALOR INICIAL DE INVESTIMENTO DEVE IGUAL OU SUPERIOR A 50");
            }
        }
    }
    
    public void mensagem(String texto){
        AlertDialog.Builder mensagem = new AlertDialog.Builder(this);
        mensagem.setTitle("ATENÇÃO!");
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK",null);
        mensagem.create();
        mensagem.show();
    }

    public void resetar(View v){
        this.valorInicial.setText(null);
        this.periodoMeses.setText(null);
        this.taxaJuros.setText(null);
        this.valorTotal.setText(null);
        this.jurosGanhos.setText(null);
    }
}