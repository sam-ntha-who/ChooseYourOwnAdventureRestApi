package co.grandcircus.FinalProject.Controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.FinalProject.Models.Photo;
import co.grandcircus.FinalProject.Models.Scene;
import co.grandcircus.FinalProject.Models.Story;
import co.grandcircus.FinalProject.Repositories.SceneRepository;
import co.grandcircus.FinalProject.Repositories.StoryRepository;
import co.grandcircus.FinalProject.Services.AdventureDBService;
import co.grandcircus.FinalProject.Services.PexelService;

@Controller
public class ViewsController {

	@Autowired
	StoryRepository storyRepo;

	@Autowired
	SceneRepository sceneRepo;

	@Autowired
	PexelService service;

	@Autowired
	AdventureDBService dbService;

	@RequestMapping("/")
	public String index(Model model) {
		Story[] list = dbService.getAllStories();
		List<Story> storyList = Arrays.asList(list);
		model.addAttribute("storyList", storyList);
		
		List<Photo> thumbnailList1 = service.getPexels("Hike");
		List<Photo> thumbnailList2 = service.getPexels("Story");
		List<Photo> thumbnailList3 = service.getPexels("Question");
		
		Photo thumbnail1 = thumbnailList1.get(0);
		Photo thumbnail2 = thumbnailList2.get(0);
		
		List<String> photoList = new ArrayList<>();
		photoList.add(thumbnail1.getSrc().getOriginal());
		photoList.add(thumbnail2.getSrc().getOriginal());
		model.addAttribute("photoList", photoList);
		return "index";
	}

	@RequestMapping("/play")
	public String play(Model model, @RequestParam String id) {
		Scene nextScene = dbService.getScene(id);
		model.addAttribute("scene", nextScene);
		return "StoryPlay";
	}
	
	
	

	@RequestMapping("/edit")
	public String storyEdit(Model model, @RequestParam String sceneId) {
		Scene editScene = dbService.getScene(sceneId);
		Story story = storyRepo.findById(editScene.getStoryId()).orElseThrow(() -> new SceneNotFoundException(sceneId));
		model.addAttribute("storyTitle", story.getTitle());
		model.addAttribute("scene", editScene);
		return "StoryEdit";
	}
	
//	// call directly
//	@PostMapping("/update")
//	public String sceneSave(@RequestBody Scene scene) {
//		dbService.
//		
//	}
	
	
	// call directly
	@DeleteMapping("/delete/{id}")
	public String sceneDelete(@PathVariable String id) {
		dbService.deleteStory(id);
		return "StoryDeleted";
	}

//	@RequestMapping("/addScene")
//	public String addScene(@PathVariable(required=false) ) {
//		return "AddScene";
//	}

	@RequestMapping("/test-pexel/{sceneId}")
	public String randomName(Model model, @PathVariable("sceneId") String sceneId)
			throws URISyntaxException, IOException, InterruptedException {
		List<Photo> response = service.getPexels("Tiger");
		Scene test = dbService.getScene(sceneId);
		String title = dbService.getStoryName(sceneId);
		model.addAttribute("storyName", title);
		model.addAttribute("scene", test);
		model.addAttribute("response", response);
		return "testing";
	}

//	@RequestMapping("/test-Story-Name/{storyId}")
//	public String randomerName(Model model, @PathVariable("storyId") String storyId) {
//		
//	}

}
