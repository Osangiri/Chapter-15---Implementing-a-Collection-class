import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import java.lang.*;

/**
 * JUnit Tests for Chapter 15
 */

public class Ch15Test {
	
	
	private ArrayStack<Integer> emptyStack;
    private ArrayStack<Integer> singleStack;
    private ArrayStack<Integer> stack;

	/**
	 * Default constructor, not used. Included to make Javadoc happy.
	 */
	public Ch15Test(){
	}

	/**
	 * Reset the base data structures just in case
	 */
	@BeforeEach 
	void reset(){
		emptyStack = new ArrayStack<>();
        singleStack = new ArrayStack<>();
        singleStack.push(1);
        stack = new ArrayStack<>();
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }
	}
	
	
	/**
	 * Tests peek method
	 */
	 @Test
	 public void testPeek(){
		 assertThrows(NoSuchElementException.class, () -> emptyStack.peek(), "empty stack");
		 assertEquals(1, singleStack.peek(), "peek should only return 1 element in single stack");
		 assertEquals(5, stack.peek(), "Peek should return the top element in stack");
		 
	 }
	 /**
	 * Tests push method
	 */
	 @Test
	 public void testPush(){
		emptyStack.push(10);
		assertEquals(10, emptyStack.peek(), "After push, peek shouls return the pushed element"); 
		assertEquals(1, emptyStack.size(), "stack size should be 1 after push");
	 }
	 /**
	 * Tests pop method
	 */
	 @Test
	  public void testPop() {
        assertThrows(NoSuchElementException.class, () -> emptyStack.pop(), "Pop on empty stack should throw exception");
        assertEquals(1, singleStack.pop(), "Pop should return the only element in singleStack");
        assertEquals(0, singleStack.size(), "Stack size should be 0 after pop");
        assertEquals(5, stack.pop(), "Pop should return the top element in stack");
        assertEquals(4, stack.size(), "Stack size should be 4 after one pop");
    }
    

	 
	  /**
	 * Tests toString method
	 */
	 @Test
	 public void testtoString() {
		 assertEquals("[]", emptyStack.toString(), "Empty stack should be []");
		 assertEquals("[1]", singleStack.toString(), "Singlestack should be represented as [1]");
		 assertEquals("[1, 2, 3, 4, 5]", stack.toString(), "Stack should be represented as [1, 2, 3, 4, 5]");
		 
	 }
	  /**
	 * Tests Equals method
	 */
	 @Test
	 public void testEquals(){
		assertEquals(stack, stack); 
		 
	 }
	  /**
	 * Tests clear method
	 */
	 @Test
	 public void testclear(){
		 stack.clear();
		 assertTrue(stack.isEmpty(), "stack should be empty after clear");
		 assertEquals("[]", stack.toString(), "cleared stack should be respresented as []");
	 }



    @SuppressWarnings("unchecked")
	public class ArrayStack<E>{
		private E[] data;
		private int size;		
		private static final int DEFAULT = 10;
		public ArrayStack(){
			clear();
		}
		/**
		 * peek method
		 * @return the element at the top of stack
		 * @throws NoSuchElementException if stack is Empty
		 */
		public E peek() {
			if (isEmpty()) throw new NoSuchElementException();
			return data[size - 1];
		}
		/**
		 * push method
		 * pushes an element to stack top
		 * @param element the element to be pushed onto the stack
		 */
		public void push(E element) {
			if (data.length == size) realloc(size * 2);
			data[size++] = element;
		}
		/**
		 * pop method
		 * removes and returns the element at the stop of stack
		 * @return the element removed form the top of stack
		 * @throws NoSuchElementException if stack is Empty
		 */
		public E pop() {
			if (isEmpty()) throw new NoSuchElementException();
			E element = data[--size];
			data[size] = null; // Clear memory reference
			if (size > 0 && size == data.length / 4 && data.length / 2 >= DEFAULT)
				realloc(data.length / 2);
			return element;
		}
		/**
		 * realloc method
		 * Resizes the array to the specified new capacity.
		 * @param newCap the new array capacity 
		 */
		private void realloc(int newCap) {//no tester for realloc because it is private
			E[] temp = (E[]) new Object[newCap];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}
		/**
		 * clear method
		 * Removes all elements from stack and resets its capacity to the default value
		 */
		 public void clear() {
			data = (E[]) new Object[DEFAULT];
			size = 0;
		}
		/**
		 * toString method
		 * @return a string representation of the stack
		 */
		public String toString() {
			if (isEmpty())return "[]";
   
			String result = "[" + data[0];
			for (int i = 1; i < size; i++) {
				result += ", " + data[i];
			}
			result += "]";
			return result;
		}
		/**
		 * isEmpty methods checks if stack is Empty
		 * 
		 * @return true if stack is empty otherwise false
		 */
		public boolean isEmpty() {
			return size == 0;
		}
		/**
		 * Return the number of elements in the stack
		 * @return the number of elements in the stack 
		 */
		public int size() {
			return size;
		}
		/**
		 * equals method
		 * Compares the specified object with this stack for equality
		 * @param other the object to be compared for equality with stack
		 * @return True if the specified object is equal to this stack and otherwise False
		 */
		 public boolean equals(Object other) {
			if (other instanceof ArrayStack stack) {
				if (this.size != stack.size) return false;
				for (int i = 0; i < size; i++) {
					if (!this.data[i].equals(stack.data[i])) return false;
				}
				return true;
			}
			return false;
		}
		
		
		
	}

	 
}
