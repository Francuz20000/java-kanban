import enumerations.Status;
import model.Epic;
import model.Subtask;
import model.Task;
import service.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    
    public static void main(String[] args) {
        System.out.println(" ");
        System.out.println("---=== Поехали! ===---");
        System.out.println(" ");
        
        // Создать Объект-менеджер который управляет всеми задачами
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        
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
    public static void createObjects(InMemoryTaskManager taskManager) {
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
    public static void showObjects(InMemoryTaskManager taskManager) {
        
        // Распечатайте списки эпиков, задач и подзадач через System.out.println(..).
        HashMap<Integer, Epic> epics = taskManager.getAllHashMapEpics();
        
        for (Map.Entry<Integer, Epic> entry : epics.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        HashMap<Integer, Task> tasks = taskManager.getAllHashMapTasks();
        
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        HashMap<Integer, Subtask> subtasks = taskManager.getAllHashMapSubtasks();
        
        for (Map.Entry<Integer, Subtask> entry : subtasks.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        System.out.println(" ");
    }
    
    // Изменить статусы созданных объектов
    public static void changeObjects(InMemoryTaskManager taskManager) {
        HashMap<Integer, Task> tasks = taskManager.getAllHashMapTasks();
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
        
        HashMap<Integer, Subtask> subtasks = taskManager.getAllHashMapSubtasks();
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
    public static void delObjects(InMemoryTaskManager taskManager) {
        // И, наконец, попробуйте удалить одну из задач и один из эпиков.
        HashMap<Integer, Task> tasks = taskManager.getAllHashMapTasks();
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
        
        HashMap<Integer, Epic> epics = taskManager.getAllHashMapEpics();
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
    
    // Вывести в консоль списки: Задач, Эпиков, Подзадач, Истории
    private static void printAllTasks(InMemoryTaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);
            
            for (Task task : manager.getAllSubtasksByEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }
        
        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
