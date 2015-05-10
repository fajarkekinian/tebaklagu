package aboot.tebaklagu.manager;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.util.color.Color;

import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.scene.Benar;
import aboot.tebaklagu.scene.CaraScene;
import aboot.tebaklagu.scene.CategoryScene;
import aboot.tebaklagu.scene.CreditScene;
import aboot.tebaklagu.scene.GameScene;
import aboot.tebaklagu.scene.Loading;
import aboot.tebaklagu.scene.MenusScene;
import aboot.tebaklagu.scene.SettingScene;
import aboot.tebaklagu.scene.SplashScene;
import aboot.tebaklagu.scene.level.DaerahScene;
import aboot.tebaklagu.scene.level.DangdutScene;
import aboot.tebaklagu.scene.level.PopScene;
import aboot.tebaklagu.scene.level.RockScene;

public class SceneManager {

	private BaseScene splashScene;
	private BaseScene menuScene;
	private BaseScene creditScene;
	private BaseScene caraScene;
	private BaseScene settingScene;
	private BaseScene categoryScene;
	private BaseScene popScene;
	private BaseScene rockScene;
	private BaseScene dangdutScene;
	private BaseScene daerahScene;
	private BaseScene gameScene;
	private BaseScene benarScene;
	private BaseScene loadScene;
	
	private static final SceneManager INSTANCE = new SceneManager();
	private SceneType currentSceneType = SceneType.SCENE_SPLASH;
	private BaseScene currentScene;
	private Engine engine = ResourceManager.getInstance().engine;
	
	public enum SceneType{
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_CATE,
        SCENE_POP,
        SCENE_ROCK,
        SCENE_DANGDUT,
        SCENE_DAERAH,
        SCENE_GAME,
        SCENE_CREDIT,
        SCENE_CARA,
        SCENE_SETTING,
        SCENE_BENAR,
        SCENE_LOAD
    }
	
