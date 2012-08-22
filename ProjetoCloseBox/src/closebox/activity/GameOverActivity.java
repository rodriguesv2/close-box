package closebox.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Classe responsavel por mostrar a tela "GameOver" ao jogador
 * @author Reinaldo
 *
 */
public class GameOverActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Coloca a tela main a frente.
        setContentView(R.layout.game_over);
        
    }
}
