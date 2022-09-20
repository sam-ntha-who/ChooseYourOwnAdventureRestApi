package co.grandcircus.FinalProject.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("stories")
public class Story {

	@Id
	private String id;
	private String title;
	private String startingSceneId;
	private String photoUrl;

	// getters and setters
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartingSceneId() {
		return startingSceneId;
	}

	public void setStartingSceneId(String startingSceneId) {
		this.startingSceneId = startingSceneId;
	}

}
