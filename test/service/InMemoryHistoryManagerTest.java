package service;

import enumerations.Status;
import model.Task;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Менеджер Истории")
public class InMemoryHistoryManagerTest {
	@DisplayName("Добавить Задачу в Историю через Менеджер Задач")
	@Test
	public void shouldSaveTasks() {
		InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
		InMemoryTaskManager taskManager = new InMemoryTaskManager(historyManager);
		
		// Создать Задачу
		final int taskId1 = taskManager.getCounter();
		Task task1 = new Task(taskId1, "Задача 1", "Попытка создать Задача 1", Status.NEW);
		
		// Добавить Задачу в Менеджер Задач
		taskManager.put(task1);
		
		// Имитировать Историю вызовов
		taskManager.getTask(taskId1);
		
		// Получить историю
		List<Task> tasks = taskManager.getHistory();
		
		assertNotNull(tasks, "Задачи не возвращаются!");
		assertEquals(1, tasks.size(), "Неверное количество задач!");
		assertEquals(task1, tasks.getFirst(), "Задачи не совпадают!");
	}
	
	@DisplayName("Добавить Задачу в Историю")
	@Test
	void shouldAddTask() {
		InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
		
		// Создать Задачу
		final int taskId1 = 1;
		Task task1 = new Task(taskId1, "Задача 1", "Попытка создать Задача 1", Status.NEW);
		
		// Добавить Задачу в Историю
		historyManager.setHistory(task1);
		
		// Получить Историю
		final List<Task> history = historyManager.getHistory();
		
		assertNotNull(history, "История пустая!");
		assertEquals(1, history.size(), "История не верное количество элементов!");
	}
	
	@DisplayName("При изменении Задачи История не меняется")
	@Test
	public void shouldChangeTask() {
		InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
		
		// Создать две Задачи
		final int taskId1 = 1;
		Task task1 = new Task(taskId1, "Задача 1", "Попытка создать Задача 1", Status.NEW);
		
		// Добавить Задачи в Менеджер Задач
		historyManager.setHistory(task1);
		
		// Изменить задачу
		task1.setName("Обновлённая Задача 1");
		
		// Получить Историю
		final List<Task> history = historyManager.getHistory();
		
		assertNotNull(history, "История пустая!");
		assertEquals(1, history.size(), "История не верное количество элементов!");
		
		Task taskResult = history.getFirst();
		assertEquals("Задача 1", taskResult.getName(), "Задача в История изменилась!");
	}
}
