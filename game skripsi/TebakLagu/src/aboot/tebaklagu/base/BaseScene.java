package aboot.tebaklagu.base;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import aboot.tebaklagu.manager.ResourceManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;
import android.app.Activity;

public abstract class BaseScene extends Scene {

	protected Engine engine;
	protected Activity activity;
	protected ResourceManager resourcesManager;
	protected VertexBufferObjectManager vbom;
	protected Camera camera;

	public BaseScene(){
		this.resourcesManager = ResourceManager.getInstance();
		this.engine = resourcesManager.engine;
		this.activity = resourcesManager.activity;
		this.vbom = resourcesManager.vbom;
		this.camera = resourcesManager.camera;
		createScene();
	}

	public abstract void createScene();
	public abstract void onBackKeyPressed();
	public abstract SceneType getSceneType();
	public abstract void disposeScene();
}
