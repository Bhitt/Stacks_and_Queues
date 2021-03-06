/* 
	Solution 1 
	
	We know that push() should behave identically to a single stack, which means that we need push()
	to call push() on the last stack in the array of stacks. We have to be a bit careful: if the last
	stack is at capacity, we beed to create a new stack.

	Pop() should behave similarly to push() in that it should operate on the last stack. If the last
	stack is empty after pop(), then we should remove the stack from the list of stacks.

	For the follow up, we can imagine a rollover system. If we pop an element from stack 1, we need
	to remove the bottom of stack 2 and push it onto stack 1. We then need to rollover from stack 3
	to stack 2, stack 4 to stack 3, etc. 
*/


/* 
	Time Complexity: 
	Space Complexity: 
*/

public class SetOfStacks {
	ArrayList<Stack> stacks = new ArrayList<Stack>();
	public int capacity;
	public SetOfStacks(int capacity){
		this.capacity = capacity;
	}

	public Stack getLastStack(){
		if(stacks.size() == 0) return null;
		return stacks.get(stacks.size() - 1);
	}

	public void push(int v){
		Stack last = getLastStack();
		if(last != null && !last.isFull()){ // add to last stack
			last.push(v);
		} else { // must create a new stack
			Stack stack = new Stack(capacity);
			stack.push(v);
			stacks.add(stack);
		}
	}

	public int pop(){
		Stack last = getLastStack();
		if(last == null) throw new EmptyStackException();
		int v = last.pop();
		if(last.size == 0) stacks.remove(stacks.size() - 1);
		return v;
	}

	public boolean isEmpty(){
		Stack last = getLastStack();
		return last == null || last.isEmpty();
	}

	public int popAt(int index){
		return leftShift(index, true);
	}

	public int leftShift(int index, boolean removeTop){
		Stack stack = stacks.get(index);
		int removed_item;
		if(removeTop) removed_item = stack.pop();
		else removed_item = stack.removeBottom();
		if(stack.isEmpty()){
			stacks.remove(index);
		} else if(stacks.size() > index + 1){
			int v = leftShift(index + 1, false);
			stack.push(v);
		}
		return removed_item;
	}
}

public class Stack {
	private int capacity;
	public Node top, bottom;
	public int size = 0;

	public Stack(int capacity) {this.capacity = capacity; }
	public boolean isFull() {return capacity == size; }

	public void join(Node above, Node below){
		if(below != null) below.above = above;
		if(above != null) above.below = below;
	}

	public boolean push(int v){
		if(size >= capacity) return false;
		size++;
		Node n = new Node(v);
		if(size == 1) bottom = n;
		join(n, top);
		top = n;
		return true;
	}

	public int pop(){
		Node t = top;
		top = top.below;
		size--;
		return t.value;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int removeBottom(){
		Node b = bottom;
		bottom = bottom.above;
		if(bottom != null) bottom.below = null;
		sizee--;
		return b.value;
	}
}