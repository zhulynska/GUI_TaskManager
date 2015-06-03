package ua.edu.sumdu.j2se.E_Zhulynska.lab1.model;

//package ua.edu.sumdu.j2se.Zhulynska.pr7;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Observer;

/**
 * @author Zhulynska This class describes the massive of tasks
 */
public class ArrayTaskList extends TaskList implements Iterable<Task>,
		Serializable {

	private Task[] massTask;
	private int count;

	/**
	 * ArrayTaskList() constructs new task
	 */
	public ArrayTaskList() {
		count = 0;
		massTask = new Task[10];
	}

	/**
	 * @return Task count
	 */
	public int size() {
		return count;
	}

	/**
	 * Add new element
	 * 
	 * @throws add
	 *             null task
	 */
	public void add(Task task) {
		if (task == null)
			throw new NullPointerException("task is null");
		// создаем массив большего размера для записи задач
		Task massNew[] = new Task[massTask.length + 10];
		// переганяем задачи в новый массив
		for (int i = 0; i < size(); i++) {
			massNew[i] = massTask[i];
		}
		// переприсваиваем массив обратно, чтобы он не занимал место
		massNew[size()] = task;
		massTask = massNew;
		// massTask[size()+1] = task;
		count++;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return Remove the element
	 * @throws remove
	 *             null task
	 */
	public void remove(Task task) {
		if (task == null)
			throw new NullPointerException("task is null");
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				if (massTask[i].equals(task)) {
					// создаем новый массив, в кот. переганяем оставшиеся после
					// удаления элементы
					Task massNew[] = new Task[massTask.length];
					for (int j = 0; j < massTask.length; j++) {
						// если элементы находятся перед удаляемой задачей, то
						// их просто переприсваивают.
						if (j < i)
							massNew[j] = massTask[j];
						// если элементы находятся после удаляемой задачи, то их
						// нужно переприсвоить со здвигом индекса.
						if (j > i)
							massNew[j - 1] = massTask[j];
					}
					massTask = massNew;
					count--;
					i--;
				}
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * @return The task with current index
	 * @throws incorrect
	 *             index
	 */
	/*
	 * public Task getTask(int index) { if ((index < 0) || (index >= size()))
	 * throw new IndexOutOfBoundsException("incorrect index!"); return
	 * massTask[index]; }
	 */

	/**
	 * @return a deep clone of element
	 */

	@Override
	public ArrayTaskList clone() {
		try {
			ArrayTaskList cloned = (ArrayTaskList) super.clone();
			cloned.massTask = (Task[]) massTask.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new Error(e.getMessage());

		}

	}

	/**
	 * @return Return the iterator, realized in LinkedTaskListIterator class
	 */
	public Iterator<Task> iterator() {
		return new ArrayIterator(this);
	}

	/**
	 * @author Zhulynska This class describes the Iterator methods
	 */
	public class ArrayIterator implements Iterator<Task> {
		private ArrayTaskList atl;
		private int index;
		private boolean nextCalled = false;

		/**
		 * @return construct the element of ArrayIterator class, based on
		 *         ArrayTaskList class
		 */
		public ArrayIterator(ArrayTaskList object) {
			atl = object;
		}

		/**
		 * @return Return true if index < size()
		 */
		public boolean hasNext() {
			if (index < atl.size())
				return true;
			else
				return false;
		}

		/**
		 * @return Return next element of collection when hasNext() = true
		 */
		public Task next() {
			if (!hasNext())
				throw new NoSuchElementException("next_End is reached");
			nextCalled = true;
			Task t = atl.massTask[index];
			index++;
			return t;
		}

		/**
		 * @return remove the element, called before
		 */

		public void remove() {
			if (nextCalled == true) {
				atl.remove(atl.massTask[index - 1]);
				nextCalled = false;
				setChanged();
				notifyObservers();
			} else
				throw new IllegalStateException("Next element wasn't called");
		}
	}

}
