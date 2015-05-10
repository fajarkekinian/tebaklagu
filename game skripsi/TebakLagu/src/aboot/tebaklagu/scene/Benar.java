package aboot.tebaklagu.scene;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;
import aboot.tebaklagu.model.Lagu;
import android.media.MediaPlayer;
import android.util.Log;

public class Benar extends BaseScene implements IOnAreaTouchListener{

	private Sprite spritebenar, spritebenarbiru, spritebenarmerah, spritenext;
	private int CWbenar, CHbenar, CWjawab, CHjawab;
	private Text jawaban;
	private Music music;
	private static String lagus, jawabs, cate;
	private static int level;
	private static Lagu catek;
	Rectangle kotak;
	@Override
	public void createScene() {
		setBackground(new Background(Color.BLACK));
		loadLaguGame();
		kebenaran();
	}

	@Override
	public void onBackKeyPressed() {
		if (cate.equals("pop")) {
    		SceneManager.getInstance().loadPopScene(engine);
		}else if (cate.equals("rock")) {
			SceneManager.getInstance().createROCKScene(engine);
		}else if (cate.equals("dangdut")) {
			SceneManager.getInstance().createDANGDUTScene(engine);
		}else if (cate.equals("daerah")) {
			SceneManager.getInstance().createDAERAHScene(engine);
		}
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_BENAR;
	}

	@Override
	public void disposeScene() {}
	public static void ambillagusebelah(String lagu, String jawab, String kate, int lpl){
		lagus = lagu;
		jawabs = jawab;
		cate = kate;
		level = lpl;
		catek = new Lagu();
		catek.setMusik(lagus);
		catek.setJudul(jawabs);
		catek.setStatus(cate);
		catek.setLevel(level);
	}
	private void loadLaguGame(){
		MusicFactory.setAssetBasePath("musik/");
		try {
			music = MusicFactory.createMusicFromAsset(engine.getMusicManager(), activity, ""+catek.getMusik()+".mp3");		
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	private void kebenaran(){
		spritebenar = new Sprite(0, 0, resourcesManager.regionBenar, vbom);
		spritebenarbiru= new Sprite(0, 0, resourcesManager.regionBenarbiru, vbom);
		spritebenarmerah = new Sprite(0, 0, resourcesManager.regionBenarMerah, vbom);
		spritenext = new Sprite(0, 0, resourcesManager.regionnext, vbom);
		
		kotak = new Rectangle(0, camera.getHeight() - 200, camera.getWidth(), 200, vbom);
		kotak.setColor(new Color(46/255f, 204/255f, 113/255f));
		attachChild(kotak);
		kotak.attachChild(spritenext);
		attachChild(spritebenar);
		attachChild(spritebenarbiru);
		attachChild(spritebenarmerah);
		CHbenar = (int) ((camera.getHeight() - spritebenar.getHeight()) / 4 - 20);
		CWbenar = (int) ((camera.getWidth() - spritebenar.getWidth()) / 2);
		spritebenar.setPosition(CWbenar, CHbenar);
		spritebenarbiru.setPosition(CWbenar, CHbenar);
		spritebenarmerah.setPosition(CWbenar, CHbenar);
		spritenext.setPosition((camera.getWidth() - spritenext.getWidth()) / 2, (kotak.getHeight() - spritenext.getHeight()) / 2);
		if (camera.getWidth() == 480) {
			spritebenar.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new ScaleModifier(1f, 0.7f, 1.0f),
									new ScaleModifier(1f, 1.0f, 0.7f))));
			spritebenarbiru.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new RotationByModifier(2.5f, 360))));
			spritebenarmerah.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new RotationByModifier(2.7f, 360))));
		}else{
			spritebenar.setScale(1.5f, 1.5f);
			spritebenarbiru.setScale(1.5f, 1.5f);
			spritebenarmerah.setScale(1.5f, 1.5f);
			spritebenar.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new ScaleModifier(1f, 1.0f, 1.5f),
									new ScaleModifier(1f, 1.5f, 1.0f))));
			spritebenarbiru.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new RotationByModifier(2.5f, 360))));
			spritebenarmerah.registerEntityModifier(
					new LoopEntityModifier(
							new SequenceEntityModifier(
									new RotationByModifier(2.7f, 360))));
		}
		
		jawaban = new Text(0, 0, resourcesManager.helvetica, ""+catek.getJudul(), vbom);
		
		attachChild(jawaban);
		
		CWjawab = (int) ((camera.getWidth() - jawaban.getWidth()) / 2);
		CHjawab = (int) ((camera.getHeight() - jawaban.getHeight()) / 2 + 100);
		
		jawaban.setPosition(CWjawab, CHjawab);
		if (jawaban.getWidth() > camera.getWidth()) {
			jawaban.setScale(0.8f);
		}
		
		
		
		
		registerTouchArea(spritebenar);
		registerTouchArea(spritenext);
		setOnAreaTouchListener(this);
		
		
		
		
		
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
			if (pSceneTouchEvent.isActionUp()) {
				if (spritebenar.contains(pSceneTouchEvent.getX(), pSceneTouchEvent.getY())) {
					music.play();
					spritebenar.setVisible(false);
					music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						
						@Override
						public void onCompletion(MediaPlayer mp) {
							spritebenar.setVisible(true);
							
						}
					});
				}else if (spritenext.contains(pSceneTouchEvent.getX(), pSceneTouchEvent.getY())) {
					Log.e("kate dan level ", ""+catek.getStatus()+"::::"+catek.getLevel());
					GameScene.CategoryLevel(""+catek.getStatus(), catek.getLevel());
					SceneManager.getInstance().createGAMEScene(engine, "Benar");
					MediaPlayer mc = music.getMediaPlayer();
					mc.release();
				}
			}
		return false;
	}
}
