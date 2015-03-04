package biz.gungnir.overlap2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.SpriteAnimation;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.script.IScript;

/**
 * Created by sin5d-kimumoku on 2015/02/27.
 * script will be attached on overlap2d tagged as "player"
 */
public class PlayerController implements IScript{
	private Overlap2DStage stage;
	private CompositeItem item;
	private SpriteAnimation animation;

	private int jumpCounter = 0;

	private boolean isWalking = false;
	private boolean isGrounded = false;
	static final float moveSpeed = 200f;
	static final float gravity = -800f;
	private float verticalSpeed = 0;

	private Vector2 initialCoordinates;

	public PlayerController(Overlap2DStage o2DStage){ stage = o2DStage;}
	@Override
	public void init(CompositeItem item){
		this.item = item;
		animation = item.getSpriteAnimationById("animation");
		animation.pause();
		initialCoordinates = new Vector2(item.getX(), item.getY());

		// Setting item origin at the center
		item.setOrigin(item.getWidth()/2,0);
	}
	@Override
	public void dispose(){}
	@Override
	public void act(float delta){
		boolean wasWalking = isWalking;
		isWalking = false;
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			item.setX(item.getX() + delta*moveSpeed);
			item.setScaleX(1f);
			isWalking = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			item.setX(item.getX() - delta*moveSpeed);
			item.setScaleX(-1f);
			isWalking = true;
		}

		if(wasWalking && !isWalking) animation.pause();
		if(!wasWalking && isWalking) animation.start();
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			// Jump
			if(isGrounded || jumpCounter < 2){
				verticalSpeed = 460f;
				isGrounded = false;
				jumpCounter++;
			}
		}

		verticalSpeed += gravity * delta;
		checkForCollisions();

		item.setY(item.getY() + verticalSpeed * delta);
	}

	private void checkForCollisions(){
		float rayGap = item.getHeight() / 2;
		float raySize = -(verticalSpeed + Gdx.graphics.getDeltaTime())*Gdx.graphics.getDeltaTime();
		if(raySize < 5f) raySize = 5f;
		if(verticalSpeed > 0) return;

		Vector2 rayFrom = new Vector2((item.getX() + item.getWidth() / 2) * PhysicsBodyLoader.SCALE, (item.getY() + rayGap) * PhysicsBodyLoader.SCALE);
		Vector2 rayTo = new Vector2((item.getX() + item.getWidth() / 2) * PhysicsBodyLoader.SCALE, (item.getY() - raySize) * PhysicsBodyLoader.SCALE);

//		Gdx.app.log("stage", stage.toString());
		RayCastCallback r = new RayCastCallback() {
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				verticalSpeed = 0;
				item.setY(point.y / PhysicsBodyLoader.SCALE + 0.1f);
				isGrounded = true;
				jumpCounter = 0;

				return 0;
			}
		};
		stage.getWorld().rayCast(r, rayFrom, rayTo);
//		Gdx.app.log("reportRayFixture",r.toString());
	}
	public CompositeItem getActor(){
		return item;
	}

	public void reset(){
		item.setX(initialCoordinates.x);
		item.setY(initialCoordinates.y);
		verticalSpeed = 0;
	}
}
