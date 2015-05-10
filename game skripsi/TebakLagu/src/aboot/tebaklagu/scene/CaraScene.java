package aboot.tebaklagu.scene;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;

public class CaraScene extends BaseScene{

	private int CW, CH, CWhead, CWcara, CHcara;
	private Rectangle header;
	private Sprite carahead, cara;
	@Override
	public void createScene() {
		SIkatSemuaScene();
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_CARA;
	}
	@Override
	public void disposeScene() {}
	private void SIkatSemuaScene(){
		CW = (int) camera.getWidth();
		CH = (int) camera.getHeight();
		create();
		custom();
		attach();
		position();
		
		
	}

	private void create() {
		header = new Rectangle(0, 0, CW, 70, vbom);
		carahead = new Sprite(0, 0, resourcesManager.regionCarahead, vbom);
		cara = new Sprite(0, 0, resourcesManager.regionCara, vbom);
	}

	private void position() {
		CWhead = (int) ((CW - carahead.getWidth()) /2);
		CWcara = (int) ((CW - cara.getWidth()) / 2);
		CHcara = (int) ((CH - cara.getHeight()) / 2);
		carahead.setPosition(CWhead, 0);
		cara.setPosition(CWcara, CHcara);
	}
	
	private void custom() {
		setBackground(new Background(52/255f, 73/255f, 94/255f));
		header.setColor(44/255f, 62/255f, 80/255f);
		if (CW == 480) {
			cara.setScale(1f);
		}else if (CW == 720) {
			cara.setScale(1.5f);
		}
	}

	private void attach() {
		attachChild(header);
		header.attachChild(carahead);
		attachChild(cara);
	}

	
	
}
