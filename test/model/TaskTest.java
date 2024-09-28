package model;

import enumerations.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Задача")
public class TaskTest {
	// Сравнить экземпляры Задач
	@DisplayName("Сравнить Задачи разных Конструкторов")
	@Test
	public void shouldBeEqual() {
		Task task1 = new Task(1);
		Task task2 = new Task(1, "Задача 1", "Пример Задача 1", Status.NEW);
		
		assertEquals(task1, task2, "Сравнение экземпляров Задач по id не пройдено!");
	}
}
