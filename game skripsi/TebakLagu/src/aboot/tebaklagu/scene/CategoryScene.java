package aboot.tebaklagu.scene;

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

public class CategoryScene extends BaseScene implements IOnMenuItemClickListener{

	
	private MenuScene menuChildScene;
	private final int font_pop = 0;
	private final int font_rock= 1;
	private final int font_dangdut = 2;
	private final int font_daerah = 3;
	private IMenuItem pop, rock,dangdut,daerah;
	private Sprite cate;
	private int CW,CH,CWhead, CWpop, CHpop, CHrock, CHdangdut, CHdaerah;
	private Rectangle header;
	@Override
	public void createScene() {
		createCateFontChildScene();
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
		SceneManager.getInstance().createMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_CATE;
	}

	@Override
	public void disposeScene() {}

	private void createCateFontChildScene(){
		CW = (int) camera.getWidth();
		CH = (int) camera.getHeight();
		
		menuChildScene = new MenuScene(camera);
		
		header = new Rectangle(0, 0, CW, 70, vbom);
		cate = new Sprite(0, 0, resourcesManager.regioncate, vbom);
		pop = new ScaleMenuItemDecorator(new SpriteMenuItem(font_pop, resourcesManager.regionpop, vbom), 1.1f, 1);
		rock = new ScaleMenuItemDecorator(new SpriteMenuItem(font_rock, resourcesManager.regionrock, vbom), 1.1f, 1);
		dangdut = new ScaleMenuItemDecorator(new SpriteMenuItem(font_dangdut, resourcesManager.regiondangdut, vbom), 1.1f, 1);
		daerah = new ScaleMenuItemDecorator(new SpriteMenuItem(font_daerah, resourcesManager.regiondaerah, vbom), 1.1f, 1);
		
		menuChildScene.setBackground(new Background(52/255f, 73/255f, 94/255f));
		header.setColor(44/255f, 62/255f, 80/255f);
		
		setChildScene(menuChildScene);
		menuChildScene.attachChild(header);
		header.attachChild(cate);
		menuChildScene.addMenuItem(pop);
		menuChildScene.addMenuItem(rock);
		menuChildScene.addMenuItem(dangdut);
		menuChildScene.addMenuItem(daerah);

		if (CW == 480) {
			
		}else if (CW == 720) {
			pop.setSize(550, 206);
			rock.setSize(550, 206);
			dangdut.setSize(550, 206);
			daerah.setSize(550, 206);
		}
		
		CWhead = (int) ((CW - cate.getWidth()) / 2);
		CWpop = (int) ((CW - pop.getWidth()) / 2);
		CHpop = (int) ((CH - pop.getHeight()) / 6 );
		CHrock = (int) ((CH - rock.getHeight()) / 6 + pop.getHeight());
		CHdangdut = (int) ((CH - dangdut.getHeight()) / 6 + (pop.getHeight() * 2));
		CHdaerah = (int) ((CH - daerah.getHeight()) / 6 + (pop.getHeight() * 3));
		
		menuChildScene.setPosition(0, 0);
		cate.setPosition(CWhead, 0);
		pop.setPosition(CWpop, CHpop);
		rock.setPosition(CWpop, CHrock);
		dangdut.setPosition(CWpop, CHdangdut);
		daerah.setPosition(CWpop, CHdaerah);
		
		menuChildScene.setOnMenuItemClickListener(this);
		
	}
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case font_pop:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createPOPScene(engine);
			return true;
		case font_rock:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createROCKScene(engine);
			return true;
		case font_dangdut:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createDANGDUTScene(engine);
			return true;
		case font_daerah:
			resourcesManager.click.play();
			resourcesManager.click.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}
			});
			SceneManager.getInstance().createDAERAHScene(engine);
			return true;
		default:
			break;
		}
		return false;
	}
}
