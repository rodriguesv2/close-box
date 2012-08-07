package closebox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SobreActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sobre_novo);
	}
	
	public void botaoCredito(View view){
		Intent intent = new Intent(this, ControllerActivity.class);
		intent.putExtra("botao", "botaoCredito");
		super.finish();
		startActivity(intent);
	}
	
	public void botaoHistoria(View view){
		Intent intent = new Intent(this, ControllerActivity.class);
		intent.putExtra("botao", "botaoHistoria");
		super.finish();
		startActivity(intent);
	}
	
	public void botaoComoJogar(View view){
		Intent intent = new Intent(this, ControllerActivity.class);
		intent.putExtra("botao", "botaoComoJogar");
		super.finish();
		startActivity(intent);
	}
}

