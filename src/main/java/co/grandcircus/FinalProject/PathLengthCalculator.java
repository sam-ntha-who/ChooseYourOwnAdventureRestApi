package co.grandcircus.FinalProject;

import java.util.List;

import org.springframework.stereotype.Component;

import co.grandcircus.FinalProject.Models.Scene;

@Component
public class PathLengthCalculator {
	public void setPathLength(Scene scene) {

		if (scene.getChildList() == null) {

			return;
		}

		for (Scene s : scene.getChildList()) {

			int pathLength = getScenePathLength(s);

			s.setPathLength(pathLength);
		}

	}

	private int getScenePathLength(Scene scene) {

		int pathLength = 0;

		if (scene.getChildList() == null) {

			return pathLength;
		}

		for (Scene s : scene.getChildList()) {

			pathLength = Math.max(pathLength, getScenePathLength(s));
		}

		return pathLength + 1;
	}

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
