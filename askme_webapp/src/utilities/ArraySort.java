package utilities;

import java.util.ArrayList;

public class ArraySort {
//	public static void main (String args[]){
//		ArrayList<Integer> num = new ArrayList<Integer>();
//			num.add(10);
//			num.add(30);
//			num.add(50);
//			num.add(20);
//			selectionSort(num);
//			for(int i =0 ; i<num.size();i++){
//
//				System.out.println(num.get(i));	
//			}
//	}
	public static void selectionSort (ArrayList<Integer> num){
		int min, temp;
		for(int index = 0; index<num.size()-1; index++){
			min=index;
			for(int scan=index+1; scan<num.size();scan++){
				if(num.get(scan) < num.get(min)){
					min=scan;					
				}
				temp=num.get(min);
				num.set(min, num.get(index));
				num.set(index, temp);
			}
		}
	}
}
