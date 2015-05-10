package aboot.tebaklagu.scene;

import java.io.IOException;
import java.util.List;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.color.Color;

import aboot.tebaklagu.R;
import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.helper.LaguAdapter;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;
import aboot.tebaklagu.model.Category;
import aboot.tebaklagu.model.Lagu;
import aboot.tebaklagu.model.Nyawa;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

public class GameScene extends BaseScene implements IOnAreaTouchListener{

	private Sprite spriteplay, spriteok, player, playerA, playerB, komplit, komplitb, spriteback;
	private int CW, CH, CWplayer, CHplayer, CWspriteok, CHspriteok, CWplayerA, CHplayerA, CWkomplit, CHkomplit;
	private Rectangle header;
	private Music music;
	private String menjawab;
	private String jawaban, nada;
	private int idlagu, idcates, levels;
	private int idjiwa, jiwajiwa;
	private static String kate;
	private static int lepel;
	private static Category kateg;
	private LaguAdapter db;
	private Rectangle kotak;
	
	private TiledSprite tilednyawa;
	public static void CategoryLevel(String cate, int lvl){
		kate = cate;
		lepel = lvl;
		kateg = new Category();
		kateg.setKate(kate);
		kateg.setLepel(lepel);
		
	}
	
	@Override
	public void createScene() {
		//db lagu
		db = new LaguAdapter(activity);
		List<Lagu>putaran = db.getSemuaLagu(kateg.getKate(), kateg.getLepel());
		db.closeDB();
		for(Lagu lagu : putaran){
			Log.e("id", ""+lagu.getID());
			Log.e("id_cate", ""+lagu.getIDCate());
			Log.e("musik", ""+lagu.getMusik());
			Log.e("judul", ""+lagu.getJudul());
			Log.e("level", ""+lagu.getLevel());
			idlagu = lagu.getID();
			idcates = lagu.getIDCate();
			levels = lagu.getLevel();
			nada = lagu.getMusik();
			jawaban = lagu.getJudul();
			Benar.ambillagusebelah(nada, jawaban, kateg.getKate(), kateg.getLepel());
		}
		
		List<Nyawa> jancuk = db.getNyawa(idcates, levels);
		db.closeDB();
		for(Nyawa nyawa : jancuk){
			idjiwa = nyawa.getID();
			nyawa.getCate();
			jiwajiwa = nyawa.getJiwa();
		}
		
		
		if (jawaban == null) {
			setBackground(new Background(0/255f, 0/255f, 0/255f));
			komplit();
			
		}else{
			
	        SikatSemuaScene();
	        ShowEdittext();
	        loadLaguGame();
	        
	        engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback(){
		        public void onTimePassed(final TimerHandler pTimerHandler){
		        	engine.unregisterUpdateHandler(pTimerHandler);
		        	LihatEdittext();
		        	
		        	music.play();
		        	lagu();
		        }
		    }));
		}
	}

	@Override
	public void onBackKeyPressed() {
		if (jawaban == null) {
			close(kateg.getKate());
		}else{
			 engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback(){
			        public void onTimePassed(final TimerHandler pTimerHandler){
			        	engine.unregisterUpdateHandler(pTimerHandler);
			        	music.stop();
			        }
			    }));
			
	    	close(kateg.getKate());
		}
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {}
	private void loadLaguGame(){
		MusicFactory.setAssetBasePath("musik/");
		try {
			music = MusicFactory.createMusicFromAsset(engine.getMusicManager(), activity, ""+nada+".mp3");		
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	private void SikatSemuaScene(){
		CW = (int) camera.getWidth();
		CH = (int) camera.getHeight();
		db = new LaguAdapter(activity);
		create();
		custom();
		attach();
		position();
		

		
	}
	

	private void create() {
		header = new Rectangle(0, 0, camera.getWidth(), 70, vbom);
		player = new Sprite(0, 0, resourcesManager.regionDj, vbom);
		playerA = new Sprite(0, 0, resourcesManager.regionDisk, vbom);
		playerB = new Sprite(0, 0, resourcesManager.regiongagang, vbom);
		spriteplay = new Sprite(0, 0, resourcesManager.regionplay, vbom);
		spriteok = new Sprite(0, 0, resourcesManager.regioncheck, vbom);
		tilednyawa = new TiledSprite(0, 0, resourcesManager.regionnyawa, vbom);
		
	}

	private void attach() {
		attachChild(header);
		attachChild(player);
		attachChild(spriteok);
		player.attachChild(playerA);
		player.attachChild(spriteplay);
		player.attachChild(playerB);
		header.attachChild(tilednyawa);
	}
	
	private void position() {
		if (CW <= 480) {
			CHplayer = (int) ((CH - player.getHeight()) / 4 - 20);
			CWplayer = (int) ((CW - player.getWidth()) / 2);
			CWspriteok = (int) ((CW - spriteok.getWidth()) - 20);
			CHspriteok = (int) ((CH - spriteok.getHeight()) / 2 + 70);
			CWplayerA = (int) ((player.getWidth() - playerA.getWidth()) / 2);
			CHplayerA = (int) ((player.getHeight() - playerA.getHeight()) / 2);
			player.setPosition(CWplayer, CHplayer);
			playerA.setPosition(CWplayerA, CHplayerA);
			spriteok.setPosition(CWspriteok ,CHspriteok);
			
		}else if (CW >= 700) {
			CHplayer = (int) ((CH - player.getHeight()) / 4 - 20);
			CWplayer = (int) ((CW - player.getWidth()) / 2);
			CWspriteok = (int) ((CW - spriteok.getWidth()) - 20);
			CHspriteok = (int) ((CH - spriteok.getHeight()) / 2 + 70);
			CWplayerA = (int) ((player.getWidth() - playerA.getWidth()) / 2);
			CHplayerA = (int) ((player.getHeight() - playerA.getHeight()) / 2);
			player.setPosition(CWplayer, CHplayer);
			playerA.setPosition(CWplayerA, CHplayerA);
			spriteok.setPosition(CWspriteok ,CHspriteok);
			player.setScale(1.5f, 1.5f);
			playerA.setScale(1f, 1f);
			playerB.setScale(1f, 1f);
			spriteplay.setScale(1f, 1f);
		}
		
	}
	
	private void custom() {
		setBackground(new Background(52/255f, 152/255f, 219/255f));
		header.setColor(41/255f, 128/255f, 185/255f);
		playerA.registerEntityModifier(
				new LoopEntityModifier(
						new SequenceEntityModifier(
								new RotationByModifier(1f, 360))));
		
		spriteplay.setVisible(false);
		
		registerTouchArea(spriteok);
		setOnAreaTouchListener(this);
		Log.e("kate", ""+kateg.getKate());
		Log.e("lepel", ""+kateg.getLepel());
		tilednyawa.setCurrentTileIndex(jiwajiwa);
	}
	private void lagu(){
		
		music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				spriteplay.setVisible(true);
				registerTouchArea(spriteplay);
			}
		});
	}
	
	
	
	
	private void komplit(){
		komplit = new Sprite(0, 0, resourcesManager.regionkomplit, vbom);
		komplitb = new Sprite(0, 0, resourcesManager.regionkomplitb, vbom);
		attachChild(komplit);
		attachChild(komplitb);
		CWkomplit = (int) ((camera.getWidth() - komplit.getWidth()) / 2);
		CHkomplit = (int) ((camera.getHeight() - komplit.getHeight()) / 4);
		komplit.setPosition(CWkomplit, CHkomplit);
		komplitb.setPosition(CWkomplit, CHkomplit);
		komplitb.registerEntityModifier(
				new LoopEntityModifier(
						new SequenceEntityModifier(
								new RotationByModifier(3.0f, 360))));
		
		kotak = new Rectangle(0, camera.getHeight() - 200, camera.getWidth(), 200, vbom);
		spriteback = new Sprite(0, 0, resourcesManager.regionback, vbom);
		kotak.setColor(231/255F,76/255F,60/255F);
		attachChild(kotak);
		kotak.attachChild(spriteback);
		spriteback.setPosition((camera.getWidth() - spriteback.getWidth()) / 2, (kotak.getHeight() - spriteback.getHeight()) / 2);
		registerTouchArea(spriteback);
		setOnAreaTouchListener(new IOnAreaTouchListener() {
			
			@Override
			public boolean onAreaTouched(TouchEvent arg0, ITouchArea arg1, float arg2,
					float arg3) {
				close(kateg.getKate());
				return true;
			}
		});
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			ITouchArea pTouchArea, float pTouchAreaLocalX,
			float pTouchAreaLocalY) {
			if (pSceneTouchEvent.isActionUp()) {
				if (spriteplay.contains(pSceneTouchEvent.getX(), pSceneTouchEvent.getY())) {
					music.play();
					spriteplay.setVisible(false);
				}else if (spriteok.contains(pSceneTouchEvent.getX(), pSceneTouchEvent.getY())) {
					menjawab = resourcesManager.editText.getText().toString().toUpperCase();
					String[] words = jawaban.split("\\s+");
					for (int i = 0; i < words.length; i++) {
						if (menjawab.equals(""+jawaban)) {
							MediaPlayer mc = music.getMediaPlayer();
							mc.release();
							db.updateMusik(kateg.getKate(), idlagu);
							Log.e("kategori ama idlagu", ""+kateg.getKate()+""+idlagu);
							SceneManager.getInstance().createBENARScene(engine);
							engine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback(){
						        public void onTimePassed(final TimerHandler pTimerHandler){
						        	engine.unregisterUpdateHandler(pTimerHandler);
						        	showturunkeyboard();
						        }
						    }));
							db.closeDB();
						}else if (menjawab.matches(".*"+words[i]+".*")) {
							showkepastian("nyaris");
							return false;
						}
					}
					if (!menjawab.equals(""+jawaban)) {
						
						
						if (tilednyawa.getCurrentTileIndex() == 3) {
							tilednyawa.setCurrentTileIndex(2);
							showkepastian("salah");
							db.updateNyawa(idjiwa, tilednyawa.getCurrentTileIndex());
							db.closeDB();
						}else if (tilednyawa.getCurrentTileIndex() == 2) {
							tilednyawa.setCurrentTileIndex(1);
							db.updateNyawa(idjiwa, tilednyawa.getCurrentTileIndex());
							db.closeDB();
							showkepastian("salah");
						}else if (tilednyawa.getCurrentTileIndex() == 1) {
							tilednyawa.setCurrentTileIndex(0);
							showkepastian("gameover");
							db.resetLevel(kateg.getKate(), kateg.getLepel());
							db.updateNyawa(idjiwa, 3);
							music.stop();
						}
						
					}
				}else if (spriteback.contains(pSceneTouchEvent.getX(), pSceneTouchEvent.getY())) {
					close(kateg.getKate());
				}
			}
		return true;
	}

	
	
	private void close(String kategori){
		if (kateg.getKate().equals("pop")) {
    		HideEdittext();
    		db.closeDB();
    		SceneManager.getInstance().loadPopScene(engine);
		}else if (kateg.getKate().equals("rock")) {
			HideEdittext();
			db.closeDB();
			SceneManager.getInstance().loadRockScene(engine);
		}else if (kateg.getKate().equals("dangdut")) {
			HideEdittext();
			db.closeDB();
			SceneManager.getInstance().loadDANGDUTScene(engine);
		}else if (kateg.getKate().equals("daerah")) {
			HideEdittext();
			db.closeDB();
			SceneManager.getInstance().loadDAERAHScene(engine);
		}
	}
	private void ShowEdittext(){
		activity.runOnUiThread(new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				activity.addContentView(resourcesManager.editText(), new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				resourcesManager.editText.setTypeface(Typeface.createFromAsset(activity.getAssets(), "font/Bubblegum.ttf"));
				resourcesManager.editText.setTextColor(android.graphics.Color.BLACK);
				resourcesManager.tableLayout.setPadding(10, CHspriteok, 150, 0);
				resourcesManager.tableLayout.setBackgroundColor(Color.ABGR_PACKED_BLUE_SHIFT);
				resourcesManager.editText.setAlpha(0);
			}
		});
	}
	private void LihatEdittext(){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resourcesManager.editText.setAlpha(10);
			}
		});
	}
	private void HideEdittext(){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resourcesManager.editText.setVisibility(View.GONE);
				resourcesManager.tableLayout.setVisibility(View.GONE);
			}
		});
		
	}
	private void showkepastian(final String toas){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				LayoutInflater inflater = activity.getLayoutInflater();
				View layout = inflater.inflate(R.layout.toast,(ViewGroup) activity.findViewById(R.id.toast_layout_root));
				ImageView image = (ImageView) layout.findViewById(R.id.image);
				if (toas.equals("nyaris")) {
					image.setImageResource(R.drawable.nyaris);
				}else if (toas.equals("salah")) {
					image.setImageResource(R.drawable.salah);
				}else{
					image.setImageResource(R.drawable.gameover);
					close(kateg.getKate());
				}
				
				Toast toast = new Toast(activity.getApplicationContext());
				toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
				toast.setDuration(Toast.LENGTH_SHORT);
				toast.setView(layout);
				toast.show();
				InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(resourcesManager.editText.getWindowToken(), 0);
			}
		});
	}
	private void showturunkeyboard(){
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				resourcesManager.editText.setVisibility(View.GONE);
				InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(resourcesManager.editText.getWindowToken(), 0);
			}
		});
	}
}
