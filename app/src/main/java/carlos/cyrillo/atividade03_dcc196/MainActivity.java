package carlos.cyrillo.atividade03_dcc196;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public enum Jogada{
        PEDRA(0), PAPEL(1), TESOURA(2),SPOCK(3),LAGARTO(4);
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
    private Button buttonLagarto;
    private Button buttonSpock;

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
        buttonSpock=findViewById(R.id.Lagarto);
        buttonLagarto=findViewById(R.id.Spock);



        progressBarComputador=findViewById(R.id.progressBarComputador);
        progressBarHumano=findViewById(R.id.progressBarHumano);
        textViewStatus=findViewById(R.id.textViewStatus);
    }


    public void buttonSpockClick(View view){
        rodada(Jogada.SPOCK);
    }

    public void buttonLagartoClick(View view){
        rodada(Jogada.LAGARTO);
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
                Toast.makeText(this,"O humano venceu ",Toast.LENGTH_SHORT).show();
                break;
            case DERROTA:
                pontosComputador+=3;
                Toast.makeText(this,"O computador venceu ",Toast.LENGTH_SHORT).show();

                break;
            case EMPATE:
                pontosHumano++;
                pontosComputador++;
                Toast.makeText(this,"Empate ",Toast.LENGTH_SHORT).show();

                break;
        }
        atualizaStatus();
    }

    private void atualizaStatus(){
        progressBarComputador.setProgress(pontosComputador);
        progressBarHumano.setProgress(pontosHumano);

        if(pontosHumano<15 && pontosComputador<15){
            textViewStatus.setText("Escolha uma opção...");
        }
        else{
            if(pontosHumano>=15&& pontosComputador<15){
                textViewStatus.setText("O humano venceu o torneio");
                iniciaTorneio();
            }
            else if(pontosHumano<15&& pontosComputador>=15){
                textViewStatus.setText("O computador venceu o torneio");
                iniciaTorneio();
            }
            else{
                textViewStatus.setText("O torneio empatou");
                iniciaTorneio();
            }
        }
    }

    private  void iniciaTorneio(){
        pontosHumano=0;
        pontosComputador=0;
    }

    public void textViewStatusClick(View view){
        AlertDialog.Builder dialogBuilder =new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Reinicar torneio? ");
        dialogBuilder.setMessage("Deseja reiniciar o torneio zerando o placar? ");
       dialogBuilder.setPositiveButton("Reiniciar ", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               iniciaTorneio();
               atualizaStatus();
           }
       });
        dialogBuilder.create();
        dialogBuilder.show();


    }
}