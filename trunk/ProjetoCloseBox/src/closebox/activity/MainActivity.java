package closebox.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import closebox.model.*;

public class MainActivity extends Activity {
    
    private Intent intent;
    
    public void botaoInicio(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class);
    	intent.putExtra("botao", "inicio");
    	startActivity(intent);
		
    }
    
    public void botaoOptions(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class);
    	intent.putExtra("botao", "options");
		startActivity(intent);
		
    }
    
    public void botaoScore(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class);
    	intent.putExtra("botao", "score");
		startActivity(intent);
		
    }
    
    public void botaoSobre(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class);
    	intent.putExtra("botao", "sobre");
    	//dialogo2(intent.get);
		startActivity(intent);
    }
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Coloca a tela main a frente.
        setContentView(R.layout.main);
        
    }
}
