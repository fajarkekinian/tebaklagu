package aboot.tebaklagu.scene;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.manager.SceneManager;
import aboot.tebaklagu.manager.SceneManager.SceneType;

public class CreditScene extends BaseScene{

	private int CW, CH, CWhead, CWcredit, CHcredit;
	private Rectangle header;
	private Sprite credithead, credit;
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
		credithead = new Sprite(0, 0, resourcesManager.regionCredithead, vbom);
		credit = new Sprite(0, 0, resourcesManager.regionCredit, vbom);
	}

	private void position() {
		CWhead = (int) ((CW - credithead.getWidth()) /2);
		CWcredit = (int) ((CW - credit.getWidth()) / 2);
		CHcredit = (int) ((CH - credit.getHeight()) / 2);
		credithead.setPosition(CWhead, 0);
		credit.setPosition(CWcredit, CHcredit);
	}
	
	private void custom() {
		setBackground(new Background(52/255f, 73/255f, 94/255f));
		header.setColor(44/255f, 62/255f, 80/255f);
		if (CW == 480) {
			credit.setScale(1f);
		}else if (CW == 720) {
			credit.setScale(1.5f);
		}
	}

	private void attach() {
		attachChild(header);
		header.attachChild(credithead);
		attachChild(credit);
	}

	
	
}
