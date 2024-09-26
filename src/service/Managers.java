package service;

import model.Task;

// Утилитарный класс для создания Менеджера
public class Managers {
	// Получить Менеджер Задач
	public static TaskManager getDefault() {
		return new InMemoryTaskManager(new InMemoryHistoryManager());
	}
	
	public static InMemoryHistoryManager getDefaultHistory() {
		return new InMemoryHistoryManager();
	}
}
