package closebox.activity;

import android.app.Activity;
import android.os.Bundle;

public class GameOverActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Coloca a tela main a frente.
        setContentView(R.layout.game_over);
        
    }
}
