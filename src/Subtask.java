import java.util.HashMap;

public class Subtask extends Task {
	// Для каждой подзадачи известно, в рамках какого эпика она выполняется.
	private Epic epic;
	
	Subtask(int id) {
		super(id);
		super.typeTask = TypeTask.SUBTASK;
	}
	
	Subtask(int id, String name, String description, Status status) {
		super(id, name, description, status);
		super.typeTask = TypeTask.SUBTASK;
	}
	
	Subtask(Subtask subtask) {
		super(subtask);
		super.typeTask = TypeTask.SUBTASK;
	}
	
	// Получить Эпик Подзадачи
	public Epic getEpic() {
		return this.epic;
	}
	
	// Назначить Подзадаче Эпик
	public void setEpic(Epic epic) {
		this.epic = epic;
	}
	
	// Получить строковое представление
	@Override
	public String toString() {
		if (this.epic != null) {
			Integer epicId = (Integer) this.epic.getId();
			return super.toString() + ",epic.id=" + epicId.toString();
		}
		return super.toString() + ",epic.id=null";
	}
}
