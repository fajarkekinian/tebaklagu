package aboot.tebaklagu.scene.level;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.helper.LaguAdapter;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;
import aboot.tebaklagu.scene.GameScene;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class PopScene extends BaseScene implements IOnMenuItemClickListener{

	private LaguAdapter db;
	private Text level1, level2, level3, level4;
	private MenuScene menuChildScene;
	private final String kategori = "pop";
	private final int no1= 1;
	private final int no2= 2;
	private final int no3 = 3;
	private final int no4 = 4;
	private IMenuItem lvl1, lvl2, lvl3, lvl4;
	private int CW,CH,CWLEVEL, CWhead;
	private int CWCOUNT, CHCOUNT, CHCOUNT1, CHCOUNT2, CHCOUNT3, CHCOUNT4;
	private Rectangle header;
	private Sprite pop;
	
	@Override
	public void createScene() {
		SikatSemuaScene();
	}

	@Override
	public void onBackKeyPressed() {
		resourcesManager.click.play();
		resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
		SceneManager.getInstance().createCateScene(engine);
		db.closeDB();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_POP;
	}

	@Override
	public void disposeScene() {}


	private void SikatSemuaScene(){
		CW = (int) camera.getWidth();
		CH = (int) camera.getHeight();
		db = new LaguAdapter(activity);
		create();
		custom();
		attach();
		position();
		
		menuChildScene.setOnMenuItemClickListener(this);
		
	}
	private void create(){
		menuChildScene = new MenuScene(camera);
		header = new Rectangle(0, 0, CW, 70, vbom);
		pop = new Sprite(0, 0, resourcesManager.regionpophead, vbom);
		lvl1 = new ScaleMenuItemDecorator(new SpriteMenuItem(no1, resourcesManager.regionpop1, vbom), 1.1f, 1);
		level1 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(1)+"/"+db.getPopLevel(1), vbom);
		if (db.getPopLevelstatus(1) == db.getPopLevel(1)) {
			lvl2 = new ScaleMenuItemDecorator(new SpriteMenuItem(no2, resourcesManager.regionpop2, vbom), 1.1f, 1);
			level2 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(2)+"/"+db.getPopLevel(2), vbom);	
		}else{
			lvl2 = new ScaleMenuItemDecorator(new SpriteMenuItem(10, resourcesManager.regiondisable, vbom), 1f, 1);
			level2 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(2)+"/"+db.getPopLevel(2), vbom);
			level2.setAlpha(0);
		}
		
		if (db.getPopLevelstatus(2) == db.getPopLevel(2)) {
			lvl3 = new ScaleMenuItemDecorator(new SpriteMenuItem(no3, resourcesManager.regionpop3, vbom), 1.1f, 1);
			level3 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(3)+"/"+db.getPopLevel(3), vbom);	
		}else{
			lvl3 = new ScaleMenuItemDecorator(new SpriteMenuItem(10, resourcesManager.regiondisable, vbom), 1f, 1);
			level3 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(3)+"/"+db.getPopLevel(3), vbom);
			level3.setAlpha(0);
		}
		
		if (db.getPopLevelstatus(3) == db.getPopLevel(3)) {
			lvl4 = new ScaleMenuItemDecorator(new SpriteMenuItem(no4, resourcesManager.regionpop4, vbom), 1.1f, 1);
			level4 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(4)+"/"+db.getPopLevel(4), vbom);	
		}else{
			lvl4 = new ScaleMenuItemDecorator(new SpriteMenuItem(10, resourcesManager.regiondisable, vbom), 1f, 1);
			level4 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(4)+"/"+db.getPopLevel(4), vbom);
			level4.setAlpha(0);
		}
		db.closeDB();
