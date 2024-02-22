package model;

import enumerations.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Epic extends Task {
	// Каждый эпик знает, id каких подзадач в него входят
	//private HashMap<Integer, Subtask> subtasks;
	private ArrayList<Integer> subtasksId;
	
	public Epic(int id) {
		super(id);
		super.typeTask = TypeTask.EPIC;
		//this.subtasks = new HashMap<>();
		this.subtasksId = new ArrayList<>();
	}
	
	public Epic(int id, String name, String description, Status status) {
		super(id, name, description, status);
		super.typeTask = TypeTask.EPIC;
		//this.subtasks = new HashMap<>();
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
		ArrayList<Integer> addSubtasksId = epic.getAllSubtasksId();
		
		// Добавить копии id Подзадач в список
		this.subtasksId.addAll(addSubtasksId);
	}
	
	/*----------- Перенести в TaskManager */
	// Расчитать текущий статус
	public Status getStatus() {
		// Если Подзадач нету то Эпик всегда NEW
		//if (this.subtasks.isEmpty()) return Status.NEW;
		if (this.subtasksId.isEmpty()) return Status.NEW;
		
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
	/*
	// Получить Подзадачу по id
	public Subtask getSubtask(int id) {
		if (this.subtasks.containsKey(id)) {
			return this.subtasks.get(id);
		}
		
		return null;
	}*/
	
	// Получить список копий id всех Подзадач
	public ArrayList<Integer> getAllSubtasksId() {
		//return this.subtasks;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int idSubtask : this.subtasksId) {
			result.add(idSubtask);
		}
		return result;
	}
	
	// Добавить Эпику id Подзадачи
	public void putSubtask(int idSubtask) {
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
		
		return super.toString();
	}
}
