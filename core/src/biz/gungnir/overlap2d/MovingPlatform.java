package biz.gungnir.overlap2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by kimumoku on 2015/02/27.
 */
public class MovingPlatform implements IScript {
	private CompositeItem item;
	private float originalPosY;
	private int direction = 1;
	private float moveSpeed = 0f, margin = 0f;

	@Override
	public void init(CompositeItem item){
		this.item = item;
		originalPosY = item.getY();

		if(item.getCustomVariables().getFloatVariable("platformSpeed") != null) moveSpeed = item.getCustomVariables().getFloatVariable("platformSpeed");
		if(item.getCustomVariables().getFloatVariable("platformMargin") != null) margin = item.getCustomVariables().getFloatVariable("platformMargin");
		Gdx.app.log("platform moveSpeed", Float.toString(moveSpeed));
		Gdx.app.log("platform margin", Float.toString(margin));
		Gdx.app.log("item position", Float.toString(item.getBody().getPosition().y));

	}

	@Override
	public void dispose(){}

	@Override
	public void act(float delta){
		setY(getY() + direction * delta * moveSpeed);
		if(getY() > originalPosY + margin || getY() < originalPosY - margin) direction *= -1;
		item.setY(getY());
	}

	public float getY(){
		Vector2 curPos = item.getBody().getPosition();
		return curPos.y;
	}
	public void setY(float y){
		Vector2 currPos = item.getBody().getPosition();
		item.getBody().setTransform(currPos.x, y * PhysicsBodyLoader.SCALE, item.getBody().getAngle());
	}
}
