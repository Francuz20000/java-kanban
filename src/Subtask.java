public class Subtask extends Task {
	
	Subtask(int id) {
		super(id);
	}
	
	Subtask(int id, String name, String description, Status status) {
		super(id, name, description, status);
	}
	
	Subtask(Subtask subtask) {
		super(subtask);
	}
}
