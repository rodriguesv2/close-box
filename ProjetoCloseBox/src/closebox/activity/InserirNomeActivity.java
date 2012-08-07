package closebox.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;

import closebox.model.Jogador;

public class InserirNomeActivity extends Activity {
	private Intent intentDados;
	private Intent intentOut;
	private EditText editJogador1;
	private EditText editJogador2;
	private EditText editJogador3;
	private TextView textJogador1;
	private TextView textJogador2;
	private TextView textJogador3;
	private ArrayList<String> arrayJogadores; 
	private ArrayList<Integer> pontosJogador;
	private int indice;
	
	
	//Metodo para ação do botão "OK"
	public void botaoOk(View view){
		//Instancia a intent com a activity destino"
		intentOut = new Intent(this, ControllerActivity.class);
		
				if(confirmaPreenchimento(indice)){
					addJogadores();
					populaPontos(indice);
			        
					//Além de chamar a nova activity, também manda dados
					intentOut.putExtra("numeroDeJogadores", indice);
					intentOut.putExtra("botao", "botaoOkInserirNome");
					
					intentOut.putStringArrayListExtra("arrayJogadores", arrayJogadores);
					intentOut.putExtra("jogadorAtual", 0);
					intentOut.putIntegerArrayListExtra("pontuacaoJogadores", pontosJogador);
					
					super.finish();
					//Vai para nova activity
					startActivity(intentOut);
				}
	}
	
	public void botaoCancelar(View view){	
				Intent intent = new Intent(this, ControllerActivity.class);
				intent.putExtra("botao", "botaoCancelarInserirNome");
				
				super.finish();
				startActivity(intent);
	}
	
	//Apenas verifica se os EditTexts foram preenchidos, caso contrario retorna falso.
	private boolean confirmaPreenchimento(int indice){
		String nome1 = editJogador1.getText().toString();
		String nome2 = editJogador2.getText().toString();
		String nome3 = editJogador3.getText().toString();
		
		this.indice = indice;
		
		if(indice == 1){
			if(nome1.length() == 0){
				return false;
			}else{
				return true;
				
			}
		}else if(indice == 2){
			if(nome1.length() == 0 || nome2.length() == 0){
				return false;
			}else{
				return true;
			}
		}else{
			if(nome1.length() == 0 || nome2.length() == 0 || nome3.length() == 0){
				return false;
			}else{
				return true;
			}
		}
		
	}
	
	private void populaPontos(int index){
		pontosJogador = new ArrayList<Integer>();
		for(int i = 0; i<index; i++){
			pontosJogador.add(i, 45);
		}
	}
	 
	//Cria a lista de Jogadores para a proxima activity montar a lista na view.
	private void addJogadores(){
		String string1 = editJogador1.getText().toString();
		String string2 = editJogador2.getText().toString();
		String string3 = editJogador3.getText().toString();
		
		arrayJogadores = new ArrayList<String>();
		arrayJogadores.add(string1);
		arrayJogadores.add(string2);
		arrayJogadores.add(string3);
		
		intentOut.putStringArrayListExtra("arrayJogadores", arrayJogadores);
		intentOut.putExtra("jogadorAtual", 0);
		intentOut.putExtra("pontuacaoJogadores", pontosJogador);
	}
	
	private void instanciarObjetos(){
		editJogador1 = (EditText)findViewById(R.id.editText1);
		editJogador2 = (EditText)findViewById(R.id.editText2);
		editJogador3 = (EditText)findViewById(R.id.editText3);
		textJogador1 = (TextView)findViewById(R.id.textView1);
		textJogador2 = (TextView)findViewById(R.id.textView2);
		textJogador3 = (TextView)findViewById(R.id.textView3);
		
	}
	
	private void setNovaIntent(){
		intentDados = getIntent();
	}
	
	//Dependendo a quantidade de jogadores escolhidos, alguns campos ficam invisiveis.
	private void setQuantidadeDeCamposDeTextos(int indice){
		if(indice == 1){
			editJogador1.setVisibility(View.VISIBLE);
			textJogador1.setVisibility(View.VISIBLE);
		
		}else if(indice == 2){
			editJogador1.setVisibility(View.VISIBLE);
			textJogador1.setVisibility(View.VISIBLE);
			editJogador2.setVisibility(View.VISIBLE);
			textJogador2.setVisibility(View.VISIBLE);
			
		}else{
			editJogador1.setVisibility(View.VISIBLE);
			textJogador1.setVisibility(View.VISIBLE);
			editJogador2.setVisibility(View.VISIBLE);
			textJogador2.setVisibility(View.VISIBLE);
			editJogador3.setVisibility(View.VISIBLE);
			textJogador3.setVisibility(View.VISIBLE);
		}
	}
	
	private void dialogo(){
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Inserir numero de jogadores");
		dialogo.setMessage("Inserir de 1 à 3");
		
		final EditText inserirNumero = new EditText(this);
    	dialogo.setView(inserirNumero);
    	
    	dialogo.setPositiveButton("OK", new OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialogo, int qualBotao) {
				try{
					indice = Integer.parseInt(inserirNumero.getText().toString());
					if(indice == 1 || indice == 2 || indice ==3){
						setQuantidadeDeCamposDeTextos(indice);
					}
					else
						dialogo();
				}catch (NumberFormatException e) {
						dialogo();
				}
			}
		});
    	
    	//Cria um botão negativo. Por padrão, apenas facha o dialogo.
    	/*dialogo.setNegativeButton("Cancel", new OnClickListener() {		
			@Override
			public void onClick(DialogInterface dialogo, int qualBotao) {	
				intentNovaPagina = new Intent(InserirNomeActivity.this, ControllerActivity.class);
				intentNovaPagina.putExtra("botao", "botaoCancelarDialogo");
				
				startActivity(intentNovaPagina);
			}
		});*/
    	
    	dialogo.show();
	}
	
	//Para o Android, essa eh a classe main.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Faz uma nova tela entrar na frente.
        setContentView(R.layout.inserir_nomes);
        instanciarObjetos();
        setNovaIntent();
        dialogo();
    }
}

