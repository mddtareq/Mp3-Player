import javazoom.jl.player.advanced.PlaybackListener;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.*;
import java.io.*;



public class Streamer{

	private final Object playerLock = new Object();
	private boolean isPaused = false;
	private int status = 0;
	private Player player;


	public Streamer( String fileName ){
		try{
			player = new Player( new FileInputStream( fileName ) );
		}
		catch( Exception ex ){
			player = null;
		}
	}


	public void play(){
		synchronized( playerLock ){
			final Thread th = new Thread(
				new Runnable(){
					public void run(){
						try{
							playSong();
						}
						catch( Exception ex ){

						}
					}
				}
			);
			status = 0;
			th.start();
		}	
	}

	public void playSong(){
		while( status!=3 ){
			try{
				if( !player.play(1) )
					break;
			}
			catch( Exception ex ){
			}
			synchronized( playerLock ){
				while( status==1 ){
					try{
						playerLock.wait();
					}
					catch( Exception ex ){
					}
				}		
			}
		}
		end();
	}


	public void pause(){
		synchronized( playerLock ){
			status = 1;
		}
	}

	public void resume(){
		synchronized( playerLock ){
			status = 0;
			playerLock.notifyAll();
		}
	}

	public void end(){
		synchronized (playerLock) {
            status = 3;
        }
        try {
            player.close();
        } 
        catch ( Exception e) {
        }
	}
}