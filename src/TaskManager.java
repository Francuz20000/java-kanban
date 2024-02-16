import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

public class TaskManager {
	private int counter = 0; // Для генерации идентификаторов можно использовать числовое поле-счётчик
	
	// Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.
	private HashMap<Integer, Task> tasks;       // Список Задач
	private HashMap<Integer, Epic> epics;       // Список Эпиков
	private HashMap<Integer, Subtask> subtasks; // Список Подзадач
	
	TaskManager() {
		this.tasks = new HashMap<>();
		this.epics = new HashMap<>();
		this.subtasks = new HashMap<>();
	}
	
	// Получить новый уникальный id
	public int getCounter() {
		return ++this.counter;
	}
	
	// Получить список всех Задач
	public HashMap<Integer, Task> getListAllTasks() {
		return this.tasks;
	}
	
	// Получить список всех Эпиков
	public HashMap<Integer, Epic> getListAllEpics() {
		return this.epics;
	}
	
	// Получить список всех Подзадач
	public HashMap<Integer, Subtask> getListAllSubtasks() {
		return this.subtasks;
	}
	
	// Удалить все Задачи
	public void delAllTasks() {
		this.tasks.clear();
	}
	
	// Удалить все Эпики
	public void delAllEpics() {
		// Удалить все Подзадачи у Эпиков
		for (Map.Entry<Integer, Epic> entry : this.epics.entrySet()) {
			this.delAllSubtasks(entry.getValue());
		}
		
		// Удалить все Эпики
		this.epics.clear();
	}
	
	// Удалить все Подзадачи
	public void delAllSubtasks(Epic epic) {
		// Получить список Подзадач Эпика
		HashMap<Integer, Subtask> subtasksToDel = epic.getAllSubtasks();
		
		// Удалить Подзадачи из общего списка
		for(Integer id : subtasksToDel.keySet()) {
			this.subtasks.remove(id);
		}
		
		// Удалить все Подзадачи
		epic.delAllSubtask();
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
	
	// Получить все Задачи
	public HashMap<Integer, Task> getAllTasks() {
		return this.tasks;
	}
	
	// Получить все Эпики
	public HashMap<Integer, Epic> getAllEpics() {
		return this.epics;
	}
	
	// Получить все Подзадачи
	public HashMap<Integer, Subtask> getAllSubtasks() {
		return this.subtasks;
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
		HashMap<Integer, Subtask> subtasks = epic.getAllSubtasks();
		
		// Добавить все подзадачи Эпика в общий список
		this.subtasks.putAll(subtasks);
	}
	
	// Создать Подзадачу
	public void put(Subtask subtask) {
		// Если объект пустой или неверного типа
		if (subtask == null) return;
		if (subtask.getTypeTask() != TypeTask.SUBTASK) return;
		
		// Добавить Подзадачу в список
		this.subtasks.put(subtask.getId(), subtask);
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
		
		// Получить все подзадачи Эпика
		HashMap<Integer, Subtask> subtasks = epic.getAllSubtasks();
		
		// Удалить у Эпика все Подзадачи
		this.delAllSubtasks(epic);
		
		// Удалить Эпик
		this.epics.remove(id);
	}
	
	// Удалить Подзадачу по id
	public void delSubtask(int id) {
		// Получить Подзадачу по id
		Subtask subtask = this.subtasks.get(id);
		
		// Из Подзадачи получить Эпик
		Epic epic = subtask.getEpic();
		
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
}
