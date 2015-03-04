package biz.gungnir.overlap2d;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

/**
 * Created by kimumoku on 2015/02/27.
 * UI scene overray
 */
public class MenuStage extends Stage {
	CompositeItem gameover;
	public MenuStage(O2DTestResourceManager rm, final GameStage gameStage) {
		super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));
		SceneLoader sl = new SceneLoader(rm);
		sl.setResolution(rm.currentResolution.name);
		sl.loadScene("UIScene");
		gameover = sl.getRoot().getCompositeById("gameoverLogo");
		gameover.addAction(alpha(0));

		addActor(sl.getRoot());
		SimpleButtonScript restartButton = SimpleButtonScript.selfInit(sl.getRoot().getCompositeById("restartBtn"));
		restartButton.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				gameStage.restart();
				restart();
			}
		});
	}
	public void gameover(){
		gameover.addAction(fadeIn(0.2f));
	}

	public void restart(){
		gameover.addAction(alpha(0));
	}
}