package closebox.activity;

import closebox.audio.SoundManager;
import closebox.service.MusicaPrincipalService;
import closebox.service.MusicaPrincipalService.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;

/**
 * Classe que inicia a tela 'Historia' e permite ao jogador conhecer, atraves de slides ilustrativos como manipular os objetos do jogo.
 * @author The EndGamers
 *
 */
public class HistoriaActivity extends Activity{
	
	private int tela = 0; // variavel usada como indice das telas
	private ImageView bot_next; // o botao que chama a proxima tela
	private ImageView bot_back; // o botao que chama a tela anterior
	private ImageView bot_cancel; // o botao cancelar
	//Array com a lista das imagens que serao mostradas nas telas.
	private int[] listaImagem = {R.drawable.historia1,R.drawable.historia2,R.drawable.historia3};
	private ImageView imagemAtual; // A imagem sendo exibida ao jogador
	private boolean mBound = false;
	private MusicaPrincipalService musicaPrincipalService;
	private SoundManager soundManager;
	//Atributo sobrescrito para conex�o com o servi�o de musica.
	private ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			mBound = false;
			
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder localBinder = (LocalBinder)service;
			musicaPrincipalService = localBinder.getService();
			musicaPrincipalService.playMusic();
			mBound = true;
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historia);
		//instancia os botoes de seta para a direita, esquerda e o botao cancelar.		
		bot_next = (ImageView)findViewById(R.id.bot_next);
		bot_back = (ImageView)findViewById(R.id.bot_back);
		bot_cancel = (ImageView)findViewById(R.id.bot_cancelar);
		//inicia com a primeira imagem da sequencia e com o botao back invisivel.
		imagemAtual = (ImageView)findViewById(R.id.his1);
		bot_back.setVisibility(View.INVISIBLE);
		soundManager = SoundManager.getInstance(this);
		
		bindService(new Intent(this, MusicaPrincipalService.class), serviceConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public void onResume(){
		if(mBound)
			musicaPrincipalService.playMusic();
		super.onResume();
	}
	
	@Override
	public void onPause(){
		if(mBound)
			musicaPrincipalService.pauseMusic();
		super.onPause();
	}
	
	@Override
	public void onStart(){
		if(mBound)
			musicaPrincipalService.playMusic();
		super.onStart();
	}
	
	@Override
	public void onDestroy(){
		if(mBound)
			unbindService(serviceConnection);
		super.onDestroy();
	}
	
	@Override
	public void finish(){
		//soundManager.cleanup();
		super.finish();
	}
	
	/**
	 * Metodo que verifica o numero da tela atual (imagem) sendo mostrada na historia,
	 * de acordo com a tela, mostra ou esconde os botoes 'next' e 'back'.
	 */
	private void mostraBotoes(){
		if(tela == 0){ // na tela inicial (primeira tela) o botao "anterior" fica invisivel
			bot_back.setVisibility(View.INVISIBLE);
		}else{
			bot_back.setVisibility(View.VISIBLE);
		}
		
		if(tela == listaImagem.length-1){//na ultima tela, o botao "proximo" fica invisivel
			bot_next.setVisibility(View.INVISIBLE);
		}else{
			bot_next.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * Metodo que torna o botao 'proximo' (navegacao � direita) sensivel ao toque.
	 * De acordo com a tela atual de navegacao, mostra a proxima imagem da sequencia.
	 * @param view o proprio botao (ImageView).
	 */
	public void botaoNext(View view){
		soundManager.playSound(SoundManager.BOTAO_NAVEGACAO);
		tela++;
		if(tela<listaImagem.length){
			imagemAtual.setImageResource(listaImagem[tela]); 
		}else{
			imagemAtual = (ImageView)findViewById(R.id.his1);// caso o indice seja maior que a lista mostra a primeira imagem da lista
		}
		mostraBotoes();
	}
	
	/**
	 * Metodo que torna o botao 'anterior' (navegacao � esquerda) sensivel ao toque.
	 * De acordo com a tela atual de navegacao, mostra a proxima imagem da sequencia.
	 * @param view o propio botao (ImageView).
	 */
	public void botaoBack(View view){
		soundManager.playSound(SoundManager.BOTAO_NAVEGACAO);
		tela--;
		if(tela>=0){
			imagemAtual.setImageResource(listaImagem[tela]); 
		}else{
			imagemAtual = (ImageView)findViewById(R.id.his1); // caso o indice seja maior que a lista mostra a primeira imagem da lista
		}
		mostraBotoes();
	}
	
	/**
	 * Metodo que torna o botao 'cancelar' sensivel ao toque.
	 *@param view o proprio botao
	 */
	public void botaoCancel(View view){
		soundManager.playSound(SoundManager.BOTAO_NAVEGACAO);
		onBackPressed();
	}
	
}
