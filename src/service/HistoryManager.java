package service;

import model.Task;

import java.util.List;

public interface HistoryManager {
	// Получить Список последних просмотренных Задач
	List<Task> getHistory();
	
	// Добавить Задачу в Историю
	void setHistory(Task task);
}
