import java.util.Objects;

public class Task {
    private String name;        // Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description; // Описание, в котором раскрываются детали.
    private final int id;       // Уникальный идентификационный номер задачи, по которому её можно будет найти.
    private Status status;      // Статус, отображающий её прогресс. Вы будете выделять следующие этапы жизни задачи, используя enum.
    
    Task(int id) {
        this.id = id;
        this.name = "";
        this.description = "";
        this.status = Status.NEW;
    }
    
    Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    
    Task(Task task) {
        this.id = task.id;
        this.name = task.name;
        this.description = task.description;
        this.status = task.status;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Status getStatus() {
        return this.status;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    // Указывает, равен ли какой-то другой объект этому объекту.
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Task task = (Task)obj;
        return Objects.equals(this.name, task.name) &&
               Objects.equals(this.description, task.description) &&
               this.id == task.id &&
               Objects.equals(this.status, task.status);
    }
    
    // Возвращает значение хэш-кода для объекта.
    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }
}
