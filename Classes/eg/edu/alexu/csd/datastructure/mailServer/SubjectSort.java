package eg.edu.alexu.csd.datastructure.mailServer;

import java.util.Stack;

public class SubjectSort implements ISort{
	@Override
	public void applySort(SLinkedList list) { //LinkedList of info
	    	
		if (list == null || list.size() == 0)
			return;
		
        int low = 0;
        int high = list.size()-1;
        String pivot = list.get( (low+high)/2 );
        Stack<Object> s = new Stack<Object>();
        s.push(low);
        s.push(pivot);
        s.push(high);
            
        
        while(low < high && !s.isEmpty()){
            
            high = (int) s.pop();
            pivot = (String) s.pop();
            low = (int) s.pop();
            
            int i = low , j = high;
            while ( ((Info)list.get(i)).subject.compareTo(pivot) < 0) { 
				i++;
			}
 
			while (((Info)list.get(j)).subject.compareTo(pivot) > 0) {
				j--;
			}
 
			if (i <= j) {
				Object temp = list.get(i);
				list.set(i,list.get(j));
				list.set(j,temp);
				i++;
				j--;
			}
			
			//low >> j , i >> high
			if (low < j) {
				s.push(low);
				s.push(list.get( (low+j)/2 ));
				s.push(j);
			}
			if (high > i) {
				s.push(i);
				s.push(list.get( (i+high)/2 ));
				s.push(high);
			}
        }
	}
}