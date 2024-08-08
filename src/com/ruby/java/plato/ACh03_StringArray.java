package com.ruby.java.plato;

import java.util.Arrays;
import java.util.List;

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */

// 두 개의 스트링 배열을 합병하고 결과를 출력하는 것이 목표
public class ACh03_StringArray {
	
//	배열의 내용을 출력하는 메서드
	 static void showList(String topic, String [] list) {
		 System.out.println(topic);
		 for (String item : list) {
			 System.out.println(item + " ");
		 }
		 System.out.println();
	    }
	 
//	 두 개의 정렬된 스트링 배열을 합병하는 메서드
	    static String[] mergeList(String[]s1, String[] s2) {
	    	int i = 0, j = 0, k =0;
//	    	합병할 배열의 크기는 두 배열의 크기의 합
	    	String[] s3 = new String[s1.length + s2.length];
	    	
//	    	두 배열을 하나의 배열로 합병
	    	while (i < s1.length && j < s2.length) {
	    		if (s1[i].compareTo(s2[j]) <= 0) {
	    			s3[k++] = s1[i++];
	    		} else {
	    			s3[k++] = s2[j++];
	    		}
	    	}
	    	
//	    	s1 배열의 남은 요소를 s3 배열에 복사
//	    	남은 요소를 복사하는 과정이 없으면 s1 또는 s2의 요소들이 s3에 포함되지 않고 누락 될 수 있음
//	    	두 배열 모두 누락되지 않고 포함되는 것을 보장하기 위해 둘 다 복사
	    	while (i < s1.length) {
	    		s3[k++] = s1[i++];
	    	}
	    	
//	    	s2 배열의 남은 요소를 s3 배열에 복사
	    	while (j < s2.length) {
	    		s3[k++] = s2[j++];
	    	}
	    	
//	    	합병된 배열 반환
	    	return s3;
	    }
	    
	    public static void main(String[] args) {
		String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신" };
		String[] s2 = { "독도", "울릉도", "한산도", "영도", "우도" };
		
//		두 배열을 정렬
		Arrays.sort(s1);
		Arrays.sort(s2);
		
//		각 배열을 출력
		showList("s1배열 = ", s1);
		showList("s2배열 = ", s2);

//		합병된 배열을 출력
		String[] s3 = mergeList(s1,s2);
		showList("스트링 배열 s3 = s1 + s2:: ", s3);
	}
}
