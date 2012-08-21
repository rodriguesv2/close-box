package closebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OptionsActivity extends Activity{
	
	private TextView credito;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.costrucao);
		
		credito = (TextView)findViewById(R.id.textConstrucao);
		
		mostraCredito();
		
	}
	
	public void mostraCredito(){
		credito.setText("EM CONSTRUCAO");
	}
	
	
	
	
	
	
}