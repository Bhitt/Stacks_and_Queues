/* 
	Solution 1 
	
	We will use a sorting method similar to insertion sort, that uses the second stack to maintain a
	sorted array and a temporary variable to hold the value we want to sort. We can swap elements onto
	the original stack as a buffer until we reach the spot that the item should be placed in.

	Note: If we had access to more than two stacks, we could implement a better sorting method such
	as quicksort or mergesort.
*/


/* 
	Time Complexity: O(N^2)
	Space Complexity: O(N)
*/

void sort(Stack<Integer> s){
	Stack<Integer> r = new Stack<Integer>();
	while(!s.isEmpty()){
		/* insert each element in s in sorted order into r. */
		int tmp = s.pop();
		while(!r.isEmpty() && r.peek() > tmp){
			s.push(r.pop());
		}
		r.push(tmp);
	}

	/* copy the elements from r back into s */
	while(!r.isEmpty()){
		s.push(r.pop());
	}
}