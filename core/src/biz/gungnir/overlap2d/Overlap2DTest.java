package biz.gungnir.overlap2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class Overlap2DTest extends ApplicationAdapter {
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;
	private GameStage gameStage;
	private MenuStage menuStage;


	@Override
	public void create () {
		InputMultiplexer inputMultiplexer;
		O2DTestResourceManager rm;

		rm = new O2DTestResourceManager();
		rm.initO2DResources();

		inputMultiplexer = new InputMultiplexer();
		gameStage = new GameStage(rm);
		menuStage = new MenuStage(rm, gameStage);
		inputMultiplexer.addProcessor(gameStage);
		inputMultiplexer.addProcessor(menuStage);

		Gdx.input.setInputProcessor(inputMultiplexer);

		debugRenderer = new Box2DDebugRenderer();
		debugMatrix = new Matrix4(gameStage.getCamera().combined);
		debugMatrix.scale(10f, 10f, 1f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStage.act();
		gameStage.draw();

		menuStage.act();
		menuStage.draw();
		debugRenderer.render(gameStage.getWorld(), debugMatrix);
	}
}
