package tira.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayListStackTest {
    
    @Test
    public void freshStackShouldBeEmpty() {
        ArrayListStack<Integer> stack = new ArrayListStack<>();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void pushShouldIncrementSize() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayListStack<Integer> stack = new ArrayListStack<>(list);
        stack.push(1);
        assertEquals(1, list.size());
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void pushShouldAddToUnderlyingList() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayListStack<Integer> stack = new ArrayListStack<>(list);
        Integer one = 1;
        stack.push(one);
        assertTrue(list.contains(one));
    }
    
    @Test
    public void popShouldReturnPushedElement() {
        ArrayListStack<Integer> stack = new ArrayListStack<>();
        Integer one = 1;
        stack.push(one);
        Integer popped = stack.pop();
        assertEquals(one, popped);
    }
    
    @Test
    public void popShouldDecrementSize() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[] { 1 });
        ArrayListStack<Integer> stack = new ArrayListStack<>(list);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

}
