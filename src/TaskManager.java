import java.util.HashMap;

public class TaskManager {
	private int counter = 0; // Для генерации идентификаторов можно использовать числовое поле-счётчик
	
	// Возможность хранить задачи всех типов. Для этого вам нужно выбрать подходящую коллекцию.
	private HashMap<Integer, Task> tasks;
	private HashMap<Integer, Epic> epics;
	private HashMap<Integer, Subtask> subtasks;
	
	// Для генерации идентификаторов использовать counter увеличивая его на 1, когда нужно получить новое значение.
	private int getCounter() {
		return ++counter;
	}
	
	// Получение списка всех задач.
	public HashMap<Integer, Task> getListAllTasks() {
		return null;
	}
	
	// Получение списка всех эпиков.
	public HashMap<Integer, Epic> getListAllEpics() {
		return null;
	}
	
	// Получение списка всех подзадач.
	public HashMap<Integer, Subtask> getListAllSubtasks() {
		return null;
	}
	
	// Удаление всех задач.
	public void delAllTasks() {
	
	}
	
	// Удаление всех эпиков.
	public void delAllEpics() {
	
	}
	
	// Удаление всех подзадач.
	public void delAllSubtasks() {
	
	}
	
	// Получение по идентификатору.
	public Task getTaskById(int id) {
		return null;
	}
	
	// Получение по идентификатору.
	public Epic getEpicById(int id) {
		return null;
	}
	
	// Получение по идентификатору.
	public Subtask getSubtaskById(int id) {
		return null;
	}
	
	// Создание. Сам объект должен передаваться в качестве параметра.
	public void put(Task task) {
	
	}
	
	// Создание. Сам объект должен передаваться в качестве параметра.
	public void put(Epic epic) {
	
	}
	
	// Создание. Сам объект должен передаваться в качестве параметра.
	public void put(Subtask subtask) {
	
	}
	
	// Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
	public void update(Task task) {
	
	}
	
	// Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
	public void update(Epic epic) {
	
	}
	
	// Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
	public void update(Subtask subtask) {
	
	}
	
	// Удаление по идентификатору.
	public void delTask(int id) {
	
	}
	
	// Удаление по идентификатору.
	public void delEpic(int id) {
	
	}
	
	// Удаление по идентификатору.
	public void delSubtask(int id) {
	
	}
	
	// Получение списка всех подзадач определённого эпика.
	public HashMap<Integer, Subtask> getAllSubtasksByEpic(Epic epic) {
		return null;
	}
	
	/*Управление статусами осуществляется по следующему правилу:
	a. Менеджер сам не выбирает статус для задачи.
	Информация о нём приходит менеджеру вместе с информацией о самой задаче.
	По этим данным в одних случаях он будет сохранять статус, в других будет рассчитывать.
	
	b. Для эпиков:
		если у эпика нет подзадач или все они имеют статус NEW, то статус должен быть NEW.
		если все подзадачи имеют статус DONE, то и эпик считается завершённым — со статусом DONE.
		во всех остальных случаях статус должен быть IN_PROGRESS.*/
}
