package aboot.tebaklagu.scene;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import aboot.tebaklagu.base.BaseScene;
import aboot.tebaklagu.manager.SceneManager.SceneType;

public class Loading extends BaseScene{

	private int CW, CH;
	private Text load;
	@Override
	public void createScene() {
		setBackground(new Background(52/255f, 152/255f, 219/255f));
		SikatSemuaScene();
	}

	@Override
	public void onBackKeyPressed() {}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_LOAD;
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
	}

	private void create() {
		load = new Text(0, 0, resourcesManager.Bubblegum, "LOADING ....", vbom);
		
	}

	private void attach() {
		attachChild(load);
		
	}

	private void position() {
		load.setPosition((CW - load.getWidth()) / 2, (CH - load.getHeight()) / 2);
		
	}

	private void custom() {
		load.setColor(1,1,1);
	}
}
