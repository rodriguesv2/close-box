package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NumeroDeJogadoresActivity extends Activity{

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.numero_de_jogadores);	
	}
		
	public void escolherNumero(View view){
		int numeroDeJogadores = 0;
		
		if(view.getId() == R.id.imageView2)		numeroDeJogadores = 1;
		else if(view.getId() == R.id.imageView3)numeroDeJogadores = 2;
		else 									numeroDeJogadores = 3;
			
		Intent intent = new Intent(this, ControllerActivity.class);
		intent.putExtra("numeroDeJogadores", numeroDeJogadores);
		intent.putExtra("botao", "botaoNumeroDeJogadores");
		startActivity(intent);
		finish();
	}
	
	public void voltar(View view){
		onBackPressed();
	}
}
