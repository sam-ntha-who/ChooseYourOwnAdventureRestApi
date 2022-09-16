package co.grandcircus.FinalProject.ExceptionHandling;

public class SceneNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public SceneNotFoundException(String id) {
		super("Scene id " + id + " not found");
	}
}
