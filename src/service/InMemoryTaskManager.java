package service;

import enumerations.*;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
	private int counter = 0; // Для генерации идентификаторов можно использовать числовое поле-счётчик
	
	// Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.
	private HashMap<Integer, Task> tasks;       // Список Задач
	private HashMap<Integer, Epic> epics;       // Список Эпиков
	private HashMap<Integer, Subtask> subtasks; // Список Подзадач
	
	public InMemoryTaskManager() {
		this.tasks = new HashMap<>();
		this.epics = new HashMap<>();
		this.subtasks = new HashMap<>();
	}
	
	// Получить новый уникальный id
	@Override
	public int getCounter() {
		return ++this.counter;
	}

	// Получить список всех Задач
	@Override
	public List<Task> getAllTasks() {
		// Подготовить пустой список копию
		List<Task> result = new ArrayList<>();

		// Пройти по списку Задач и скопировать каждую в возвращаемый список
		for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
			// Создать копию Задачи
			Task task = new Task(entry.getValue());

			// Добавить id и копию Задачи в список-копию
			result.add(task);
		}
		return result;
	}

	// Получить список всех Эпиков
	@Override
	public List<Epic> getAllEpics() {
		// Подготовить пустой список копию
		List<Epic> result = new ArrayList<>();
		
		// Пройти по списку Эпиков и скопировать каждую в возвращаемый список
		for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
			// Создать копию Эпика
			Epic epic = new Epic(entry.getValue());
			
			// Добавить id и копию Эпик в список-копию
			result.add(epic);
		}
		return result;
	}
	
	// Получить список всех Подзадач
	@Override
	public List<Subtask> getAllSubtasks() {
		// Подготовить пустой список копию
		List<Subtask> result = new ArrayList<>();
		
		// Пройти по списку Подзадач и скопировать каждую в возвращаемый список
		for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
			// Создать копию Подзадачи
			Subtask subtask = new Subtask(entry.getValue());
			
			// Добавить id и копию Подзадачи в список-копию
			result.add(subtask);
		}
		return result;
	}
	
	// Удалить все Задачи
	@Override
	public void delAllTasks() {
		this.tasks.clear();
	}
	
	// Удалить все Эпики
	@Override
	public void delAllEpics() {
		// Удалить все Подзадачи у Эпиков
		for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
			this.delAllSubtasksId(entry.getValue());
		}
		
		// Удалить все Эпики
		this.epics.clear();
	}
	
	// Удалить все Подзадачи Эпика
	@Override
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
	@Override
	public Task getTaskById(int id) {
		return this.tasks.get(id);
	}
	
	// Получить Эпик по id
	@Override
	public Epic getEpicById(int id) {
		return this.epics.get(id);
	}
	
	// Получить Подзадачу по id
	@Override
	public Subtask getSubtaskById(int id) {
		return this.subtasks.get(id);
	}
	
	// Добавить Задачу
	@Override
	public void put(Task task) {
		// Если объект пустой или неверного типа
		if (task == null) return;
		if (task.getTypeTask() != TypeTask.TASK) return;
		
		// Добавить Задачу в список
		this.tasks.put(task.getId(), task);
	}
	
	// Добавить Эпик
	@Override
	public void put(Epic epic) {
		// Если объект пустой или неверного типа
		if (epic == null) return;
		if (epic.getTypeTask() != TypeTask.EPIC) return;
		
		// Добавить Эпик в список
		this.epics.put(epic.getId(), epic);
	}
	
	// Создать Подзадачу
	@Override
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
	@Override
	public void update(Task task) {
		this.tasks.put(task.getId(), task);
	}
	
	// Обновить Эпик
	@Override
	public void update(Epic epic) {
		this.epics.put(epic.getId(), epic);
	}
	
	// Обновить Подзадачу
	@Override
	public void update(Subtask subtask) {
		this.subtasks.put(subtask.getId(), subtask);
	}
	
	// Удалить Задачу по id
	@Override
	public void delTask(int id) {
		this.tasks.remove(id);
	}
	
	// Удалить Эпик по id
	@Override
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
	@Override
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
	@Override
	public List<Subtask> getAllSubtasksByEpic(Epic epic) {
		List<Subtask> result = new ArrayList<>();
		if (epic != null) {
			List<Integer> subtasksId = epic.getAllSubtasksId();
			for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
				
				Subtask subtask = new Subtask(entry.getValue());
				result.add(subtask);
			}
		}
		return result;
	}
	
	// Получить списка всех Подзадач определённого Эпика по id
	@Override
	public List<Subtask> getAllSubtasksByEpic(int idEpic) {
		List<Subtask> result = new ArrayList<>();
		Epic epic = this.epics.get(idEpic);
		if (epic != null) {
			List<Integer> subtasksId = epic.getAllSubtasksId();
			for (int i = 0, c = subtasksId.size(); i < c; i++) {
				Integer subtaskId = subtasksId.get(i);
				Subtask subtask = new Subtask(this.subtasks.get(subtaskId));
				result.add(subtask);
			}
		}
		return result;
	}

	// Рассчитать Статус задачи
	@Override
	public Status CalculateTaskStatus(Task task) {
		if (task == null) return null;
		Status result = null;

		if (task.getTypeTask() == TypeTask.EPIC) {
			Epic epic = (Epic)task;
			List<Subtask> list = getAllSubtasksByEpic(epic);
			for (Subtask subtask : list) {
				if (subtask.getStatus() == Status.IN_PROGRESS) return Status.IN_PROGRESS;
				if (result == null) result = subtask.getStatus();
				if (result != subtask.getStatus()) return Status.IN_PROGRESS;
			}
		}
		else {
			result = task.getStatus();
		}
		return result;
	}
	
	// Получить список всех Эпиков
	public HashMap<Integer, Epic> getAllHashMapEpics() {
		HashMap<Integer, Epic> result = new HashMap<>();
		for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
			Epic epic = new Epic(entry.getValue());
			result.put(entry.getKey(), epic);
		}
		
		return result;
	}
	
	// Получить список всех Задач
	public HashMap<Integer, Task> getAllHashMapTasks() {
		HashMap<Integer, Task> result = new HashMap<>();
		for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
			Task task = new Task(entry.getValue());
			result.put(entry.getKey(), task);
		}
		
		return result;
	}
	
	// Получить список всех Подзадач
	public HashMap<Integer, Subtask> getAllHashMapSubtasks() {
		HashMap<Integer, Subtask> result = new HashMap<>();
		for (Map.Entry<Integer, Subtask> entry : this.subtasks.entrySet()) {
			Subtask subtask = new Subtask(entry.getValue());
			result.put(entry.getKey(), subtask);
		}
		
		return result;
	}
	
	// Добавить Подзадачу в Эпик
	private void addSubtaskToEpic(int epicId, Subtask subtask) {
		if (this.epics.containsKey(epicId)) {
			Epic epic = this.epics.get(epicId);
			epic.putSubtask(subtask.getId());
		}
	}
}
