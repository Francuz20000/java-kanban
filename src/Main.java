import enumerations.Status;
import model.Epic;
import model.Subtask;
import model.Task;
import service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {

    
    public static void main(String[] args) {
        System.out.println(" ");
        System.out.println("---=== Поехали! ===---");
        System.out.println(" ");
        
        // Создать Объект-менеджер который управляет всеми задачами
        TaskManager taskManager = Managers.getDefault();
        
        // Создать Задачи, Эпики и Подзадачи
        createObjects(taskManager);
        
        // Распечатать списки Эпиков, Задач и Подзадач
        showObjects(taskManager);
        
        // Изменить статусы созданных объектов
        changeObjects(taskManager);
        
        // Распечатать списки Эпиков, Задач и Подзадач
        showObjects(taskManager);
        
        // Удалить Обекты Задачи и Эпика
        delObjects(taskManager);
        
        // Распечатать списки Эпиков, Задач и Подзадач
        showObjects(taskManager);
        
        // Имитировать Историю вызовов
        taskManager.getTask(2);
        taskManager.getEpic(6);
        
        // Вывести в консоль списки: Задач, Эпиков, Подзадач, Истории
        printAllTasks(taskManager);
        
        System.out.println("---=== Приехали! ===---");
    }
    
    // Создать Задачи, Эпики и Подзадачи
    public static void createObjects(TaskManager taskManager) {
        // Создать две Задачи
        Task task1 = new Task(taskManager.getCounter(), "Задача 1", "Попытка создать Задача 1", Status.NEW);
        Task task2 = new Task(taskManager.getCounter(), "Задача 2", "Попытка создать Задача 2", Status.NEW);
        
        // Добавить Задачи в Менеджер Задач
        taskManager.put(task1);
        taskManager.put(task2);
        
        // Создать Эпик с двумя Подзадачами
        Epic epic1 = new Epic(taskManager.getCounter(), "Эпик 1", "Попытка создать Эпик 1", Status.NEW);
        Subtask subtask1 = new Subtask(taskManager.getCounter(), "Подзадача 1", "Попытка создать Подзадача 1", Status.NEW);
        Subtask subtask2 = new Subtask(taskManager.getCounter(), "Подзадача 2", "Попытка создать Подзадача 2", Status.NEW);
        
        // Положить Эпик в Менеджер Задач
        taskManager.put(epic1);
        
        // Добавить Подзадачи в Менеджер Задач
        taskManager.put(epic1.getId(), subtask1);
        taskManager.put(epic1.getId(), subtask2);
        
        // Создать Эпик с одной Подзадачей.
        Epic epic2 = new Epic(taskManager.getCounter(), "Эпик 2", "Попытка создать Эпик 2", Status.NEW);
        Subtask subtask3 = new Subtask(taskManager.getCounter(), "Подзадача 3", "Попытка создать Подзадача 3", Status.NEW);
        
        // Добавить Эпик в Менеджер Задач
        taskManager.put(epic2);
        
        // Добавить Подзадачу в Менеджер Задач
        taskManager.put(epic2.getId(), subtask3);
    }
    
    // Распечатать списки эпиков, задач и подзадач
    public static void showObjects(TaskManager taskManager) {
        
        // Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
        List<Epic> epics = taskManager.getAllEpics();
        
        for (Epic epic : epics) {
            System.out.println(epic.toString());
        }
        
        List<Task> tasks = taskManager.getAllTasks();
        
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        
        List<Subtask> subtasks = taskManager.getAllSubtasks();
        
        for (Subtask subtask : subtasks) {
            System.out.println(subtask.toString());
        }
        
        System.out.println(" ");
    }
    
    // Изменить статусы созданных объектов
    public static void changeObjects(TaskManager taskManager) {
        List<Task> tasks = taskManager.getAllTasks();
        int i = 1;
        for (Task task : tasks) {
            if (i == 1) {
                task.setStatus(Status.IN_PROGRESS);
            } else if (i == 2) {
                task.setStatus(Status.IN_PROGRESS);
            }
            i++;
        }
        
        List<Subtask> subtasks = taskManager.getAllSubtasks();
        i = 1;
        for (Subtask subtask : subtasks) {
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
        List<Task> tasks = taskManager.getAllTasks();
        int i = 1;
        int delId = 0;
        for (Task task : tasks) {
            if (i == 1) {
                delId = task.getId();
            }
            i++;
        }
        taskManager.delTask(delId);
        
        List<Epic> epics = taskManager.getAllEpics();
        i = 1;
        delId = 0;
        for (Epic epic : epics) {
            if (i == 1) {
                delId = epic.getId();
            }
            i++;
        }
        taskManager.delEpic(delId);
    }
    
    // Вывести в консоль списки: Задач, Эпиков, Подзадач, Истории
    private static void printAllTasks(TaskManager taskManager) {
        System.out.println("Задачи:");
        for (Task task : taskManager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : taskManager.getAllEpics()) {
            System.out.println(epic);
            
            for (Task task : taskManager.getAllSubtasksByEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : taskManager.getAllSubtasks()) {
            System.out.println(subtask);
        }
        
        System.out.println("История:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
        System.out.println(" ");
    }
}
