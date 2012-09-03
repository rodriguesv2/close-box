package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Classe que inicia a tela 'Como Jogar' e permite ao jogador aprender, atraves de slides ilustrativos como manipular os objetos do jogo.
 * @author The EndGamers
 *
 */
public class ComoJogarActivity extends Activity{
	
	private int tela = 0; // variavel usada como indice das telas
	private ImageView bot_next; // o botao que chama a proxima tela
	private ImageView bot_back; // o botao que chama a tela anterior
	private ImageView bot_cancel; // o botao cancelar
	private int[] listaImagem = {R.drawable.comojogar1,R.drawable.comojogar2,R.drawable.comojogar3,R.drawable.comojogar4}; // Lista de imagens utilizadas
	private ImageView imagemAtual; // A imagem sendo exibida ao jogador

	/**
	 * Inicializa a Activity e chama o layout apropriado, associado a essa Activity.
	 */
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
	 * Verifica o numero da tela atual (imagem) sendo mostrada na historia,
	 * de acordo com a tela, mostra ou esconde os botoes 'next' e 'back'.
	 */
	private void mostraBotoes(){
		if(tela == 0){ // na tela inicial (primeira tela) o botao "anterior" fica invisivel
			bot_back.setVisibility(View.INVISIBLE);
		}else{
			bot_back.setVisibility(View.VISIBLE);
		}
		
		if(tela == listaImagem.length-1){ //na ultima tela, o botao "proximo" fica invisivel
			bot_next.setVisibility(View.INVISIBLE);
		}else{
			bot_next.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * Torna o botao 'proximo' (navegacao à direita) sensivel ao toque.
	 * De acordo com a tela atual de navegacao, mostra a proxima imagem da sequencia.
	 * @param view o proprio botao (ImageView).
	 */
	public void botaoNext(View view){
		tela++;
		if(tela<listaImagem.length){
			imagemAtual.setImageResource(listaImagem[tela]); 
		}else{
			imagemAtual = (ImageView)findViewById(R.id.cj1); // caso o indice seja maior que a lista mostra a primeira imagem da lista
		}
		mostraBotoes();
	}
	
	/**
	 * Torna o botao 'anterior' (navegacao à esquerda) sensivel ao toque.
	 * De acordo com a tela atual de navegacao, mostra a proxima imagem da sequencia.
	 * @param view o propio botao (ImageView).
	 */
	public void botaoBack(View view){
		tela--;
		if(tela>=0){
			imagemAtual.setImageResource(listaImagem[tela]); 
		}else{
			imagemAtual = (ImageView)findViewById(R.id.cj1); // caso o indice seja maior que a lista mostra a primeira imagem da lista
		}
		mostraBotoes();
	}
	
	/**
	 * Torna o botao 'cancelar' sensivel ao toque.
	 *@param view o proprio botao
	 */
	public void botaoCancel(View view){
		onBackPressed();
	}
	
}
