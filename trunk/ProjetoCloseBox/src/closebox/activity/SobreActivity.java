package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Classe que inicializa as classes especificas: HistoriaActivity, CreditosActivity e ComoJogarActivity 
 * de acordo com o botao tocado pelo jogador
 * @author THE ENDGAMERS
 *
 */
public class SobreActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState){ // metodo CONSTRUTOR
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sobre_novo);
	}
	
	public void botaoCredito(View view){
		Intent intent = new Intent(this, ControllerActivity.class); //determina a nova Activity
		intent.putExtra("botao", "botaoCredito"); // o nome do botao, na verdade uma referencia a ser tratada no controller
		startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
	}
	
	public void botaoHistoria(View view){
		Intent intent = new Intent(this, ControllerActivity.class); //determina a nova Activity
		intent.putExtra("botao", "botaoHistoria"); // o nome do botao, na verdade uma referencia a ser tratada no controller
		startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
	}
	
	public void botaoComoJogar(View view){
		Intent intent = new Intent(this, ControllerActivity.class); //determina a nova Activity
		intent.putExtra("botao", "botaoComoJogar"); // o nome do botao, na verdade uma referencia a ser tratada no controller
		startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
	}
}

