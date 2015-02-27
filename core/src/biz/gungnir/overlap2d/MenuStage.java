package biz.gungnir.overlap2d;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

/**
 * Created by kimumoku on 2015/02/27.
 */
public class MenuStage extends Stage {
	public MenuStage(final GameStage gameStage) {
		SceneLoader sl = new SceneLoader(gameStage.essentials.rm);
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