/* 
	Solution 1 
	
	Have each node record what the minimum beneath itself is. Then, to find the min, you just look at
	what the top element thinks the min is.

	Note: This solution wastes a lot of space by keeping track of the min for every element. Another
	solution is to keep a second stack of just the mins.
*/


/* 
	Time Complexity: O(1)
	Space Complexity: 
*/

public class StackWithMin extends Stack<NodeWithMin> {
	public void push(int value){
		int newMin = Math.min(value, min());
		super.push(new NodeWithMin(value, newMin));
	}

	public int min(){
		if(this.isEmpty()){
			return Integer.MAX_VALUE; // error value
		} else {
			return peek().min;
		}
	}
}

public class NodeWithMin {
	public int value;
	public int min;
	public NodeWithMin(int v, int min){
		value = v;
		this.min = min;
	}
}