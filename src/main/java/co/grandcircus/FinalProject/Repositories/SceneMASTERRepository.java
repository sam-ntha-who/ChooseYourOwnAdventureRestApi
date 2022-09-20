package co.grandcircus.FinalProject.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.FinalProject.Models.Scene;

public interface SceneMASTERRepository extends MongoRepository<Scene, String> {
	
	List<Scene> findAll();
	
	List<Scene> findByParentId(String parentId);
	
	Optional<Scene> findByStoryIdAndId(String storyId, String id);
	
	Scene findSceneById(String id);

}
