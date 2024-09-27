package model;

import enumerations.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EpicTest {
	
	// Сравнить экземпляры Эпиков
	@Test
	public void shouldBeEqual() {
		Epic epic1 = new Epic(1);
		Epic epic2 = new Epic(1, "Эпик 1", "Пример Эпик 1", Status.NEW);
		
		assertEquals(epic1, epic2, "Сравнение экземпляров Эпиков по id не пройдено!");
	}
	
	// Проверить можно ли положить Эпик в самого себя
	@Test
	public void shouldPutHimself() {
		Epic epic = new Epic(1);
		
		epic.putSubtask(1);
		
		assertTrue(epic.checkSubtask(1), "Запрещенно класть Эпик самого в себя как Подзадачу!");
	}
}
