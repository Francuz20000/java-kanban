package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
	private ArrayList<Task> history;            // Последние просмотренные пользователем задачи
	
	InMemoryHistoryManager() {
		this.history = new ArrayList<>();
	}
	
	// Получить Список последних 10 просмотренных Задач
	@Override
	public List<Task> getHistory() {
		List<Task> result = new ArrayList<>();
		//int taskCount = 0;
		for(Task task : history) {
			result.add(task);
			//taskCount++;
			//if(taskCount > 10) break;
		}
		return result;
	}
	
	// Добавить Задачу в Историю
	@Override
	public void setHistory(Task task) {
		int historySize = 10;
		this.history.add(task);
		while(this.history.size() > historySize) {
			this.history.remove(1);
		}
	}
}
