package co.grandcircus.FinalProject;

import java.util.List;

import org.springframework.stereotype.Component;

import co.grandcircus.FinalProject.Models.Scene;

@Component
public class PathLengthCalculator {
	public void setPathLength(Scene scene) {
		System.out.println("******** STARTING PATH LENGTH *********"); //TESTING DELETE ME

		if (scene.getChildList() == null) {
			System.out.println("******** ChildList NULL *********");  //TESTING DELETE ME
			return;
		}

		for (Scene s : scene.getChildList()) {
			System.out.println("******** S in ChildList.... *********");

			int pathLength = getScenePathLength(s);
			
			s.setPathLength(pathLength);
		}


	}

	// could the path functionality move out of the controller while keeping the
	// tree function?
	private int getScenePathLength(Scene scene) {

		int pathLength = 0;
//		// not needed if tree passed in
//		List<Scene> childList = scene.getChildList();
//		scene.setChildList(childList);

		if (scene.getChildList() == null) {

			return pathLength;
		}

		for (Scene s : scene.getChildList()) {

			pathLength = Math.max(pathLength, getScenePathLength(s));
		}

		return pathLength + 1;
	}
	// path boolean method

	public Scene setPathBool(Scene scene) {
		List<Scene> childList = scene.getChildList();
		for (Scene s : childList) {
			s.setLongest(false);
			s.setShortest(false);
		}
		Scene shortest = childList.get(0);
		Scene longest = childList.get(0);
		for (Scene s : childList) {
			if (shortest.getPathLength() > s.getPathLength()) {
				shortest = s;
			}
			if (longest.getPathLength() < s.getPathLength()) {
				longest = s;
			}
		}
		for (Scene s : childList) {
			if (shortest.getPathLength() == s.getPathLength()) {
				s.setShortest(true);
			}
			if (longest.getPathLength() == s.getPathLength()) {
				s.setLongest(true);
			}
		}
		
		return scene;
	}
}
