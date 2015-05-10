package aboot.tebaklagu;

import java.io.IOException;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;
import aboot.tebaklagu.helper.LaguHelper;
import aboot.tebaklagu.manager.ResourceManager;
import aboot.tebaklagu.manager.SceneManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Display;
import android.view.KeyEvent;

public class Tebaklagu extends BaseGameActivity {

	private Camera camera;
	private int CWIDHT, CHEIGHT;
	private EngineOptions eOptions;
	ResourceManager resourceManager;
	SQLiteDatabase sqLiteDatabase;
	Display display;
	LaguHelper myDbHelper;
	@SuppressWarnings("deprecation")
	@Override
	public EngineOptions onCreateEngineOptions() {
		display = getWindow().getWindowManager().getDefaultDisplay();
		CWIDHT = display.getWidth();
		CHEIGHT = display.getHeight();
		camera = new Camera(0, 0, CWIDHT, CHEIGHT);
		eOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED
				, new RatioResolutionPolicy(CWIDHT, CHEIGHT), camera);
		eOptions.getAudioOptions().setNeedsMusic(true);
		eOptions.getAudioOptions().setNeedsSound(true);
		return eOptions;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		ResourceManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
		resourceManager = ResourceManager.getInstance();
		ngopidatabase();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		
		SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback ponOnPopulateSceneCallback)
			throws Exception {
		 mEngine.registerUpdateHandler(new TimerHandler(5.5f, new ITimerCallback() 
		    {
		            public void onTimePassed(final TimerHandler pTimerHandler) 
		            {
		                mEngine.unregisterUpdateHandler(pTimerHandler);
		                SceneManager.getInstance().createMenuScene(mEngine);
		                SceneManager.getInstance().disposeSplashScene();
		            }
		    }));
		ponOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{  
	    if (keyCode == KeyEvent.KEYCODE_BACK)
	    {
	    	SceneManager.getInstance().getCurrentScene().onBackKeyPressed();
	    }
	    return false; 
	}
	   private void ngopidatabase(){
		   myDbHelper = new LaguHelper(this);
	       myDbHelper = new LaguHelper(this);
	       try {
	       	myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
	   }
	@Override
	protected void onDestroy(){
		super.onDestroy();
		System.exit(0);
		myDbHelper.close();
	}
}
