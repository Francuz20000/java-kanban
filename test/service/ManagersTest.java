package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagersTest {
	// Проверить что Менеджер Задач создётся корректно
	@Test
	public void checkInitializedAndReadyToWorkInstancesOfTaskManagers() {
		TaskManager taskManager = Managers.getDefault();
		
		assertNotNull(taskManager, "Создать Объект Менеджера Задач не удалось!");
	}
	
	// Проверить что Менеджер Истории создаётся корректно
	@Test
	public void checkInitializedAndReadyToWorkInstancesOfHistoryManagers() {
		HistoryManager historyManager = Managers.getDefaultHistory();
		
		assertNotNull(historyManager, "Создать Объект Менеджера Истории не удалось!");
	}
}
