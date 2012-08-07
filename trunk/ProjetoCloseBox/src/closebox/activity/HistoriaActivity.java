package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Classe que inicia a tela 'Historia' e permite ao jogador conhecer, atraves de slides ilustrativos como manipular os objetos do jogo.
 * @author Reinaldo
 *
 */
public class HistoriaActivity extends Activity{
	
	private Intent intentIn;
	private int tela = 0;
	private ImageView backgroundh;
	private ImageView bot_next;
	private ImageView bot_back;
	private ImageView bot_cancel;
	
	//Array com a lista das imagens que serao mostradas nas telas.
	private int[] listaImagem = {R.drawable.historia1,R.drawable.historia2,R.drawable.historia3};
	private ImageView imagemAtual;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historia);
		//instancia o background e os botoes de seta para a direita, esquerda e o botao cancelar.
		backgroundh = (ImageView)findViewById(R.id.bg_historia1);
		bot_next = (ImageView)findViewById(R.id.bot_next);
		bot_back = (ImageView)findViewById(R.id.bot_back);
		bot_cancel = (ImageView)findViewById(R.id.bot_cancelar);
		//inicia com a primeira imagem da sequencia e com o botao back invisivel.
		imagemAtual = (ImageView)findViewById(R.id.his1);
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
			imagemAtual = (ImageView)findViewById(R.id.his1);
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
			imagemAtual = (ImageView)findViewById(R.id.his1); 
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
		Intent intent = new Intent(this, ControllerActivity.class);
		
		super.finish();
		intent.putExtra("botao", "botaoHistoriaCancel");
		startActivity(intent);
	}
	
}
