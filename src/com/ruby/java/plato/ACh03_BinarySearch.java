package com.ruby.java.plato;

import java.util.Arrays;
import java.util.Comparator;

// 3장 객체 배열 정렬 - binary search

/*
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* Arrays.sort(array, myComparator);
* Collections.sort(list, myComparator);
*/

// comparator 인터페이스를 사용하려 객체의 배열 정렬 및 이진 탐색 수행
// 배열을 정렬하고 이진 탐색을 통해 특정한 객체 찾는법을 학습 하는 것이 목표

class Fruit4 {
	String name;
	int price;
	String expire;

//	생성자
	public Fruit4(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}
	
//	가격을 반환하는 메서드
	public int getPrice() {
		return price;
	}
	
//	이름을 반환하는 메서드
	public String getName() {
		return name;
	}
}

//	Fruit4 객체를 이름으로 비교하는 comparator 구현
//	f1과 f2 비교하여 f1이 사전적으로 앞서면 음수, 뒤면 양수, 같으면 0 반환
//	숫자의 반환 통해 이름 기준 오름차순 정렬이 가능함
class FruitNameComparator2 implements Comparator<Fruit4>{
	public int compare(Fruit4 f1, Fruit4 f2) {
		return f1.getName().compareTo(f2.getName());
	}
}
	
public class ACh03_BinarySearch {

//	주어진 Comparator를 사용하여 배열을 정렬하는 메서드
	private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {
		Arrays.sort(arr, cc_price);
	}
	
//	두 배열의 요소를 교환하는 메서드
	static void swap(Fruit4[]arr, int ind1, int ind2) {
//		임시 변수에 ind1 위치 요소 저장
		Fruit4 temp = arr[ind1]; 
//		ind1 위치에 ind2 위치 요소 대입
		arr[ind1] = arr[ind2]; 
//		ind2 위치에 임시 변수에 저장한 ind1 위치의 배열 요소 대입
		arr[ind2] = temp;
	}
	
//	주어진 comparator를 사용하여 배열을 정렬하는 메서드 (버블 정렬)
	static void sortData(Fruit4 []arr, FruitNameComparator2 cc) {
//		배열의 각 요소 순회
		for (int i = 0; i < arr.length; i++) {
//			현재 요소 이후의 요소 순회
			for (int j = i+1; j < arr.length; j++) {
				if (cc.compare(arr[i],arr[j])> 0) { 
//					잘못된 순서 있을 경우 두 요소의 위치 교환
					swap(arr, i, j);
				}
			}
		}
	}

//	객체 배열을 출력하는 메서드
	static void showData(String topic, Fruit4[] arr) {
		System.out.println(topic);
		for (Fruit4 item : arr) {
			System.out.println(item.getName() + " " + item.getPrice() + " " + item.expire);
		}
	}
	
//	객체 배열을 역순으로 정렬하는 메서드
	static void reverse(Fruit4[] arr) {
//		배열의 총길이 저장
		int len = arr.length;
//		배열의 중간까지 반복 (배열의 양 끝 요소 교환하기에 중간까지만 반복해도 전체 배열 순서 뒤집을 수 있음)
		for (int i = 0; i < len / 2; i++) {
//			첫번째 요소와 마지막 요소를 교환, 두번쩨 요소와 마지막에서 두번째 요소를 교환, ~~~
			swap(arr, i, len - 1 -i);
		}
	}
	
//	이진 탐색을 구현하는 메서드
	static int binarySearch(Fruit4[] arr, Fruit4 key, Comparator<Fruit4> cc) {
//		탐색 범위에서 앞의 인덱스
		int low = 0;
//		탐색 범위에서 뒤의 인덱스
		int high = arr.length - 1;
//		탐색 범위가 유효한 동안 반복
		while (low <= high) {
//			중간 인덱스를 계산
			int mid = (low + high) / 2;
//			중간 값과 키 값을 비교
			int cmp = cc.compare(arr[mid], key);
//			중간 값 < 키 값이면 탐색 범위를 오른쪽 절반으로 좁힘
			if (cmp < 0) {
				low = mid + 1;
//			중간 값 > 키 값이면 탐색 범위 왼쪽 절반으로 좁힘	
			} else if (cmp < 0) {
				high = mid - 1;
//			중간 값 = 키 값이면 키 값의 인덱스를 반환
			} else {
				return mid;
			}
		}
//		키 값을 찾지 못한 경우 -1을 반환
		return -1;
	}
	
	public static void main(String[] args) {
//		객체 배열 생성 및 초기화
		Fruit4[] arr = {new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") 
		};
		
//		정렬 전 객체 배열 출력
		System.out.println("\n정렬 전 객체 배열: ");
		showData("정렬 전 객체", arr);
		
//		FruitNameComparator2 객체를 사용하여 배열을 이름 기준으로 정렬
		FruitNameComparator2 cc = new FruitNameComparator2();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) 정렬 후", arr);
		
//		reverse 메서드 통해 배열을 역순으로 정렬
		reverse(arr);
		showData("역순 재배치 후", arr);
		
//		sortData 메서드 통해 이름 기준으로 버블 정렬
		sortData(arr, cc);
		showData("sortData(arr,cc) 실행후", arr);
		
//		람다식 사용하여 가격 기준으로 정렬
//		두 개의 객체 a와 b 받아서 a의 가격에서 b의 가격 뺀 결과를 반환
		Comparator<Fruit4> cc_price2 = (a, b) -> a.getPrice() - b.getPrice();
		
//		람다식 변수 사용하여 정렬		
		Arrays.sort(arr, cc_price2);
		showData("람다식 변수 cc_price2을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
//		변수 사용하지 않고 람다식 직접 전달		
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // Fruit4에 compareTo()가 있어도 람다식 우선 적용
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

//		익명 클래스를 사용하여 이름 기준으로 정렬
//		익명 클래스 : 클래스 선언과 동시에 인스턴스 생성, 클래스 이름 없는게 특징
//		--> 간단한 인터페이스 구현에 주로 사용됨, 일회성 사용이 특징 (캡슐화, 가독성 우수)
		System.out.println("\n익명 클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a1, Fruit4 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("comparator 객체를 사용한 정렬:", arr);
	
//		익명 클래스를 사용하여 이름 기준으로 비교하는 comparator 구현
		Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				return (f1.name.compareTo(f2.name));
			}
		};
		
//		익명 클래스를 사용하여 가격 기준으로 비교하는 ~ 구현
		Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.getPrice() - f2.getPrice();
			}
		};

//		이진 탐색을 위한 새로운 Fruit4 객체 생성
		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		
//		Arrays.binarySearch 사용하여 이름 기준으로 이진 탐색
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
//		binarySearch 메서드 사용하여 이름 기준으로 이진 탐색
		result3Index = binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

//		sortData 메서드 사용하여 가격 기준으로 정렬
		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);
		
//		Arrays.binarySearch 사용하여 가격 기준 이진 탐색
		result3Index = Arrays.binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
//		binarySearch 메서드 ~ 가격 기준 이진 탐색
		result3Index = binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3Index);

	}
}
