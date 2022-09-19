package co.grandcircus.FinalProject;

import java.util.List;

import co.grandcircus.FinalProject.Models.Scene;

public class PathLengthCalculator {

	public Scene setPathLength(Scene scene) {

		if (scene.getChildList() == null) {
			return scene;
		}
	
		for(Scene s : scene.getChildList()) {
				
			int pathLength = getScenePathLength(s);
			s.setPathLength(pathLength);

			
			setPathBool(scene.getChildList());

		}
		
		return scene;
	}
	// could the path functionality move out of the controller while keeping the tree function?
	private int getScenePathLength(Scene scene) {

		int pathLength = 0;
		// this is referencing the getScene within the DB Service
		List<Scene> childList = scene.getChildList();
		scene.setChildList(childList);
		
		if (scene.getChildList() == null) {

			return pathLength;
		}

		for (Scene s : scene.getChildList()) {

			pathLength = Math.max(pathLength, getScenePathLength(s));
		}

		return pathLength + 1;
	}
	//path boolean method
	
	private static void setPathBool(List<Scene> childList) {
		for(Scene s : childList) {
			s.setLongest(false);
			s.setShortest(false);
		}
		Scene shortest = childList.get(0);
		Scene longest = childList.get(0);
		for(Scene s : childList) {
			if(shortest.getPathLength() > s.getPathLength()) {
				shortest = s;
			}
			if(longest.getPathLength() < s.getPathLength()) {
				longest = s;
			}
		}
		for(Scene s : childList) {
			if(shortest.getPathLength() == s.getPathLength()) {
				s.setShortest(true);
			}
			if(longest.getPathLength() == s.getPathLength()) {
				s.setLongest(true);
			}
		}
}
