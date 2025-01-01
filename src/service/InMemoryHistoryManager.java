package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
	private final Map<Integer, Node<Task>> shortcut;
	public Node<Task> head;
	public Node<Task> tail;
	private int size = 0;

	InMemoryHistoryManager() {
		this.shortcut = new HashMap<>();
		this.head = null;
		this.tail = null;
	}

	//
	public int getSize() {
		return this.size;
	}

	// Добавить Задачу в конец списка
	public void linkLast(Task task) {
		Node<Task> node = new Node<>(task);
		node.prev = this.tail;
		this.tail = node;
		this.size++;
		this.shortcut.put(task.getId(), node);
	}

	// Получить список Задач
	public List<Task> getTasks(int count) {
		List<Task> list = new ArrayList<>();
		Node<Task> node = this.head;
		if (count >= this.getSize()) {
			while (node != null) {
				list.add(node.data);
				node = node.next;
			}
		} else {
			int countNode = 0;
			while (node != null) {
				list.add(node.data);
				node = node.next;
				if (countNode > count) node = null;
				countNode++;
			}
		}
		return list;
	}

	// Удалить Узел связного списка
	public void removeNode(Node<Task> node) {
		if (node.prev.next != null) {
			node.prev.next = node.next;
		} else {
			this.head = node.next;
		}

		if (node.next.prev != null) {
			node.next.prev = node.prev;
		} else {
			this.tail = node.prev;
		}

		this.shortcut.remove(node.data.getId());

		this.size--;
	}

	// Получить Список последних 10 просмотренных Задач
	@Override
	public List<Task> getHistory() {
		return this.getTasks(10);
	}
	
	// Добавить Задачу в Историю
	@Override
	public void setHistory(Task task) {
		this.add(task);
	}

	// Удалить Задачу из Истории
	@Override
	public void remove(int id) {
		if (shortcut.containsKey(id)) {
			Node<Task> node = this.shortcut.get(id);
			this.removeNode(node);
			this.shortcut.remove(id);
		}
	}

	// Добавить Задачу в конец списка
	public void add(Task task) {
		if (this.shortcut.containsKey(task.getId())) {
			this.remove(task.getId());
		}
		this.linkLast(task);
	}
}
