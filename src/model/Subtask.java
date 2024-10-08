package model;

import enumerations.*;

public class Subtask extends Task {
	// Для каждой Подзадачи известно, в рамках какого Эпика она выполняется.
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
		return this.epicId;
	}
	
	// Назначить Подзадаче Эпик
	public void setEpic(int epic) {
		if(epic == this.getId()) return;
		this.epicId = epic;
	}
	
	// Получить строковое представление
	@Override
	public String toString() {
		return super.toString() + ",epic.id=" + this.epicId;
	}
}
