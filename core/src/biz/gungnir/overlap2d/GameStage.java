package biz.gungnir.overlap2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.IBaseItem;

/**
 * Created by kimumoku on 2015/02/27.
 * main game scene
 */
public class GameStage extends Overlap2DStage{
	private PlayerController player;

	public GameStage(O2DTestResourceManager rm){
		super(new StretchViewport(rm.currentResolution.width, rm.currentResolution.height));
		player = new PlayerController(this);
		essentials.world = new World(new Vector2(0, -98f), true);
		setDebugInvisible(true);
		initSceneLoader(rm);
		sceneLoader.setResolution(rm.currentResolution.name);
		sceneLoader.loadScene("MainScene");
		sceneLoader.getRoot().getCompositeById("player").addScript(player);
		addActor(sceneLoader.getRoot());

		for(IBaseItem item: sceneLoader.getRoot().getItems()){
			if(item.getCustomVariables().getFloatVariable("platformSpeed") != null && item.isComposite()){
				((CompositeItem) item).addScript(new MovingPlatform());
			}
		}


	}
	public void act(float delta){
		super.act(delta);
//		((OrthographicCamera)getCamera()).position.x = player.getActor().getX();
	}
	public void restart(){
		player.reset();
	}
}