//		lvl2 = new ScaleMenuItemDecorator(new SpriteMenuItem(no2, resourcesManager.regionpop2, vbom), 1.1f, 1);
//		level2 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(2)+"/"+db.getPopLevel(2), vbom);
//		lvl3 = new ScaleMenuItemDecorator(new SpriteMenuItem(no3, resourcesManager.regionpop3, vbom), 1.1f, 1);
//		level3 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(3)+"/"+db.getPopLevel(3), vbom);
//		lvl4 = new ScaleMenuItemDecorator(new SpriteMenuItem(no4, resourcesManager.regionpop4, vbom), 1.1f, 1);
//		level4 = new Text(0, 0, resourcesManager.helvetica, ""+db.getPopLevelstatus(4)+"/"+db.getPopLevel(4), vbom);
	}
	private void attach(){
		setChildScene(menuChildScene);
		menuChildScene.attachChild(header);
		header.attachChild(pop);
		menuChildScene.addMenuItem(lvl1);
		lvl1.attachChild(level1);
		menuChildScene.addMenuItem(lvl2);
		lvl2.attachChild(level2);
		menuChildScene.addMenuItem(lvl3);
		lvl3.attachChild(level3);
		menuChildScene.addMenuItem(lvl4);
		lvl4.attachChild(level4);
	}
	private void position(){
		CWhead = (int) ((CW - pop.getWidth()) / 2);
		CWCOUNT = (int) ((lvl1.getWidth() - level1.getWidth()) / 2);
		CHCOUNT = (int) (lvl1.getHeight() - level1.getHeight() - 10);
		CWLEVEL = (int) ((CW - lvl1.getWidth()) / 2);
		CHCOUNT1 = (int) ((CH - lvl1.getHeight()) / 6);
		CHCOUNT2 = (int) ((CH - lvl2.getHeight()) / 6 + lvl1.getHeight());
		CHCOUNT3 = (int) ((CH - lvl3.getHeight()) / 6 + (lvl1.getHeight() * 2));
		CHCOUNT4 = (int) ((CH - lvl4.getHeight()) / 6 + (lvl1.getHeight() * 3));
		menuChildScene.setPosition(0, 0);
		pop.setPosition(CWhead, 0);
		lvl1.setPosition(CWLEVEL, CHCOUNT1);
		level1.setPosition(CWCOUNT, CHCOUNT);
		lvl2.setPosition(CWLEVEL, CHCOUNT2);
		level2.setPosition(CWCOUNT, CHCOUNT);
		lvl3.setPosition(CWLEVEL, CHCOUNT3);
		level3.setPosition(CWCOUNT, CHCOUNT);
		lvl4.setPosition(CWLEVEL, CHCOUNT4);
		level4.setPosition(CWCOUNT, CHCOUNT);
	}
	private void custom(){
		menuChildScene.setBackground(new Background(52/255f, 73/255f, 94/255f));
		header.setColor(44/255f, 62/255f, 80/255f);
		Log.e("ukuran layar", "CW: "+CW);
		if (CW == 480) {
		}else if (CW == 720) {
			lvl1.setSize(550, 206);
			lvl2.setSize(550, 206);
			lvl3.setSize(550, 206);
			lvl4.setSize(550, 206);
		}
	}
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
			switch (pMenuItem.getID()) {
			case no1:
				resourcesManager.click.play();
				resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				GameScene.CategoryLevel(kategori, no1);
				SceneManager.getInstance().createGAMEScene(engine, kategori);
				db.closeDB();
				return true;
			
			case no2:
				resourcesManager.click.play();
				resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				GameScene.CategoryLevel(kategori, no2);
				SceneManager.getInstance().createGAMEScene(engine, kategori);
				db.closeDB();
				return true;
			
			case no3:
				resourcesManager.click.play();
				resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				GameScene.CategoryLevel(kategori, no3);
				SceneManager.getInstance().createGAMEScene(engine, kategori);
				db.closeDB();
				return true;
			
			case no4:
				resourcesManager.click.play();
				resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
				GameScene.CategoryLevel(kategori, no4);
				SceneManager.getInstance().createGAMEScene(engine, kategori);
				db.closeDB();
				return true;
			
			default:
				break;
			}
		return false;
	}
}
