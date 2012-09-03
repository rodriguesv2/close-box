package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Classe responsavel por determinar a quantidade de jogadores que participarao do jogo
 * @author The EndGamers
 *
 */
public class NumeroDeJogadoresActivity extends Activity{

	
	@Override
	public void onCreate(Bundle savedInstanceState){ // CONSTRUTOR
		super.onCreate(savedInstanceState);
		setContentView(R.layout.numero_de_jogadores);	
	}
	
	/**
	 * Determina a quantidade de jogadores
	 * @param view o proprio botao selecionado pelo Jogador, que apresenta as imagens '1', '2' ou '3'.
	 */
	public void escolherNumero(View view){
		int numeroDeJogadores = 0;
		
		if(view.getId() == R.id.imageView2)		numeroDeJogadores = 1; // caso seja selecionada a imagem 1
		else if(view.getId() == R.id.imageView3)numeroDeJogadores = 2; // caso seja selecionada a imagem 2
		else 									numeroDeJogadores = 3; // caso seja selecionada a imagem 3
			
		Intent intent = new Intent(this, ControllerActivity.class); // faz a chamada da Activity ControllerActivity.
		intent.putExtra("numeroDeJogadores", numeroDeJogadores); // envia a quantidade selecionada pelo Jogador
		intent.putExtra("botao", "botaoNumeroDeJogadores"); // envia uma String "botaoNumeroDeJogadores", com chave "botao"
		startActivity(intent);
		finish();
	}
	
	/**
	 * Ao clicar no botao "Voltar" ou pressionar o botao "voltar" nativo do aparelho
	 * @param view o proprio botao voltar
	 */
	public void voltar(View view){
		onBackPressed();
	}
}