	public void setScene(BaseScene scene){
		engine.setScene(scene);
		currentScene = scene;
		currentSceneType = scene.getSceneType();
	}
	public void setScene(SceneType sceneType){
		switch (sceneType) {
		case SCENE_MENU:
			setScene(menuScene);
			break;
		case SCENE_CATE:
			setScene(categoryScene);
			break;
		case SCENE_POP:
			setScene(popScene);
			break;
		case SCENE_ROCK:
			setScene(rockScene);
			break;
		case SCENE_DANGDUT:
			setScene(dangdutScene);
			break;
		case SCENE_DAERAH:
			setScene(daerahScene);
			break;
		case SCENE_SPLASH:
			setScene(splashScene);
			break;
		case SCENE_CREDIT:
			setScene(creditScene);
			break;
		case SCENE_CARA:
			setScene(caraScene);
			break;
		case SCENE_SETTING:
			setScene(settingScene);
			break;
		case SCENE_GAME:
			setScene(gameScene);
			break;
		case SCENE_BENAR:
			setScene(benarScene);
			break;
		case SCENE_LOAD:
			setScene(loadScene);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * load splash
	 * @param pOnCreateSceneCallback
	 */
	public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback){
	    ResourceManager.getInstance().loadSplashScreen();
	    splashScene = new SplashScene();
	    currentScene = splashScene;
	    splashScene.setBackground(new Background(Color.BLACK));
	    pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
	}
	public void disposeSplashScene(){
	    ResourceManager.getInstance().unloadSplashScreen();
	    splashScene.disposeScene();
	    splashScene = null;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * load menu
	 */
	public void createMenuScene(final Engine mEngine){
	    ResourceManager.getInstance().loadMenuResource();
	    menuScene = new MenusScene();
	    SceneManager.getInstance().setScene(menuScene);
	}

	public void createCateScene(final Engine mEngine){
		ResourceManager.getInstance().unloadMenuGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadCategoryResource();
	    		categoryScene = new CategoryScene();
	    	    SceneManager.getInstance().setScene(categoryScene);   
	        }
	    })); 
	}

	public void createPOPScene(final Engine mEngine){
		ResourceManager.getInstance().unloadCategoryGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadPopResource();
	    		popScene = new PopScene();
	    		loadScene = new Loading();
	    		SceneManager.getInstance().setScene(popScene);  
	        }
	    })); 
	}
	public void createROCKScene(final Engine mEngine){
		ResourceManager.getInstance().unloadCategoryGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadRockResource();
	    		rockScene = new RockScene();
	    		loadScene = new Loading();
	    		SceneManager.getInstance().setScene(rockScene);  
	        }
	    })); 
	}
	public void createDANGDUTScene(final Engine mEngine){
		ResourceManager.getInstance().unloadCategoryGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadDangdutResource();
	    		dangdutScene = new DangdutScene();
	    		loadScene = new Loading();
	    		SceneManager.getInstance().setScene(dangdutScene); 
	        }
	    })); 
	}
	public void createDAERAHScene(final Engine mEngine){
		ResourceManager.getInstance().unloadCategoryGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadDaerahResource();
	    		daerahScene = new DaerahScene();
	    		loadScene = new Loading();
	    		SceneManager.getInstance().setScene(daerahScene);
	        }
	    })); 
	}
	
	public void createGAMEScene(final Engine mEngine, String kate){
		setScene(loadScene);
		if (kate.equals("pop")) {
			ResourceManager.getInstance().unloadPOPGraphics();
		}else if (kate.equals("rock")) {
			ResourceManager.getInstance().unloadROCKGraphics();
		}else if (kate.equals("dangdut")) {
			ResourceManager.getInstance().unloadDANGDUTGraphics();
		}else if (kate.equals("daerah")) {
			ResourceManager.getInstance().unloadDAERAHGraphics();
		}else if (kate.equals("Benar")) {
			
		}
		 mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
		    {
		        public void onTimePassed(final TimerHandler pTimerHandler) 
		        {
		        	mEngine.unregisterUpdateHandler(pTimerHandler);
		        	ResourceManager.getInstance().loadGAMEGraphics();
		    		gameScene = new GameScene();
		    		SceneManager.getInstance().setScene(gameScene);
		        }
		    }));
		
		
	}
	
	

	public void loadPopScene(final Engine mEngine){
		gameScene.dispose();
		setScene(loadScene);
		 mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
		    {
		        public void onTimePassed(final TimerHandler pTimerHandler) 
		        {
		        	mEngine.unregisterUpdateHandler(pTimerHandler);
		        	ResourceManager.getInstance().loadPopResource();
		    		popScene = new PopScene();
		    		SceneManager.getInstance().setScene(popScene);
		        }
		    }));
		
	}
	public void loadRockScene(final Engine mEngine){
		gameScene.dispose();
		setScene(loadScene);
		 mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
		    {
		        public void onTimePassed(final TimerHandler pTimerHandler) 
		        {
		        	mEngine.unregisterUpdateHandler(pTimerHandler);
		        	ResourceManager.getInstance().loadRockResource();
		    		rockScene = new RockScene();
		    		SceneManager.getInstance().setScene(rockScene);
		        }
		    }));
		
	}
	public void loadDANGDUTScene(final Engine mEngine){
		gameScene.dispose();
		setScene(loadScene);
		 mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
		    {
		        public void onTimePassed(final TimerHandler pTimerHandler) 
		        {
		        	mEngine.unregisterUpdateHandler(pTimerHandler);
		        	ResourceManager.getInstance().loadDangdutResource();
		    		dangdutScene = new DangdutScene();
		    		SceneManager.getInstance().setScene(dangdutScene);
		        }
		    }));
		
	}
	public void loadDAERAHScene(final Engine mEngine){
		gameScene.dispose();
		setScene(loadScene);
		 mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
		    {
		        public void onTimePassed(final TimerHandler pTimerHandler) 
		        {
		        	mEngine.unregisterUpdateHandler(pTimerHandler);
		        	ResourceManager.getInstance().loadDaerahResource();
		    		daerahScene = new DaerahScene();
		    		SceneManager.getInstance().setScene(daerahScene);
		        }
		    }));
		
	}
	public void createBENARScene(final Engine mEngine){
		ResourceManager.getInstance().unloadGAMEGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadBENARGraphics();
	    		benarScene = new Benar();
	    		SceneManager.getInstance().setScene(benarScene);
	        }
	    }));
		
	}
	public void createCaraScene(final Engine mEngine){
		ResourceManager.getInstance().unloadMenuGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadCARAGraphics();
			    caraScene = new CaraScene();
			    SceneManager.getInstance().setScene(caraScene);  
	        }
	    })); 
	}
	public void createCreditScene(final Engine mEngine){
		ResourceManager.getInstance().unloadMenuGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadCARAGraphics();
			    creditScene = new CreditScene();
			    SceneManager.getInstance().setScene(creditScene);
	        }
	    })); 
	}
	public void loadMenuScene(final Engine mEngine){
		ResourceManager.getInstance().loadMenuResource();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().unLoadCARAGraphics();
			    menuScene = new MenusScene();
			    SceneManager.getInstance().setScene(menuScene);  
	        }
	    })); 
	}
	public void createSettingScene(final Engine mEngine){
		ResourceManager.getInstance().unloadMenuGraphics();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().loadSettingResource();
			    settingScene = new SettingScene();
			    SceneManager.getInstance().setScene(settingScene);
	        }
	    })); 
	}
	public void loadMenusScene(final Engine mEngine){
		ResourceManager.getInstance().loadMenuResource();
		mEngine.registerUpdateHandler(new TimerHandler(0.001f, new ITimerCallback(){
	        public void onTimePassed(final TimerHandler pTimerHandler){
	        	mEngine.unregisterUpdateHandler(pTimerHandler);
	        	ResourceManager.getInstance().unloadSettinggraphics();
			    menuScene = new MenusScene();
			    SceneManager.getInstance().setScene(menuScene);  
	        }
	    })); 
	}
	public static SceneManager getInstance(){
		return INSTANCE;
	}
	public SceneType getCurrentSceneType(){
		return currentSceneType;
	}
	public BaseScene getCurrentScene(){
		return currentScene;
	}
}
