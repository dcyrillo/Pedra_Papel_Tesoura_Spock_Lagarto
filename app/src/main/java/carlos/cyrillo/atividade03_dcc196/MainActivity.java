package carlos.cyrillo.atividade03_dcc196;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public enum Jogada{
        PEDRA(0), PAPEL(1), TESOURA(2);
        private final int valor;

        Jogada(int valor){
            this.valor=valor;
        }
    }

    public enum Resultado{
        DERROTA(-1), EMPATE(0), VITORIA(1);
        private final int valor;

        Resultado(int valor){
            this.valor=valor;
        }
    }

    public static Resultado TABELA[][]={
            {Resultado.EMPATE,Resultado.DERROTA,Resultado.VITORIA},
            {Resultado.VITORIA,Resultado.EMPATE,Resultado.DERROTA},
            {Resultado.DERROTA,Resultado.VITORIA,Resultado.EMPATE},
    };

    private Integer pontosComputador=0;
    private Integer pontosHumano=0;

    private Button buttonPedra;
    private Button buttonPapel;
    private Button buttonTesoura;

    private ProgressBar progressBarComputador;
    private ProgressBar progressBarHumano;
    private TextView textViewStatus;

    private Random dado= new Random();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPedra=findViewById(R.id.buttonPedra);
        buttonPapel=findViewById(R.id.buttonPapel);
        buttonTesoura=findViewById(R.id.buttonTesoura);

        progressBarComputador=findViewById(R.id.progressBarComputador);
        progressBarHumano=findViewById(R.id.progressBarHumano);
        textViewStatus=findViewById(R.id.textViewStatus);
    }


    public void buttonTesouraClick(View view){
        rodada(Jogada.TESOURA);
    }

    public void buttonPapelClick(View view){
        rodada(Jogada.PAPEL);
    }

    public  void buttonPedraClick(View view){
        rodada(Jogada.PEDRA);
    }

    public void rodada(Jogada jogada){
        Jogada jogadaComputador = Jogada.values()[dado.nextInt(3)];

        switch (TABELA[jogada.valor][jogadaComputador.valor]){
            case VITORIA:
                pontosHumano+=3;
                break;
            case DERROTA:
                pontosComputador+=3;
                break;
            case EMPATE:
                pontosHumano++;
                pontosComputador++;
                break;
        }
        atualizaStatus();
    }

    private void atualizaStatus(){
        progressBarComputador.setProgress(pontosComputador);
        progressBarHumano.setProgress(pontosHumano);
    }

}