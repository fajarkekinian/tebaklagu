package aboot.tebaklagu.scene;

import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MenusScene extends BaseScene implements IOnMenuItemClickListener{

	private MenuScene menuChildScene;
	private final int font_mulai = 0;
	private final int font_carabermain = 1;
	private final int font_credit = 2;
	private final int font_setting = 3;
	private IMenuItem mulai, carabermain, credit, setting;
	private int CW,CH,CWmulai, CHmulai, CWcarabermain, CHcarabermain, CHcredit, CWlogoa, CHlogoa, CWsetting;
	private Rectangle kotak ;
	private Sprite bg, logoa, logob;
	CategoryScene cateeeee;
	@Override
	public void createScene() {
		SikatSemuaScene();
	}

	@Override
	public void onBackKeyPressed() {
		System.exit(0);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {}
	private void SikatSemuaScene(){
		CW = (int) camera.getWidth();
		CH = (int) camera.getHeight();
		create();
		custom();
		attach();
		position();
		
		menuChildScene.setOnMenuItemClickListener(this);
		
	}
	private void create() {
		menuChildScene = new MenuScene(camera);
		bg = new Sprite(0, 0, resourcesManager.regionbg, vbom);
		logoa = new Sprite(0, 0, resourcesManager.regionlogoa, vbom);
		logob = new Sprite(0, 0, resourcesManager.regionlogob, vbom);
		mulai = new ScaleMenuItemDecorator(new SpriteMenuItem(font_mulai, resourcesManager.regionbtnmulai, vbom), 1.1f, 1);
		carabermain = new ScaleMenuItemDecorator(new SpriteMenuItem(font_carabermain, resourcesManager.regionbtncarabermain, vbom), 1.1f, 1);
		kotak = new Rectangle(0, CH - 100, CW, 100, vbom);
		credit = new ScaleMenuItemDecorator(new SpriteMenuItem(font_credit, resourcesManager.regioncredit, vbom), 1.1f, 1);
		setting = new ScaleMenuItemDecorator(new SpriteMenuItem(font_setting, resourcesManager.regionsetting, vbom), 1.1f, 1);
		
		
	}

	private void attach() {
		setChildScene(menuChildScene);
		menuChildScene.attachChild(bg);
		bg.attachChild(logoa);
		bg.attachChild(logob);
		menuChildScene.addMenuItem(mulai);
		menuChildScene.addMenuItem(carabermain);
		menuChildScene.attachChild(kotak);
		menuChildScene.addMenuItem(credit);
		menuChildScene.addMenuItem(setting);
	}

	private void position() {
		CWlogoa = (int) ((CW - logoa.getWidth()) / 2);
		CHlogoa = (int) ((CH - logoa.getHeight()) / 8);
		CWmulai = (int) ((CW - mulai.getWidth()) / 2);
		CHmulai = (int) ((CH - mulai.getHeight()) / 3 + (CH / 4));
		CWcarabermain = (int) ((CW - carabermain.getWidth()) / 2);
		CHcarabermain = (int) ((CH - carabermain.getHeight()) / 3 + (CH / 4) + mulai.getHeight());
		CHcredit = (int) (CH - credit.getHeight());
		CWsetting = (int) (CW - setting.getWidth());
		
		menuChildScene.setPosition(0, 0);
		logoa.setPosition(CWlogoa, CHlogoa);
		logob.setPosition(CWlogoa, CHlogoa);
		mulai.setPosition(CWmulai , CHmulai);
		carabermain.setPosition(CWcarabermain, CHcarabermain);
		credit.setPosition(0 , CHcredit);
		setting.setPosition(CWsetting, CHcredit);
		
	}

	private void custom() {
		menuChildScene.setBackground(new Background(255/255f, 153/255f, 0/255f));
		kotak.setColor(22/255f, 160/255f, 133/255f, 0.6f);
		if (CW <= 500) {
			
		}else if (CW >= 600) {
			logoa.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new ScaleModifier(1f, 1.0f, 1.3f),
									new ScaleModifier(1f, 1.3f, 1.0f))));
			logob.setScale(1.3f, 1.3f);
			mulai.setSize(400, 120);
			carabermain.setSize(400, 120);
			credit.setSize(340, 102);
			setting.setSize(340, 102);
			kotak.setSize(CW, 150);
		}else{
			
			
		}
		logob.registerEntityModifier(
				new LoopEntityModifier(
						new SequenceEntityModifier(
								new RotationByModifier(10f, 360))));
		logoa.registerEntityModifier(
				new LoopEntityModifier(
						new SequenceEntityModifier(
								new ScaleModifier(1f, 0.8f, 1.f),
								new ScaleModifier(1f, 1.0f, 0.8f))));
		
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY){
		switch (pMenuItem.getID()) {
		case font_mulai:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createCateScene(engine);
			return true;
		case font_carabermain:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createCaraScene(engine);
			return true;
		case font_credit:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createCreditScene(engine);
			return true;
		case font_setting:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createSettingScene(engine);
			return true;
		default:
			break;
		}
		return false;
	}
	
}
