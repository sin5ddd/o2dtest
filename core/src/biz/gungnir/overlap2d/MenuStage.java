package biz.gungnir.overlap2d;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

/**
 * Created by kimumoku on 2015/02/27.
 * UI scene overray
 */
public class MenuStage extends Stage {
	public MenuStage(O2DTestResourceManager rm, final GameStage gameStage) {
		super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));
		SceneLoader sl = new SceneLoader(rm);
		sl.setResolution(rm.currentResolution.name);
		sl.loadScene("UIScene");

		addActor(sl.getRoot());
		SimpleButtonScript restartButton = SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("restartBtn"));
		restartButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				gameStage.restart();
			}
		});
	}
}