package aboot.tebaklagu.scene;

/*
 * author by aboot |*splashscene|09062014|
 */



import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.engine.camera.Camera;

import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.manager.SceneManager.SceneType;

public class SplashScene extends BaseScene{

	private int CW,CH,CWsplash,CHsplash;
	private Sprite splash;
	@Override
	public void createScene() {
		splash = new Sprite(0, 0, resourcesManager.regionSplash, vbom){
			protected void preDraw(GLState pGLState, Camera pCamera){
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		
		CW = (int) camera.getWidth();
		CH = (int) camera.getHeight();
		CWsplash = (int) ((CW - splash.getWidth()) / 2);
		CHsplash = (int) ((CH - splash.getWidth()) / 2);
		splash.setPosition(CWsplash, CHsplash);
		splash.setScale(0.01f);
		splash.registerEntityModifier(
				new SequenceEntityModifier(
						new ScaleModifier(0.8f, 0.0f, 0.0f),
						new ScaleModifier(0.1f, 0.09f, 0.9f),
				new SequenceEntityModifier(
						new ScaleModifier(0.5f, 0.9f, 0.9f),
						new AlphaModifier(0.5f, 0.8f, 0.2f),
						new AlphaModifier(0.5f, 0.2f, 0.8f),
				new SequenceEntityModifier(
						new AlphaModifier(0.5f, 0.8f, 0.1f),
						new AlphaModifier(1f, 0.1f, 0.9f),
				new SequenceEntityModifier(
						new ScaleModifier(1f, 0.9f, 0.9f),
						new ScaleModifier(0.2f, 0.8f, 0.0f))))));
		attachChild(splash);
	}

	@Override
	public void onBackKeyPressed() {}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SPLASH;
	}
	@Override
	public void disposeScene() {
		splash.detachSelf();
		splash.dispose();
		this.detachSelf();
		this.dispose();
	}
}
