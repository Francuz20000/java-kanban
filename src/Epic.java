public class Epic extends Task {
	
	Epic(int id) {
		super(id);
	}
	
	Epic(int id, String name, String description, Status status) {
		super(id, name, description, status);
	}
	Epic(Epic epic) {
		super(epic);
	}
}
