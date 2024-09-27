package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Утилитарный Класс")
public class ManagersTest {
	// Проверить что Менеджер Задач создётся корректно
	@DisplayName("Создать Менеджер Задач")
	@Test
	public void checkInitializedAndReadyToWorkInstancesOfTaskManagers() {
		TaskManager taskManager = Managers.getDefault();
		
		assertNotNull(taskManager, "Создать Объект Менеджера Задач не удалось!");
	}
	
	// Проверить что Менеджер Истории создаётся корректно
	@DisplayName("Создать Менеджер Истории")
	@Test
	public void checkInitializedAndReadyToWorkInstancesOfHistoryManagers() {
		HistoryManager historyManager = Managers.getDefaultHistory();
		
		assertNotNull(historyManager, "Создать Объект Менеджера Истории не удалось!");
	}
}
