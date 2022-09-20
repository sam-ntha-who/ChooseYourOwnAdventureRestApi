package co.grandcircus.FinalProject.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("scenesMASTER")
public class Scene{

	@Id
	private String id;
	private String storyId;
	private String storyTitle;
	private String parentId;
	private String description;
	private String option;
	private String photoUrl;
	private List<Scene> childList;
	private int pathLength;
	boolean shortest;
	boolean longest;

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoryId() {
		return storyId;
	}

	public void setStoryId(String storyId) {
		this.storyId = storyId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public List<Scene> getChildList() {
		return childList;
	}

	public void setChildList(List<Scene> childList) {
		this.childList = childList;
	}

	public int getPathLength() {
		return pathLength;
	}

	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}

	public boolean isShortest() {
		return shortest;
	}

	public void setShortest(boolean shortest) {
		this.shortest = shortest;
	}

	public boolean isLongest() {
		return longest;
	}

	public void setLongest(boolean longest) {
		this.longest = longest;
	}

}
