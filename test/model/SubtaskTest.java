package model;

import enumerations.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Подзадача")
public class SubtaskTest {
	// Сравнить экземпляры Подзадач
	@DisplayName("Сравнить Подзадачи созданые разными конструкторами")
	@Test
	public void shouldBeEqual() {
		Subtask subtask1 = new Subtask(1);
		Subtask subtask2 = new Subtask(1, "Подзадача 1", "Пример Подзадача 1", Status.NEW);
		
		assertEquals(subtask1, subtask2, "Сравнение экземпляров Подзадач по id не пройдено!");
	}
	
	// Проверить можно ли Подзадаче назначить саму себя своим Эпиком
	@DisplayName("Нельзя Подзадаче назначить себя своим Эпиком")
	@Test
	public void ShouldMakeEpicHimself() {
		Subtask subtask = new Subtask(1);
		subtask.setEpic(1);
		
		assertNotEquals(1, subtask.getEpicId(), "Запрещено назначать Подзадаче себя своим Эпиком!");
	}
}
