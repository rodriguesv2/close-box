package closebox.activity;

import closebox.model.Creditos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
/**
 * Classe responsavel por mostrar um texto na tela, com as informações dos desenvolvedores e todo os artefatos utilizados no projeto.
 * @author Reinaldo
 *
 */
public class CreditosActivity extends Activity{
	private TextView credito; // TextView responsavel por mostrar o texto "creditos"
	private Handler handler;
	private ScrollView scroll;
	int indice = 0; // usado como indice na rolagem da tela
	
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credito);
		
		credito = (TextView)findViewById(R.id.textView1);
		scroll = (ScrollView)findViewById(R.id.scrollView1);
		handler = new Handler();
		mostraCredito();
		passarCreditos();
	}
	
	/**
	 * Insere um texto na TextView da tela, proveniente do método getCredito() da classe Creditos do pacote Model.
	 */
	public void mostraCredito(){
		credito.setText(Creditos.getCredito());
	}
	
	/**
	 * Thread que faz o scroll do texto,  quando o texto chega ao fim, volta ao começo sucessivamente.
	 */
	public void passarCreditos(){
		Runnable runnable = new Runnable() {
			 	
			@Override
			public void run() {
				
				while(true){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							int rolar = indice;
							scroll.scrollTo(0, rolar);
							if(indice < 900){
								indice += 3;
							}else{
								indice = 0;
							}
						}
					});
				}
			}
		};
		new Thread(runnable).start();
	}
	
	/**
	 * Implementa o botao voltar do Android
	 * @param view o proprio botao voltar
	 */
	public void botaoVoltar(View view){
		onBackPressed();
	}
}
