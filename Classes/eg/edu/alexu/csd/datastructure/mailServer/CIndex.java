package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;

public class CIndex extends Index{
     
	public CIndex(File path,boolean isNew) throws FileNotFoundException {
		super(path,isNew);
	}
	
	public ILinkedList readIndex(){
	    try(Scanner reader = new Scanner(super.getPath())){ 
	        while (reader.hasNextLine()){ 
	            CInfo item = new CInfo();
	            item.stringToInfo(reader.nextLine());
	            list.add(item);
	        }
	        return list;
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	
	public void writeToIndex(){
		try(PrintWriter writer = new PrintWriter(getPath())){
			for(Object o : list) {
			    CInfo item = (CInfo)o;
			    writer.println(item.infoToString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(Object item) {
		if(item == null || !(item instanceof Contact)){
	        throw new RuntimeException();
	    }
        CInfo i = new CInfo();
        i.name = ((Contact)item).getName(); // string
        i.directory = ((Contact) item).getPath().getAbsolutePath();
        list.add(i);
		size++;
	}
	
	public Object remove(Object o) {    //remove it from the linked list  //we only need contact name
	    if( !(o instanceof String) || o == null || o.length() ==0 ){
	        throw new RuntimeException();
	    }else{
		    int found = (Integer)find(o);
		    if(found != -1) {
		        size--;
		        CInfo temp = (CInfo)list.get(found);
		        list.remove(found);
		        return temp;
		    }
		    return null;
	    }
	}


	public Object find(Object o) {     //find it in the linked list by name  //return index of the found element , -1 if not found
	    if( !(o instanceof String) || o == null || ((File) o).length() == 0 ){
	        throw new RuntimeException();
	    }
	    int middle, high, low;
		boolean found = false;
		Stack stack = new Stack();
		stack.push(0);
		stack.push(list.size()-1);
		while(!found) {
			high = stack.pop();
			low = stack.pop();
			if(high < low) {
				stack.push(-1);
				break;
			}
			middle = (high + low)/2;
			if(list.get(middle).equals(o)) {
				stack.push(middle);
				found = true;
			}else if(list.get(middle).compareTo(o) > 0) { //sorted by ??
				stack.push(low);
				stack.push(middle-1);
			}else {
				stack.push(middle+1);
				stack.push(high);
			}
		}
		return stack.pop();
	}

}
