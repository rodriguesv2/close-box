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

/**
 * Classe responsavel por mostrar a tela inicial do jogo e de acordo com o botao tocado pelo jogador, inicializa uma nova Activity
 * @author Reinaldo
 *
 */
public class MainActivity extends Activity {
    
    private Intent intent;
    
    public void botaoInicio(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class); //determina a nova Activity
    	intent.putExtra("botao", "inicio"); // o nome do botao, na verdade uma referencia a ser tratada no controller
    	startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
		
    }
    
    public void botaoOptions(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class); //determina a nova Activity
    	intent.putExtra("botao", "options"); // o nome do botao, na verdade uma referencia a ser tratada no controller
		startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
		
    }
    
    public void botaoScore(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class); //determina a nova Activity
    	intent.putExtra("botao", "score"); // o nome do botao, na verdade uma referencia a ser tratada no controller
		startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
		
    }
    
    public void botaoSobre(View view){
    	intent = new Intent(MainActivity.this, ControllerActivity.class); //determina a nova Activity
    	intent.putExtra("botao", "sobre"); // o nome do botao, na verdade uma referencia a ser tratada no controller
    	startActivity(intent); // inicializa a nova Activity, envia os dados ao controller
    }
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Coloca a tela main a frente.
        setContentView(R.layout.main);
        
    }
}
