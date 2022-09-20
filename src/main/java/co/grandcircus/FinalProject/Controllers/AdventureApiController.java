package co.grandcircus.FinalProject.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.FinalProject.PathLengthCalculator;
import co.grandcircus.FinalProject.ExceptionHandling.SceneNotFoundException;
import co.grandcircus.FinalProject.ExceptionHandling.StoryNotFoundException;
import co.grandcircus.FinalProject.Models.Scene;
import co.grandcircus.FinalProject.Models.Story;
import co.grandcircus.FinalProject.Repositories.SceneRepository;
import co.grandcircus.FinalProject.Repositories.StoryRepository;

@RestController
public class AdventureApiController {

	@GetMapping("")
	public String root() {
		return "ok";
	}

	
	@Autowired
	private StoryRepository storyRepo;

	@Autowired
	private SceneRepository sceneRepo;

	@Autowired
	PathLengthCalculator pathCalc;

	// CRUD Functions

	// Create Story
	@PostMapping("/create-story")
	@ResponseStatus(HttpStatus.CREATED)
	public Story createStory(@RequestBody Story story) {
		storyRepo.insert(story);
		return story;
	}

	// Create Scene
	@PostMapping("/create-scene")
	@ResponseStatus(HttpStatus.CREATED)
	public Scene createScene(@RequestBody Scene scene) {
		sceneRepo.insert(scene);
		return scene;
	}

	// Get list of stories
	@GetMapping("/allStories")
	public List<Story> getStories() {
		return storyRepo.findAll();
	}

	// get a single story
	@GetMapping("story/{storyId}")
	public Story getStory(@PathVariable String storyId) {
		return storyRepo.findById(storyId).orElseThrow(() -> new StoryNotFoundException(storyId));
	}

	// get a scene
	@GetMapping("/read-scene/{id}")
	public Scene getScene(@PathVariable("id") String id) {
		// calls database for specific scene and saves for later as response
		Scene response = sceneRepo.findById(id).orElseThrow(() -> new SceneNotFoundException(id));

		// calls database for specific scene or throws exception if not found
		Scene scene = sceneRepo.findById(id).orElseThrow(() -> new SceneNotFoundException(id));

		// builds tree from scene
		Scene sceneTree = buildTree(scene);

		// checks if childList is empty, if it is, this scene is an end scene
		if (sceneTree.getChildList() == null || sceneTree.getChildList().size() == 0) {

			return scene;
		}

		pathCalc.setPathLength(sceneTree);

		// new list created to hold Child Scenes for the response (this is so an entire
		// tree isn't returned in the response)
		List<Scene> responseChildList = new ArrayList<>();

		int childListSize = sceneTree.getChildList().size();

		// creates a new scene, copying the necessary data from the children in the tree
		// (which have pathLength calculated)
		// adds the completed copied scene to an ArrayList
		for (int i = 0; i < childListSize; i++) {
			Scene masterChild = sceneTree.getChildList().get(i);
			Scene responseChild = new Scene();
			
			responseChild.setId(masterChild.getId());
			responseChild.setOption(masterChild.getOption());
			responseChild.setPathLength(masterChild.getPathLength());

			responseChildList.add(responseChild);
		}

		// adds newly created childList to response
		response.setChildList(responseChildList);

		// sets booleans on ChildList
		response = pathCalc.setPathBool(response);

		return response;
	}

	// Read a Story Name
	public String findStoryName(@RequestParam String id) {

		Story story = storyRepo.findById(id).orElseThrow(() -> new SceneNotFoundException(id));

		return story.getTitle();
	}

	// Update a scene
	@PostMapping("/update-scene")
	public Scene updateScene(@RequestBody Scene scene, @RequestParam String id) {

		Scene sceneToUpdate = sceneRepo.findByStoryIdAndId(scene.getStoryId(), id)
				.orElseThrow(() -> new SceneNotFoundException(scene.getId()));

		sceneToUpdate.setDescription(scene.getDescription());
		sceneToUpdate.setChildList(scene.getChildList());

		return sceneRepo.save(sceneToUpdate);
	}

	@PostMapping("/save-scene")
	public Scene saveScene(@RequestBody Scene scene) {
		return sceneRepo.save(scene);

	}

	@PostMapping("/save-story")
	public Story saveStory(@RequestBody Story story) {
		return storyRepo.save(story);

	}

	// Delete Scene (and all connected scenes)
	@DeleteMapping("/delete-scene-tree/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSceneTree(@PathVariable String id) {

		ArrayList<Scene> scenesToDelete = new ArrayList<>();

		Scene sceneToDelete = sceneRepo.findById(id).orElseThrow(() -> new SceneNotFoundException(id));

		scenesToDelete.add(sceneToDelete);

		for (int i = 0; i < scenesToDelete.size(); i++) {
			String sceneId = scenesToDelete.get(i).getId();
			List<Scene> subList = sceneRepo.findByParentId(sceneId);

			if (subList.size() > 0) {

				for (Scene sc : subList) {
					scenesToDelete.add(sc);
				}
			}
		}

		for (Scene s2d : scenesToDelete) {
			sceneRepo.delete(s2d);
		}
	}

	@DeleteMapping("/delete-story/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStory(@PathVariable String id) {

		Story storyToDelete = storyRepo.findById(id).orElseThrow(() -> new StoryNotFoundException(id));

		deleteSceneTree(storyToDelete.getStartingSceneId());

		storyRepo.delete(storyToDelete);
	}

	public Scene buildTree(Scene scene) {

		List<Scene> childList = sceneRepo.findByParentId(scene.getId());
		scene.setChildList(childList);
		if (childList != null) {

			for (Scene s : childList) {
				buildTree(s);
			}
		}
		return scene;
	}

	// Error Handling
	@ResponseBody
	@ExceptionHandler(SceneNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String sceneNotFoundHandler(SceneNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(StoryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String storyNotFoundHandler(StoryNotFoundException ex) {
		return ex.getMessage();
	}

}
