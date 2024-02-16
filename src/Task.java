import java.util.Objects;

public class Task {
    protected TypeTask typeTask;// Тип Объекта
    private String name;        // Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description; // Описание, в котором раскрываются детали.
    private final int id;       // Уникальный идентификационный номер задачи, по которому её можно будет найти.
    private Status status;      // Статус, отображающий её прогресс. Вы будете выделять следующие этапы жизни задачи, используя enum.
    
    Task(int id) {
        this.typeTask = TypeTask.TASK;
        this.id = id;
        this.name = "";
        this.description = "";
        this.status = Status.NEW;
    }
    
    Task(int id, String name, String description, Status status) {
        this.typeTask = TypeTask.TASK;
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    
    Task(Task task) {
        this.typeTask = TypeTask.TASK;
        this.id = task.id;
        this.name = task.name;
        this.description = task.description;
        this.status = task.status;
    }
    
    public TypeTask getTypeTask() {
        return this.typeTask;
    }
    
    // Получить Имя Задачи
    public String getName() {
        return this.name;
    }
    
    // Получить Описание Задачи
    public String getDescription() {
        return this.description;
    }
    
    // Получить id Задачи
    public int getId() {
        return this.id;
    }
    
    // Получить Статус Задачи
    public Status getStatus() {
        return this.status;
    }
    
    // Установить Тип Обекта
    public void setTypeTask(TypeTask typeTask) {
        this.typeTask = typeTask;
    }
    
    // Установить Имя Задачи
    public void setName(String name) {
        this.name = name;
    }
    
    // Установить Описание Задачи
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Установить Статус Задачи
    public void setStatus(Status status) {
        this.status = status;
    }
    
    // Сравнить с другим объектом
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task task = (Task)obj;
        return Objects.equals(this.typeTask, task.typeTask) &&
               Objects.equals(this.name, task.name) &&
               Objects.equals(this.description, task.description) &&
               this.id == task.id &&
               Objects.equals(this.status, task.status);
    }
    
    // Получить значение хэш-кода для объекта
    @Override
    public int hashCode() {
        return Objects.hash(this.typeTask, this.name, this.description, this.id, this.status);
    }

    // Получить строковое представление
    @Override
    public String toString() {
        return "typeTask=" + typeTask.toString() + ",name='" + this.name + "',description='" + this.description + "',id=" + this.id + ",status=" + this.status;
    }
}
