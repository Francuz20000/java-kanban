import java.util.HashMap;
import java.util.Map;

public class Main {

    
    public static void main(String[] args) {
        System.out.println("Поехали!");
        
        // Объект-менеджер который управляет всеми задачами
        TaskManager taskManager = new TaskManager();
        
        // Создать Задачи, Эпики и Подзадачи
        createObjects(taskManager);
        
        // Распечатать списки Эпиков, Задач и Подзадач
        ShowObjects(taskManager);
        
        // Изменить статусы созданных объектов
        changeObjects(taskManager);
        
        // Распечатать списки Эпиков, Задач и Подзадач
        ShowObjects(taskManager);
        
        // Удалить Обекты Задачи и Эпика
        delObjects(taskManager);
        
        // Распечатать списки Эпиков, Задач и Подзадач
        ShowObjects(taskManager);
        
        System.out.println("Приехали!");
    }
    
    // Создать Задачи, Эпики и Подзадачи
    public static void createObjects(TaskManager taskManager) {
        // Создать две Задачи
        Task task1 = new Task(taskManager.getCounter(), "Задача 1", "Попытка создать Задача 1", Status.NEW);
        Task task2 = new Task(taskManager.getCounter(), "Задача 2", "Попытка создать Задача 2", Status.NEW);
        taskManager.put(task1);
        taskManager.put(task2);
        
        // Создать Эпик с двумя Подзадачами
        Epic epic1 = new Epic(taskManager.getCounter(), "Эпик 1", "Попытка создать Эпик 1", Status.NEW);
        Subtask subtask1 = new Subtask(taskManager.getCounter(), "Подзадача 1", "Попытка создать Подзадача 1", Status.NEW);
        Subtask subtask2 = new Subtask(taskManager.getCounter(), "Подзадача 2", "Попытка создать Подзадача 2", Status.NEW);
        
        // Положить Подзадачи в Эпик
        epic1.putSubtask(subtask1);
        epic1.putSubtask(subtask2);
        
        // Положить Эпик в Менеджер Задач
        taskManager.put(epic1);
        
        // Создать Эпик с одной Подзадачей.
        Epic epic2 = new Epic(taskManager.getCounter(), "Эпик 2", "Попытка создать Эпик 2", Status.NEW);
        Subtask subtask3 = new Subtask(taskManager.getCounter(), "Подзадача 3", "Попытка создать Подзадача 3", Status.NEW);
        
        // Положить Подзадачу в Эпик
        epic2.putSubtask(subtask3);
        
        // Положить Эпик в Менеджер Задач
        taskManager.put(epic2);
    }
    
    // Распечатать списки эпиков, задач и подзадач
    public static void ShowObjects(TaskManager taskManager) {
        
        // Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
        HashMap<Integer, Epic> epics = taskManager.getAllEpics();
        
        for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        HashMap<Integer, Task> tasks = taskManager.getAllTasks();
        
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        HashMap<Integer, Subtask> subtasks = taskManager.getAllSubtasks();
        
        for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        System.out.println(" ");
    }
    
    // Изменить статусы созданных объектов
    public static void changeObjects(TaskManager taskManager) {
        HashMap<Integer, Task> tasks = taskManager.getAllTasks();
        int i = 1;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
            if (i == 1) {
                task.setStatus(Status.IN_PROGRESS);
            } else if (i == 2) {
                task.setStatus(Status.IN_PROGRESS);
            }
            i++;
        }
        
        HashMap<Integer, Subtask> subtasks = taskManager.getAllSubtasks();
        i = 1;
        for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
            Subtask subtask = entry.getValue();
            if (i == 1) {
                subtask.setStatus(Status.IN_PROGRESS);
            } else if (i == 2) {
                subtask.setStatus(Status.IN_PROGRESS);
            } else if (i == 3) {
                subtask.setStatus(Status.IN_PROGRESS);
            }
            i++;
        }
    }
    
    // Удалить Обекты Задачи и Эпика
    public static void delObjects(TaskManager taskManager) {
        // И, наконец, попробуйте удалить одну из задач и один из эпиков.
        HashMap<Integer, Task> tasks = taskManager.getAllTasks();
        int i = 1;
        int delId = 0;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task task = entry.getValue();
            if (i == 1) {
                delId = task.getId();
            }
            i++;
        }
        taskManager.delTask(delId);
        
        HashMap<Integer, Epic> epics = taskManager.getAllEpics();
        i = 1;
        delId = 0;
        for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
            Epic epic = entry.getValue();
            if (i == 1) {
                delId = epic.getId();
            }
            i++;
        }
        taskManager.delEpic(delId);
    }
}
