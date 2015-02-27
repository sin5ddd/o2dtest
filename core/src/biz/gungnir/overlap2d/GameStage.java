package biz.gungnir.overlap2d;

import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.IBaseItem;

/**
 * Created by kimumoku on 2015/02/27.
 */
public class GameStage extends Overlap2DStage{
	private PlayerController player;
	public GameStage(){
		initSceneLoader();
		for(IBaseItem item: sceneLoader.getRoot().getItems()){
			if(item.getCustomVariables().getFloatVariable("platformSpeed") != null && item.isComposite()){
				((CompositeItem) item).addScript(new MovingPlatform());
			}
		}

		sceneLoader.loadScene("MainScene");
		addActor(sceneLoader.getRoot());

		PlayerController player = new PlayerController(this);
		sceneLoader.getRoot().getCompositeById("player").addScript(player);
	}
	public void act(float delta){
		super.act(delta);
	}
	public void restart(){
		player.reset();
	}
}
