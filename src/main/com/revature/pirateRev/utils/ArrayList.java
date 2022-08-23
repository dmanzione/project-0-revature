package com.revature.pirateRev.utils;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<T> implements Iterable<T> {
	private int length;
	private int size;
	private int currentLastIndex;
	private T[] backingArray;

	@SuppressWarnings("unchecked")
	public ArrayList() {

		this.length = 0;
		this.size = 10;
		this.currentLastIndex = 0;
		this.backingArray = (T[]) new Object[10];

	}

	public ArrayList(T[] arr) {
		this.backingArray = arr;
		this.length = arr.length;
		this.size = (arr.length > 10) ? (arr.length + (arr.length / 2)) : 10;
		this.currentLastIndex = arr.length - 1;
	}

	public void add(T data) {

		backingArray[currentLastIndex] = data;
		length++;
		currentLastIndex++;

		if (currentLastIndex == size - 1) {
			backingArray = resize(backingArray);
		}
	}

	public T getElementAtIndex(int index) {
		if (backingArray[index] == null) {
			throw new NoSuchElementException("There are no elements at that index!");
		} else {
			return backingArray[index];
		}

	}

	public Object[] getAllElements() {

		return backingArray;

	}

	public int getLength() {
		return length;
	}

	private T[] resize(T[] oldArray) {

		T[] newArray = Arrays.copyOf(oldArray, size + size / 2);

		size = newArray.length;

		return newArray;
	}

	public void print() {
		
		for (int i = 0; i < backingArray.length; i++) {
			if (backingArray[i] == null)
				break;
			System.out.println("\n\n   " + (i + 1) + ") " + backingArray[i]);
		}
	}

	public boolean isEmpty() {
		return currentLastIndex == 0;
	}

	@Override
	public Iterator<T> iterator() {

		return new ArrayListIterator();
	}

	class ArrayListIterator implements Iterator {
		int index = 0;
		T data;

		@Override
		public boolean hasNext() {
			return (index <= backingArray.length - 1 && backingArray[index] != null);

		}

		@Override
		public T next() {
			if (!hasNext())
				return null;
			data = backingArray[index];
			index++;
			return data;
		}

	}

}
