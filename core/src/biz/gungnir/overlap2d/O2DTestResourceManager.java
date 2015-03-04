package biz.gungnir.overlap2d;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.ResolutionEntryVO;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * Created by sin5d on 2015/02/27.
 * Resource manager which used for any game scene classes
 */
public class O2DTestResourceManager extends ResourceManager {

	public ResolutionEntryVO currentResolution;
	public float stageWidth;

	public void initO2DResources(){
		loadProjectVO();
		currentResolution = getBestResolutionMatch(getProjectVO());

		stageWidth = Gdx.graphics.getHeight() / currentResolution.height * Gdx.graphics.getWidth();
		setWorkingResolution(currentResolution.name);
		Gdx.app.log("current resolution", currentResolution.name);

		initAllResources();
	}

	public ResolutionEntryVO getBestResolutionMatch(ProjectInfoVO projectInfoVO){
		float deltaSize = Math.abs(projectInfoVO.originalResolution.height - Gdx.graphics.getHeight());
		ResolutionEntryVO result = projectInfoVO.originalResolution;

		for(int i = 0; i < projectInfoVO.resolutions.size(); i++){
			float newDeltaSize = Math.abs(projectInfoVO.resolutions.get(i).height - Gdx.graphics.getHeight());
			System.out.println(newDeltaSize);
			if(deltaSize > newDeltaSize){
				deltaSize = newDeltaSize;
				result = projectInfoVO.resolutions.get(i);
			}
		}
		return result;
	}
}
