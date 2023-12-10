package com.ssafy.enjoytrip.util;


import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.Member;

public class MemberSorting {
	private static final int RUN = 32;
	
	private static MemberSorting instance;
	MemberSorting() {}
	
	public static MemberSorting getInstance() {
		if(instance != null) {
			instance = new MemberSorting();
		}
		return instance;
	}
	
	//아이디 , 이름
	public static void insertionSort(List<Member> list, int left, int right, boolean desc) {
		for(int i= left+1; i <= right ; i++) {
			String temp = list.get(i).getUserId();
			Member tempDto = list.get(i);
			int j = i-1;
			
			int idx = 0;
			if(desc) idx = -1;
			else idx = 1;
			
			while(j >= left && (list.get(j).getUserId().compareTo(temp)) * idx > 0) {
				list.set(j+1, list.get(j));
				j--;
			}

			list.set(j+1, tempDto);
		}
	}
	
	public static void merge(List<Member> list, int l, int m, int r, boolean desc) {
		int len1= m - 1 + 1;
		int len2 = r-m;
		List<Member> left = new ArrayList<>();
		List<Member> right = new ArrayList<>();
		for(int i=l ; i < len1 ; i++) {
			left.add(list.get(i));
		}
		for(int i=m+1; i < len2 ; i++) {
			right.add(list.get(i));
		}
		
		int idx = 0;
		if(desc) idx = -1;
		else idx = 1;
		
		int i=0;
		int j=0;
		int k=l;
		
		while(i < len1 && j < len2) {
			if((left.get(i).getUserId().compareTo(right.get(j).getUserId()))*idx < 0) {
				list.set(k, left.get(i));
				i++;
			} else {
				list.set(k, right.get(j));
				j++;
			}
			k++;
		}
		
		while(i < len1) {
			list.set(k, left.get(i));
			k++;
			i++;
		}
		while(j < len2) {
			list.set(k, right.get(j));
			k++;
			j++;
		}
	}
	
	public static void nameInsertionSort(List<Member> list, int left, int right, boolean desc) {
		for(int i= left+1; i <= right ; i++) {
			String temp = list.get(i).getName();
			Member tempDto = list.get(i);
			int j = i-1;
			
			int idx = 0;
			if(desc) idx = -1;
			else idx = 1;
			
			while(j >= left && (list.get(j).getName().compareTo(temp)) * idx > 0) {
				list.set(j+1, list.get(j));
				j--;
			}

			list.set(j+1, tempDto);
		}
	}
	
	public static void nameMerge(List<Member> list, int l, int m, int r, boolean desc) {
		int len1= m - 1 + 1;
		int len2 = r-m;
		List<Member> left = new ArrayList<>();
		List<Member> right = new ArrayList<>();
		for(int i=l ; i < len1 ; i++) {
			left.add(list.get(i));
		}
		for(int i=m+1; i < len2 ; i++) {
			right.add(list.get(i));
		}
		
		int idx = 0;
		if(desc) idx = -1;
		else idx = 1;
		
		int i=0;
		int j=0;
		int k=l;
		
		while(i < len1 && j < len2) {
			if((left.get(i).getName().compareTo(right.get(j).getName()))*idx < 0) {
				list.set(k, left.get(i));
				i++;
			} else {
				list.set(k, right.get(j));
				j++;
			}
			k++;
		}
		
		while(i < len1) {
			list.set(k, left.get(i));
			k++;
			i++;
		}
		while(j < len2) {
			list.set(k, right.get(j));
			k++;
			j++;
		}
	}
	
	public static void idTimSort(List<Member> list, int n, boolean desc) {
		for(int i=0 ; i < n ; i+= RUN) {
			insertionSort(list, i , Math.min(i+RUN -1, n-1), desc);
		}
		
		for(int size= RUN; size <n ; size= 2*size) {
			for(int left = 0 ; left< n ; left+=2 * size) {
				int mid = left+ size -1;
				int right = Math.min(left+2*size-1, n-1);
				merge(list,left,mid,right,desc);
			}
		}
	}
	
	public static void nameTimSort(List<Member> list, int n, boolean desc) {
		for(int i=0 ; i < n ; i+= RUN) {
			nameInsertionSort(list, i , Math.min(i+RUN -1, n-1), desc);
		}
		
		for(int size= RUN; size <n ; size= 2*size) {
			for(int left = 0 ; left< n ; left+=2 * size) {
				int mid = left+ size -1;
				int right = Math.min(left+2*size-1, n-1);
				nameMerge(list,left,mid,right,desc);
			}
		}
	}
}
