package model;

import enumerations.*;

public class Subtask extends Task {
	// Для каждой подзадачи известно, в рамках какого эпика она выполняется.
	//private Epic epic;
	private int epicId;
	
	public Subtask(int id) {
		super(id);
		super.typeTask = TypeTask.SUBTASK;
	}
	
	public Subtask(int id, String name, String description, Status status) {
		super(id, name, description, status);
		super.typeTask = TypeTask.SUBTASK;
	}
	
	// Конструктор копирования
	public Subtask(Subtask subtask) {
		super(subtask);
		super.typeTask = TypeTask.SUBTASK;
		this.epicId = subtask.getEpicId();
	}
	
	// Получить Эпик Подзадачи
	public int getEpicId() {
		//return this.epic;
		return this.epicId;
	}
	
	// Назначить Подзадаче Эпик
	public void setEpic(int epic) {
		this.epicId = epic;
	}
	
	// Получить строковое представление
	@Override
	public String toString() {
		return super.toString() + ",epic.id=" + this.epicId;
	}
}
