package service;

import enumerations.Status;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Менеджер Задач")
public class InMemoryTaskManagerTest {
	@DisplayName("Сравнить две Задачи")
	@Test
	public void shouldCreateTasks() {
		InMemoryTaskManager taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());
		
		// Создать две Задачи
		final int taskId1 = taskManager.getCounter();
		final int taskId2 = taskManager.getCounter();
		Task task1 = new Task(taskId1, "Задача 1", "Попытка создать Задача 1", Status.NEW);
		Task task2 = new Task(taskId2, "Задача 2", "Попытка создать Задача 2", Status.NEW);
		
		// Добавить Задачи в Менеджер Задач
		taskManager.put(task1);
		taskManager.put(task2);
		
		// Найти по id
		Task taskResult1 = taskManager.getTask(taskId1);
		Task taskResult2 = taskManager.getTask(taskId2);
		
		// Проверить что Задачи найдены
		assertNotNull(taskResult1, "Не найдена Задача по id!");
		assertNotNull(taskResult2, "Не найдена Задача по id!");
		
		// Сравнить поля Задач
		assertEquals(task1.getTypeTask(), taskResult1.getTypeTask(), "Тип Задач не совподает!");
		assertEquals(task2.getTypeTask(), taskResult2.getTypeTask(), "Тип Задач не совподает!");
		assertEquals(task1.getId(), taskResult1.getId(), "Id Задач не совподает!");
		assertEquals(task2.getId(), taskResult2.getId(), "Id Задач не совподает!");
		assertEquals(task1.getName(), taskResult1.getName(), "Название Задач не совподает!");
		assertEquals(task2.getName(), taskResult2.getName(), "Название Задач не совподает!");
		assertEquals(task1.getDescription(), taskResult1.getDescription(), "Описание Задач не совподает!");
		assertEquals(task2.getDescription(), taskResult2.getDescription(), "Описание Задач не совподает!");
		
		// Изменить Задачу
		task1.setName("Обновлённая Задача 1");
		
		// Добавить Задачи в Менеджер Задач
		taskManager.put(task1);
		
		
	}
	
	@DisplayName("Сравнить Эпики и Подзадачи")
	@Test
	public void shouldCreateEpicWithSubtasks() {
		InMemoryTaskManager taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());
		// Создать Эпик с двумя Подзадачами
		final int epicId1 = taskManager.getCounter();
		final int subtaskId1 = taskManager.getCounter();
		final int subtaskId2 = taskManager.getCounter();
		Epic epic1 = new Epic(epicId1, "Эпик 1", "Попытка создать Эпик 1", Status.NEW);
		Subtask subtask1 = new Subtask(subtaskId1, "Подзадача 1", "Попытка создать Подзадача 1", Status.NEW);
		Subtask subtask2 = new Subtask(subtaskId2, "Подзадача 2", "Попытка создать Подзадача 2", Status.NEW);
		
		// Добавить Эпик в Менеджер Задач
		taskManager.put(epic1);
		
		// Добавить Подзадачи в Менеджер Задач
		taskManager.put(epic1.getId(), subtask1);
		taskManager.put(epic1.getId(), subtask2);
		
		// Найти по id
		Epic epicResult1 = taskManager.getEpic(epicId1);
		Subtask subtaskResult1 = taskManager.getSubtask(subtaskId1);
		Subtask subtaskResult2 = taskManager.getSubtask(subtaskId2);
		
		// Проверить что Эпик и Подзадачи найдены
		assertNotNull(epicResult1, "Не найден Эпик по id!");
		assertNotNull(subtaskResult1, "Не найдена Подзадача по id!");
		assertNotNull(subtaskResult2, "Не найдена Подзадача по id!");
		
		// Сравнить поля Эпиков и Подзадач
		assertEquals(epic1.getTypeTask(), epicResult1.getTypeTask(), "Тип Эпиков не совподает!");
		assertEquals(subtask1.getTypeTask(), subtaskResult1.getTypeTask(), "Тип Подзадач не совподает!");
		assertEquals(subtask2.getTypeTask(), subtaskResult2.getTypeTask(), "Тип Подзадач не совподает!");
		assertEquals(epic1.getId(), epicResult1.getId(), "Id Эпиков не совподает!");
		assertEquals(subtask1.getId(), subtaskResult1.getId(), "Id Подзадач не совподает!");
		assertEquals(subtask2.getId(), subtaskResult2.getId(), "Id Подзадач не совподает!");
		assertEquals(epic1.getName(), epicResult1.getName(), "Название Эпиков не совподает!");
		assertEquals(subtask1.getName(), subtaskResult1.getName(), "Название Подзадач не совподает!");
		assertEquals(subtask2.getName(), subtaskResult2.getName(), "Название Подзадач не совподает!");
		assertEquals(epic1.getDescription(), epicResult1.getDescription(), "Описание Эпиков не совподает!");
		assertEquals(subtask1.getDescription(), subtaskResult1.getDescription(), "Описание Подзадач не совподает!");
		assertEquals(subtask2.getDescription(), subtaskResult2.getDescription(), "Описание Подзадач не совподает!");
		assertEquals(subtask1.getEpicId(), subtaskResult1.getEpicId(), "Id Эпиков у Подзадач не совподает!");
		assertEquals(subtask2.getEpicId(), subtaskResult2.getEpicId(), "Id Эпиков у Падзадач не совподает!");
	}
}
