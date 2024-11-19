package inventorysystemproject;

import java.util.Iterator;

public class ListHashTable<K, V> implements HashTableInterface<K, V> {
	private AList<K> keys; // Array List ADT for keys
	private AList<V> values; // Array List ADT for values
	private int numberOfEntries;
	private int size;

	private final static int DEFAULT_CAPACITY = 1000; // Default capacity
	private static final int MAX_CAPACITY = 10000;
	private static final double LOAD_FACTOR_THRESHOLD = 0.75; // Load factor before resizing

	public ListHashTable() {
		this(DEFAULT_CAPACITY); // Call next constructor
	} // end default constructor

	public ListHashTable(int initialCapacity) {
		// Create lists with the initial capacity
		this.keys = new AList<K>(initialCapacity);
		this.values = new AList<V>(initialCapacity);
		size = initialCapacity;
		numberOfEntries = 0;
		for (int i = 1; i <= initialCapacity; i++) {
			keys.add(i, null);
			values.add(i, null);
		}
	} // end constructor

	public int hash(K key) {
		int hashCode = key.hashCode(); // Get the hash code of the key
		return Math.abs(hashCode % size); // Use modulo operation to ensure it fits within the table size
	}

	/** Precondition: key and value are not null. */
	public V add(K key, V value) {
		if ((double) numberOfEntries / size > LOAD_FACTOR_THRESHOLD) {
			resize(); // Resize if load factor exceeds the threshold
		}

		int index = hash(key); // Get the index for the key

		if (keys.getEntry(index + 1) != null && keys.getEntry(index + 1).equals(key)) {
			// If the key already exists, update the value
			V oldValue = values.getEntry(index + 1);
			values.replace(index + 1, value);
			return oldValue;
		} else {
			// Otherwise, add a new key-value pair
			keys.replace(index + 1, key);
			values.replace(index + 1, value);
			numberOfEntries++;
			return null;
		}
	}

	public V remove(K key) {
		int index = hash(key); // Get the index for the key
		if (keys.getEntry(index + 1) != null && keys.getEntry(index + 1).equals(key)) {
			V removedValue = values.getEntry(index + 1);
			keys.replace(index + 1, null);
			values.replace(index + 1, null);
			numberOfEntries--;
			return removedValue;
		}
		return null; // Key not found
	}

	public V getValue(K key) {
		int index = hash(key); // Get the index for the key
		if (keys.getEntry(index + 1) != null && keys.getEntry(index + 1).equals(key)) {
			return values.getEntry(index + 1);
		}
		return null; // Key not found
	}

	public boolean contains(K key) {
		int index = hash(key);
		return keys.getEntry(index + 1) != null && keys.getEntry(index + 1).equals(key);
	}

	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	public int getSize() {
		return numberOfEntries;
	}

	public final void clear() {
		for (int i = 1; i <= size; i++) {
			keys.replace(i, null);
			values.replace(i, null);
		}
		numberOfEntries = 0;
	}

	public Iterator<K> getKeyIterator() {
		return keys.getIterator();
	}

	public Iterator<V> getValueIterator() {
		return values.getIterator();
	}

	// Method to resize the table
	private void resize() {
		int newCapacity = size * 2; // Double the capacity
		if (newCapacity > MAX_CAPACITY) {
			newCapacity = MAX_CAPACITY; // Limit to max capacity
		}

		AList<K> newKeys = new AList<>(newCapacity);
		AList<V> newValues = new AList<>(newCapacity);

		// Initialize new lists
		for (int i = 1; i <= newCapacity; i++) {
			newKeys.add(i, null);
			newValues.add(i, null);
		}

		// Rehash and copy old entries into the new table
		for (int i = 1; i <= size; i++) {
			K oldKey = keys.getEntry(i);
			V oldValue = values.getEntry(i);
			if (oldKey != null) {
				int newIndex = Math.abs(oldKey.hashCode() % newCapacity);
				newKeys.replace(newIndex + 1, oldKey);
				newValues.replace(newIndex + 1, oldValue);
			}
		}

		// Replace old tables with new ones
		keys = newKeys;
		values = newValues;
		size = newCapacity;
	}

	// Method to print all entries in the hash table
	public void printTable() {
		System.out.println("Hash Table Entries:");
		for (int i = 1; i <= size; i++) {
			K key = keys.getEntry(i);
			V value = values.getEntry(i);
			if (key != null) {
				System.out.println("Index " + (i - 1) + ": Key = " + key + ", Value = " + value);
			}
		}
	}
}
