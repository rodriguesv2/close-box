package closebox.activity;

import closebox.model.Creditos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class CreditosActivity extends Activity{
	private TextView credito;
	private Handler handler;
	private ScrollView scroll;
	int indice = 0;
	
	
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
	
	public void mostraCredito(){
		credito.setText(Creditos.getCredito());
	}
	
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
	
	public void botaoVoltar(View view){
		Intent intent = new Intent(this, ControllerActivity.class);
		intent.putExtra("botao", "botaoVoltarCredito");
		super.finish();
		startActivity(intent);
	}
}
