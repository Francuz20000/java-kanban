package service;

import enumerations.Status;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface TaskManager {
	// Получить новый уникальный id
	int getCounter();
	
	// Получить список всех Задач
	List<Task> getAllTasks();
	
	// Получить список всех Эпиков
	List<Epic> getAllEpics();
	
	// Получить список всех Подзадач
	List<Subtask> getAllSubtasks();
	
	// Удалить все Задачи
	void delAllTasks();
	
	// Удалить все Эпики
	void delAllEpics();
	
	// Удалить все Подзадачи Эпика
	void delAllSubtasksId(Epic epic);
	
	// Получить Задачу по id
	Task getTask(int id);
	
	// Получить Эпик по id
	Epic getEpic(int id);
	
	// Получить Подзадачу по id
	Subtask getSubtask(int id);
	
	// Добавить Задачу
	void put(Task task);
	
	// Добавить Эпик
	void put(Epic epic);
	
	// Создать Подзадачу
	void put(int epicId, Subtask subtask);
	
	// Обновить Задачу
	void update(Task task);
	
	// Обновить Эпик
	void update(Epic epic);
	
	// Обновить Подзадачу
	void update(Subtask subtask);
	
	// Удалить Задачу по id
	void delTask(int id);
	
	// Удалить Эпик по id
	void delEpic(int id);
	
	// Удалить Подзадачу по id
	void delSubtask(int id);
	
	// Получить список всех Подзадач определённого Эпика
	List<Subtask> getAllSubtasksByEpic(Epic epic);
	
	// Получить списка всех Подзадач определённого Эпика по id
	List<Subtask> getAllSubtasksByEpic(int idEpic);
	
	// Рассчитать Статус задачи
	Status CalculateTaskStatus(Task task);
}

