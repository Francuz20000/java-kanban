package model;

import enumerations.*;
import java.util.HashMap;
import java.util.Map;

public class Epic extends Task {
	// Каждый эпик знает, какие подзадачи в него входят
	private HashMap<Integer, Subtask> subtasks;
	
	public Epic(int id) {
		super(id);
		super.typeTask = TypeTask.EPIC;
		this.subtasks = new HashMap<>();
	}
	
	public Epic(int id, String name, String description, Status status) {
		super(id, name, description, status);
		super.typeTask = TypeTask.EPIC;
		this.subtasks = new HashMap<>();
		
	}
	
	public Epic(Epic epic) {
		super(epic);
		super.typeTask = TypeTask.EPIC;
		this.subtasks = new HashMap<>();
	}
	
	// Расчитать текущий статус
	public Status getStatus() {
		// Если Подзадач нету то Эпик всегда NEW
		if (this.subtasks.isEmpty()) return Status.NEW;
		
		// Если все Подзадачи NEW то Эпик тоже NEW
		boolean isNew = true;
		for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
			if (entry.getValue().getStatus() != Status.NEW) {
				isNew = false;
				break;
			}
		}
		if (isNew) return Status.NEW;
		
		// Если все Подзадачи DONE то Эпик тоже DONE
		boolean isDONE = true;
		for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
			if (entry.getValue().getStatus() != Status.DONE) {
				isDONE = false;
				break;
			}
		}
		if (isDONE) return Status.DONE;
		
		// Иначе IN_PROGRESS
		return Status.IN_PROGRESS;
	}
	
	// Получить Подзадачу по id
	public Subtask getSubtask(int id) {
		if (this.subtasks.containsKey(id)) {
			return this.subtasks.get(id);
		}
		
		return null;
	}
	
	// Получить список всех Подзадач
	public HashMap<Integer, Subtask> getAllSubtasks() {
		return this.subtasks;
	}
	
	// Добавить Эпику Подзадачу
	public void putSubtask(Subtask subtask) {
		if (subtask != null) {
			this.subtasks.put(subtask.getId(), subtask);
			subtask.setEpic(this);
		}
	}
	
	// Удалить у Эпика Подзадачу
	public void delSubtask(int id) {
		this.subtasks.remove(id);
	}
	
	// Удалить все Подзадачи у Эпика
	public void delAllSubtask() {
		this.subtasks.clear();
	}
	
	// Получить строковое представление
	@Override
	public String toString() {
		// Обновить Статус
		super.setStatus(this.getStatus());
		
		String result = super.toString() + ",subtasks=[";
		
		boolean isFirst = true;
		for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
			// Всегда кроме первой этэрации вначале добавить запятую
			if (isFirst) {
				isFirst = false;
			} else {
				result = result + ",";
			}
			
			Integer subtaskId = (Integer) entry.getValue().getId();
			result = result + "subtask.id=" + subtaskId.toString();
		}
		
		result = result + "]";
		
		return super.toString();
	}
}
