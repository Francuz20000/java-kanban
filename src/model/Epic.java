package model;

import enumerations.*;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
	// Каждый эпик знает, id каких подзадач в него входят
	private final ArrayList<Integer> subtasksId;
	
	public Epic(int id) {
		super(id);
		super.typeTask = TypeTask.EPIC;
		this.subtasksId = new ArrayList<>();
	}
	
	public Epic(int id, String name, String description, Status status) {
		super(id, name, description, status);
		super.typeTask = TypeTask.EPIC;
		this.subtasksId = new ArrayList<>();
	}
	
	// Конструктор копирования
	public Epic(Epic epic) {
		// Заполнить поля базового класса
		super(epic);
		
		// Задать другой тип задачи
		super.typeTask = TypeTask.EPIC;
		
		// Инициализировать список
		this.subtasksId = new ArrayList<>();
		
		// Получить список копий id всех Подзадач
		List<Integer> addSubtasksId = epic.getAllSubtasksId();
		
		// Добавить копии id Подзадач в список
		this.subtasksId.addAll(addSubtasksId);
	}
	
	// Получить список копий id всех Подзадач
	public List<Integer> getAllSubtasksId() {
		List<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0, c = this.subtasksId.size(); i < c; i++) {
			result.add(this.subtasksId.get(i));
		}
		return result;
	}
	
	// Проверить есть ли Подзадача в Эпике
	public boolean checkSubtask(Integer subtask) {
		return this.subtasksId.contains(subtask);
	}
	
	// Добавить Эпику id Подзадачи
	public void putSubtask(int idSubtask) {
		if(idSubtask == this.getId()) return;
		this.subtasksId.add(idSubtask);
	}
	
	// Удалить у Эпика id Подзадачи
	public void delSubtask(int id) {
		this.subtasksId.remove(id);
	}
	
	// Удалить у Эпика все id Подзадач
	public void delAllSubtasksId() {
		this.subtasksId.clear();
	}
	
	// Получить строковое представление
	@Override
	public String toString() {
		// Обновить Статус
		super.setStatus(this.getStatus());
		
		String result = super.toString() + ",subtasks=[";
		
		
		for (int i = 0; i < this.subtasksId.size(); i++) {
			// Всегда кроме первой этэрации вначале добавить запятую
			if (i > 0) {
				result = result + ",";
			}
			
			Integer subtaskId = this.subtasksId.get(i);
			result = result + "subtask.id=" + subtaskId.toString();
		}
		
		result = result + "]";
		
		return result;
	}
}
