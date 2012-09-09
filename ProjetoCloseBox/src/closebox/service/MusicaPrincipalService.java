package closebox.service;

import closebox.activity.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicaPrincipalService extends Service{
	
	private MediaPlayer mediaPlayer;
    private IBinder myBinder = new LocalBinder();
    
    public class LocalBinder extends Binder {
        public MusicaPrincipalService getService() {
            return MusicaPrincipalService.this;
        }
    }
    
    public void pauseMusic(){
    	mediaPlayer.pause();
    }
    
    public void playMusic(){
    	mediaPlayer.start();
    }
    
    @Override  
    public IBinder onBind(Intent i) {
        return myBinder;  
    }  
    @Override  
    public void onCreate() {  
    		mediaPlayer = MediaPlayer.create(this, R.raw.yoho);
    		mediaPlayer.start();
    		mediaPlayer.setLooping(true);
    }  
      
    @Override  
    public void onDestroy() {  
    	mediaPlayer.stop();
    	mediaPlayer.release();
    }
}
