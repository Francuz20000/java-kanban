package service;

import model.Task;

// Утилитарный класс для создания Менеджера
public class Managers {
	TaskManager taskManager;
	InMemoryHistoryManager historyManager;
	
	Managers() {
		this.taskManager = new InMemoryTaskManager();
		this.historyManager = new InMemoryHistoryManager();
	}
	
	// Получить Менеджер Задач
	public TaskManager getDefault() {
		return this.taskManager;
	}
	
	public InMemoryHistoryManager getDefaultHistory() {
		return this.historyManager;
	}
}
