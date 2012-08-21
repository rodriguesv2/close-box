package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Classe que inicia a tela 'Como Jogar' e permite ao jogador aprender, atraves de slides ilustrativos como manipular os objetos do jogo.
 * @author Reinaldo
 *
 */
public class ComoJogarActivity extends Activity{
	
	private Intent intentIn;
	private int tela = 0;
	private ImageView bot_next;
	private ImageView bot_back;
	private ImageView bot_cancel;
	private int[] listaImagem = {R.drawable.comojogar1,R.drawable.comojogar2,R.drawable.comojogar3,R.drawable.comojogar4};
	private ImageView imagemAtual;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.como_jogar);
		//instancia os botoes de seta para a direita, esquerda e o botao cancelar.
		bot_next = (ImageView)findViewById(R.id.bt_next);
		bot_back = (ImageView)findViewById(R.id.bt_back);
		bot_cancel = (ImageView)findViewById(R.id.bt_cancelar);
		//inicia com a primeira imagem da sequencia e com o botao back invisivel.
		imagemAtual = (ImageView)findViewById(R.id.cj1);
		bot_back.setVisibility(View.INVISIBLE);
	}
	
	/**
	 * Metodo que verifica o numero da tela atual (imagem) sendo mostrada na historia,
	 * de acordo com a tela, mostra ou esconde os botoes 'next' e 'back'.
	 */
	private void mostraBotoes(){
		if(tela == 0){
			bot_back.setVisibility(View.INVISIBLE);
		}else{
			bot_back.setVisibility(View.VISIBLE);
		}
		
		if(tela == listaImagem.length-1){
			bot_next.setVisibility(View.INVISIBLE);
		}else{
			bot_next.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * Metodo que torna o botao 'proximo' (navegacao à direita) sensivel ao toque.
	 * De acordo com a tela atual de navegacao, mostra a proxima imagem da sequencia.
	 * @param view o proprio botao (ImageView).
	 */
	public void botaoNext(View view){
		tela++;
		if(tela<listaImagem.length){
			imagemAtual.setImageResource(listaImagem[tela]); 
		}else{
			imagemAtual = (ImageView)findViewById(R.id.cj1);
		}
		mostraBotoes();
	}
	
	/**
	 * Metodo que torna o botao 'anterior' (navegacao à esquerda) sensivel ao toque.
	 * De acordo com a tela atual de navegacao, mostra a proxima imagem da sequencia.
	 * @param view o propio botao (ImageView).
	 */
	public void botaoBack(View view){
		tela--;
		if(tela>=0){
			imagemAtual.setImageResource(listaImagem[tela]); 
		}else{
			imagemAtual = (ImageView)findViewById(R.id.cj1);
		}
		mostraBotoes();
	}
	
	/**
	 * Metodo que torna o botao 'cancelar' sensivel ao toque.
	 * Envia uma String que servira de referencia para o Controller
	 * poder gerenciar a navegacao.
	 * @param view o proprio botao
	 */
	public void botaoCancel(View view){
		onBackPressed();
	}
	
}
