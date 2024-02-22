package service;

import enumerations.*;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
	private int counter = 0; // Для генерации идентификаторов можно использовать числовое поле-счётчик
	
	// Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.
	private HashMap<Integer, Task> tasks;       // Список Задач
	private HashMap<Integer, Epic> epics;       // Список Эпиков
	private HashMap<Integer, Subtask> subtasks; // Список Подзадач
	
	public TaskManager() {
		this.tasks = new HashMap<>();
		this.epics = new HashMap<>();
		this.subtasks = new HashMap<>();
	}
	
	// Получить новый уникальный id
	public int getCounter() {
		return ++this.counter;
	}
	
	// Получить список копий всех Задач
	public HashMap<Integer, Task> getCopyAllTasks() {
		// Подготовить пустой список копию
		HashMap<Integer, Task> result = new HashMap<>();
		
		// Пройти по списку Задач и скопировать каждую в возвращаемый список
		for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
			// Создать копию Задачи
			Task task = new Task(entry.getValue());
			
			// Получить id Задачи
			int taskId = entry.getKey();
			
			// Добавить id и копию Задачи в список-копию
			result.put(taskId, task);
		}
		return result;
	}
	
	// Получить список копий всех Эпиков
	public HashMap<Integer, Epic> getCopyAllEpics() {
		// Подготовить пустой список копию
		HashMap<Integer, Epic> result = new HashMap<>();
		
		// Пройти по списку Эпиков и скопировать каждую в возвращаемый список
		for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
			// Создать копию Эпика
			Epic epic = new Epic(entry.getValue());
			
			// Получить id Эпика
			int epicId = entry.getKey();
			
			// Добавить id и копию Эпик в список-копию
			result.put(epicId, epic);
		}
		return result;
	}
	
	// Получить список копий всех Подзадач
	public HashMap<Integer, Subtask> getCopyAllSubtasks() {
		// Подготовить пустой список копию
		HashMap<Integer, Subtask> result = new HashMap<>();
		
		// Пройти по списку Подзадач и скопировать каждую в возвращаемый список
		for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
			// Создать копию Подзадачи
			Subtask subtask = new Subtask(entry.getValue());
			
			// Получить id Подзадачи
			int subtaskId = entry.getKey();
			
			// Добавить id и копию Подзадачи в список-копию
			result.put(subtaskId, subtask);
		}
		return result;
	}
	
	// Удалить все Задачи
	public void delAllTasks() {
		this.tasks.clear();
	}
	
	// Удалить все Эпики
	public void delAllEpics() {
		// Удалить все Подзадачи у Эпиков
		for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
			this.delAllSubtasksId(entry.getValue());
		}
		
		// Удалить все Эпики
		this.epics.clear();
	}
	
	// Удалить все Подзадачи Эпика
	public void delAllSubtasksId(Epic epic) {
		// Получить список всех Подзадач
		ArrayList<Integer> SubtasksId = epic.getAllSubtasksId();
		
		// Удалить Подзадачи из общего списка
		for (int i = 0, c = SubtasksId.size(); i < c; i++) {
			this.subtasks.remove(SubtasksId.get(i));
		}
		
		// Удалить у Эпика все id Подзадач
		epic.delAllSubtasksId();
	}
	
	// Получить Задачу по id
	public Task getTaskById(int id) {
		return this.tasks.get(id);
	}
	
	// Получить Эпик по id
	public Epic getEpicById(int id) {
		return this.epics.get(id);
	}
	
	// Получить Подзадачу по id
	public Subtask getSubtaskById(int id) {
		return this.subtasks.get(id);
	}
	
	// Добавить Задачу
	public void put(Task task) {
		// Если объект пустой или неверного типа
		if (task == null) return;
		if (task.getTypeTask() != TypeTask.TASK) return;
		
		// Добавить Задачу в список
		this.tasks.put(task.getId(), task);
	}
	
	// Создать Эпик
	public void put(Epic epic) {
		// Если объект пустой или неверного типа
		if (epic == null) return;
		if (epic.getTypeTask() != TypeTask.EPIC) return;
		
		// Добавить Эпик в список
		this.epics.put(epic.getId(), epic);
		
		// Получить все подзадачи Эпика
		HashMap<Integer, Subtask> subtasks = epic.getCopyAllSubtasks();
		
		// Добавить все подзадачи Эпика в общий список
		this.subtasks.putAll(subtasks);
	}
	
	// Создать Подзадачу
	public void put(int epicId, Subtask subtask) {
		// Если объект пустой или неверного типа
		if (subtask == null) return;
		if (subtask.getTypeTask() != TypeTask.SUBTASK) return;
		
		// Если такой Эпик в списке существует
		if (this.epics.containsKey(epicId)) {
			// Получить из списка Эпик
			Epic epic = this.epics.get(epicId);
			
			// Добавить в Подзадачу id Эпика
			subtask.setEpic(epic.getId());
			
			// Добавить в Эпик id Подзадачи
			epic.putSubtask(subtask.getId());
			
			// Добавить Подзадачу в список
			this.subtasks.put(subtask.getId(), subtask);
		}
	}
	
	// Обновить Задачу
	public void update(Task task) {
		this.tasks.put(task.getId(), task);
	}
	
	// Обновить Эпик
	public void update(Epic epic) {
		this.epics.put(epic.getId(), epic);
	}
	
	// Обновить Подзадачу
	public void update(Subtask subtask) {
		this.subtasks.put(subtask.getId(), subtask);
	}
	
	// Удалить Задачу по id
	public void delTask(int id) {
		this.tasks.remove(id);
	}
	
	// Удалить Эпик по id
	public void delEpic(int id) {
		// Получить Эпик из общего списка по id
		Epic epic = this.epics.get(id);
		
		if (epic != null) {
			// Удалить из списка все Подзадачи Эпика
			delAllSubtasksId(epic);
			
			// Удалить Эпик
			this.epics.remove(id);
		}
	}
	
	// Удалить Подзадачу по id
	public void delSubtask(int id) {
		// Получить Подзадачу по id
		Subtask subtask = this.subtasks.get(id);
		
		// Из Подзадачи получить id Эпика
		int idEpic = subtask.getEpicId();
		
		// Из списка получить Эпик по id
		Epic epic = this.epics.get(idEpic);
		
		// Из Эпика удалить Подзадачу
		epic.delSubtask(id);
		
		// Удалить Подзадачу из списка Подзадач
		this.subtasks.remove(id);
	}
	
	// Получить список всех Подзадач определённого Эпика
	public HashMap<Integer, Subtask> getAllSubtasksByEpic(Epic epic) {
		return epic.getAllSubtasks();
	}
	
	// Получить списка всех Подзадач определённого Эпика по id
	public HashMap<Integer, Subtask> getAllSubtasksByEpic(int idEpic) {
		Epic epic = this.epics.get(idEpic);
		if (epic != null) return epic.getAllSubtasks();
		return null;
	}
	
	private void addSubtaskToEpic(int epicId, Subtask subtask) {
		if (this.epics.containsKey(epicId)) {
			Epic epic = this.epics.get(epicId);
			epic.putSubtask(subtask.getId());
		}
	}
}
