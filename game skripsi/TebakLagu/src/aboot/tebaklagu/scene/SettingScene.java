package aboot.tebaklagu.scene;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;

import aboot.tebaklagu.R;
import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.helper.LaguAdapter;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.LayoutInflater;

public class SettingScene extends BaseScene implements IOnMenuItemClickListener{

	private LaguAdapter db;
	private MenuScene menuChildScene;
	private final int no1= 1;
	private final int no2= 2;
	private final int no3 = 3;
	private final int no4 = 4;
	private IMenuItem pop, rock, dangdut, daerah;
	private int CW, CH, CWhead, CWpop, CHpop, CHrock, CHdangdut, CHdaerah;
	private Rectangle header;
	private Sprite setting;
	@Override
	public void createScene() {
		SIkatSemuaScene();
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
		SceneManager.getInstance().loadMenusScene(engine);
		db.closeDB();
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_DANGDUT;
	}

	@Override
	public void disposeScene() {}

	private void SIkatSemuaScene(){
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
		setting = new Sprite(0, 0, resourcesManager.regionheadsetting, vbom);
		pop = new ScaleMenuItemDecorator(new SpriteMenuItem(no1, resourcesManager.regionresetpop, vbom), 1.1f, 1);
		rock = new ScaleMenuItemDecorator(new SpriteMenuItem(no2, resourcesManager.regionresetrock, vbom), 1.1f, 1);
		dangdut = new ScaleMenuItemDecorator(new SpriteMenuItem(no3, resourcesManager.regionresetdangdut, vbom), 1.1f, 1);
		daerah = new ScaleMenuItemDecorator(new SpriteMenuItem(no4, resourcesManager.regionresetdaerah, vbom), 1.1f, 1);		
	}
	private void position(){
		CWhead = (int) ((CW - setting.getWidth()) / 2);
		CWpop = (int) ((CW - pop.getWidth()) / 2);
		CHpop = (int) ((CH - pop.getHeight()) / 4);
		CHrock = (int) ((CH - rock.getHeight()) / 4 + pop.getHeight());
		CHdangdut = (int) ((CH - dangdut.getHeight()) / 4 + (pop.getHeight() * 2));
		CHdaerah = (int) ((CH - daerah.getHeight()) / 4 + (pop.getHeight() * 3));
		
		setting.setPosition(CWhead, 0);
		menuChildScene.setPosition(0, 0);
		dangdut.setPosition(CWhead, 0);
		pop.setPosition(CWpop, CHpop);
		rock.setPosition(CWpop, CHrock);
		dangdut.setPosition(CWpop, CHdangdut);
		daerah.setPosition(CWpop, CHdaerah);
	}
	private void custom(){
		menuChildScene.setBackground(new Background(52/255f, 73/255f, 94/255f));
		header.setColor(44/255f, 62/255f, 80/255f);
		Log.e("ukuran layar", "CW: "+CW);
		if (CW == 480) {
		}else if (CW == 720) {
			pop.setSize(550, 96);
			rock.setSize(550, 96);
			dangdut.setSize(550, 96);
			daerah.setSize(550, 96);
		}
	}
	private void attach(){
		setChildScene(menuChildScene);
		menuChildScene.attachChild(header);
		header.attachChild(setting);
		menuChildScene.addMenuItem(pop);
		menuChildScene.addMenuItem(rock);
		menuChildScene.addMenuItem(dangdut);
		menuChildScene.addMenuItem(daerah);
	}


	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
			switch (pMenuItem.getID()) {
			case no1:
				resourcesManager.click.play();
				alert("pop");
				return true;
			case no2:
				resourcesManager.click.play();
				alert("rock");
				return true;
			case no3:
				resourcesManager.click.play();
				alert("dangdut");
				return true;
			case no4:
				resourcesManager.click.play();
				alert("daerah");
				return true;
			default:
				break;
			}
		return false;
	}
	private void alert(final String kateks){
		activity.runOnUiThread(new Runnable() {
		     @Override
		     public void run() {
		    	 AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		         alert.setMessage("reset "+kateks);
		         alert.setPositiveButton("OK", new OnClickListener() {
		                 @Override
		                 public void onClick(DialogInterface arg0, int arg1) {
		                	 
		                	 db.ResetKate(kateks);
		                 }});
		         alert.setNegativeButton("cancel", null);
		         alert.show();
		     }
		    });
	}
}
