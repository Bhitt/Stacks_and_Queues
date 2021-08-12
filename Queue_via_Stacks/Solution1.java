/* 
	Solution 1 
	
	We will use a second stack to reverse the order of items from the first stack. This will give us
	the oldest item on the top. We can leave the ordering reversed until we need to swap it back in
	case there are multiple pop() oldest in a row.
*/


/* 
	Time Complexity: 
	Space Complexity: 
*/

public class MyQueue<T> {
	Stack<T> stackNewest, stackOldest;

	public MyQueue(){
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}

	public int size(){
		return stackNewest.size() + stackOldest.size();
	}

	public void add(T value){
		/* push onto stacknewest, which always has the newest elements on top */
		stackNewest.push(value);
	}

	/* move elements from stackNewest into stackOldest. This is usually done so that we can do
	 * operations on stackOldest */
	private void shiftStacks(){
		if(stackOldest.isEmpty()){
			while(!stackNewest.isEmpty()){
				stackOldest.push(stackNewest.pop());
			}
		}
	}

	public T peek(){
		shiftStacks(); // Ensure stackOldest has the current elements
		return stackOldest.peek(); // retrieve the oldest item
	}

	public T remove(){
		shiftStacks(); // Ensure stackOldest has the current elements
		return stackOldest.pop(); // pop the oldest item
	}
}