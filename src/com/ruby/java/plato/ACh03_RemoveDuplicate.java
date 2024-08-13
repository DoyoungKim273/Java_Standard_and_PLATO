package com.ruby.java.plato;

// 파일에 바이트 스트림으로 데이터를 출력하기 위한 클래스
import java.io.FileOutputStream;
// 입출력 작업 중 발생할 수 있는 예외를 처리하기 위한 클래스
import java.io.IOException;
// 바이트 배열을 효율적으로 읽고 쓰기 위한 버퍼 클래스, 데이터를 임시로 저장하는데 사용
import java.nio.ByteBuffer;
// 파일에 대한 읽기 / 쓰기 작업을 수행하느 채널 클래스, NIO(File I/O)에서 사용
import java.nio.channels.FileChannel;
// 파일 및 디렉토리 조작을 위한 유틸리티 클래스
import java.nio.file.Files;
// 파일 시스템 내의 경로를 표현하는 인터페이스
import java.nio.file.Path;
// 문자열 경로를 Path 객체로 변환하는 유틸리티 클래스 
import java.nio.file.Paths;
// 동적 배열을 제공하는 클래스, 배열의 크기 동적 조정 가능, 데이터의 삽입 및 삭제가 용이
import java.util.ArrayList;
// 배열 조작 위한 유틸리티 클래스 
import java.util.Arrays;
// 리스트, 세트, 맵 등 다양한 컬렉션 조작할 수 있는 메서드 제공하는 클래스
import java.util.Collections;
// ArrayList와 같은 리스트 컬렉션을 다룰 수 있는 인터페이스
import java.util.List;

// 중복이 없는 리스트를 merge하는 버젼
public class ACh03_RemoveDuplicate {
	// string 정렬, binary search 구현
	// 1단계: string, 2단계: 객체,  Person 객체들의 list
	// file1: 서울, 북경, 상해, 서울, 도쿄, 뉴욕, 부산
	// file2: 런던, 로마, 방콕, 도쿄, 서울, 부산
	// file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장


		
		static int binSearch(String[] s, int n, String key) {
//			검색 범위의 하한
			int low = 0;
//			검색 범위의 상한
			int high = n - 1;
//			결과를 저장할 변수, 초기값은 -1 (찾지 못했을 경우)
			int result = -1;
			
//			이진 검색 수행
			while (low <= high) {
//				중간 인덱스 계삭
				int mid = (low + high) / 2;
				
				if (s[mid].compareTo(key) == 0) {
//					키와 일치하는 요소를 찾은 경우 일치하는 인덱스를 저장
					result = mid;
//					맨 앞의 요소를 찾기 위해 왼쪽 부분도 계속 탐색
					high = mid - 1;
				} else if (s[mid].compareTo(key) < 0) {
//					키가 배열의 중간 요소보다 크다면, 오른쪽을 탐색
					low = mid + 1;
				} else {
//					키가 배열의 중간 요소보다 작다면, 왼쪽을 탐색
					high = mid - 1;
				}
			}
//			맨 앞의 요소의 인덱스 반환, 없으면 -1 반환
			return result;
		}

//		리스트에서 중복 제거, 리스트 정렬한 후 이 함수가 호출됨
		static ArrayList<String> removeDuplicate(ArrayList<String> al) {			
//			결과를 담을 리스트
			ArrayList<String> list1 = new ArrayList<>();
			
			if (al.size() > 0) {
//				첫번째 요소는 무조건 추가
				list1.add(al.get(0));
			}

//			이전의 요소와 현재의 요소를 비교하여 중복이 아닌 경우만 리스트에 추가
			for (int i = 1; i < al.size(); i++) {
				if (!al.get(i).equals(al.get(i - 1))) {
					list1.add(al.get(i));
				}
			}
//			중복이 제거된 리스트를 반환
			return list1;
		}

//		두 리스트를 병합하여 정렬된 중복 없는 리스트를 반환하는 메서드		
		static List<String> mergeList(List<String> list1, List<String> list2) {			
//			list3 = merge(list1, list2);
//			새로운 리스트에 정렬 값 순서로 merge 하는 알고리즘
			ArrayList<String> list3 = new ArrayList<>();
//			리스트1과 리스트2의 인덱스를 추적할 변수
			int i = 0, j = 0;
			
//			두 리스트를 비교하면서 작은 값을 list3에 추가하고 인덱스 증가
			while (i < list1.size() && j < list2.size()) {
				if (list1.get(i).compareTo(list2.get(j)) < 0) {
					list3.add(list1.get(i));
					i++;
				} else if (list1.get(i).compareTo(list2.get(j)) > 0){
					list3.add(list2.get(j));
					j++;
				} else {
//					중복된 값이 있으면 하나만 추가하고, 두 리스트의 인덱스를 모두 증가시킴
					list3.add(list1.get(i));
					i++;
					j++;
				}
			}
			
//			리스트1에 남아있는 요소들을 리스트3에 추가
			while (i < list1.size()) {
				list3.add(list1.get(i));
				i++;
			}
			
//			리스트2에 남아있는 요소들을 리스트3에 추가
			while (j < list2.size()) {
				list3.add(list2.get(j));
				j++;
			}
			
//			병합된 리스트 반환
			return list3;			
		}
		
//		배열을 출력하는 메서드 (쉼표로 구분하여 출력)
		static void showData(String message, String[] data) {
			System.out.println(message);
			for (int i = 0; i < data.length; i++) {
				System.out.print(data[i]);
//				마지막 요소가 아닐때 쉼표와 공백 추가 (마지막에는 쉼표 오지 않도록)
				if (i < data.length - 1) {
					System.out.print(", ");
				}
			}
//			줄바꿈 추가(왜 그냥은 빈 줄이 오지 않는지 모르겠어서 일단은 개행문자를 넣음)
			System.out.println("\n");
		}
		
//		배열의 각 요소에서 공백을 제거하는 메서드
		static void trimSpace(String[] data) {
			for (int i = 0; i < data.length; i++) {
//				각 요소의 앞뒤 공백 제거
//				근데 애초에 원본 데이터에 공백이 없게 해서 이 메서드는 역할이 없는것 같음
				data[i] = data[i].trim();
			}
		}
		
//		배열의 데이터를 리스트에 추가하는 메서드
		static void makeList(String[] array, ArrayList<String> list)  {
			for (String s : array) {
				list.add(s);
			}
		}
		
//		리스트의 데이터를 출력하는 메서드 (이번에는 데이터를 공백으로 구분)
		static void showList(String message, List<String> list) {
			System.out.println(message);
			for (String s : list) {
				System.out.print(s + " ");
			}
//			여기서도 여백 줄이 안 들어가서 그냥 개행문자를 사용
			System.out.println("\n");
		}
		
		public static void main(String[] args) {
			try {
//				첫번째 파일 읽기 및 처리
				Path input1 = Paths.get("a1.txt");
//				파일의 모든 바이트를 읽어오는 메서드
//				파일을 열고 파일의 크기만큼 바이트를 읽어서 바이트 배열로 반환
				byte[] bytes1 = Files.readAllBytes(input1);
				System.out.println("bytes[]의 길이 = " + bytes1.length);
				
//				두번째 파일 읽기 및 처리
				Path input2 = Paths.get("a2.txt");
				byte[] bytes2 = Files.readAllBytes(input2);
				
//				바이트 데이터를 문자열로 반환
				String s1 = new String(bytes1);
				String s2 = new String(bytes2);
				
//				입력된 문자열 출력
				System.out.println("입력 스트링 : s1 = " + s1);
				System.out.println("입력 스트링 : s2 = " + s2);
				System.out.println();
				
//				[,\\s]+\r\n은 쉼표나 공백이 하나 이상 나오고 이어서 캐리지 리턴과 개행 문자가 있는 패턴
//				file에서 enter키는 \r\n으로 해야 분리됨
//				String[] sarray1 = s1.split("[,\\s]+\r\n");
//				String[] sarray2 = s2.split("[,\\s]+\r\n");
				
//				정렬과 중복 제거가 이루어지지 않아서 임의로 split()의 형태를 변경함
//				split() 메서드를 사용하여 문자열을 쉼표로 구분된 배열로 변환
				String[] sarray1 = s1.split(",\\s*");
	            String[] sarray2 = s2.split(",\\s*");
				showData("스트링 배열 sarray1", sarray1);
				showData("스트링 배열 sarray2", sarray2);

//				배열의 각 요소에서 공백 제거
				trimSpace(sarray1);
				trimSpace(sarray2);

//				공백 제거 후의 배열 출력
				showData("trimSpace() 실행 후 : 스트링 배열 sarray1", sarray1);
				showData("trimSpace() 실행 후 : 스트링 배열 sarray2", sarray2);
				System.out.println("+++++++\n");
				
				// file1에서 read하여 list1.add()
				// 배열을 list로 만드는 방법
				
//				방법1 : makeList() 메서드 활용해서 배열을 리스트로 변환하여 저장
//				배열 sarray1의 요소들을 ArrayList<String>에 직접 추가하는 방식
//				장점 : 명시적인 추가, 코드 유연하게 변경 가능
//				단점 : 코드가 장황해짐
				ArrayList<String> list1 = new ArrayList<>();
				makeList(sarray1, list1);
				showList("리스트1: ", list1);
				
//				방법2 : Arrays.asList() 메서드를 사용, 배열을 리스트로 변환하고 그 결과를 ArrayList 생성자의 인자로 전달하여 리스트 생성
//				장점 : 코드가 간결
//				단점 : Array.asList()로 생성된 리스트는 고정 크기, 추가나 삭제 작업 수행 시 새로운 ArrayList로 감싸야함
				ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sarray2));
				showList("리스트2: ", list2);
				
//				리스트를 정렬
				System.out.println("+++++++ collection.sort()::");
				Collections.sort(list1);
				showList("정렬 후 리스트1: ", list1);
				
//				Arrays.sort(list2, null);
//				null은 올바른 Comparator가 아니기에 NullPointerException이 발생됨
//				배열을 자연스러운 순서에 따라 정렬하려고 해도 Comparator를 명시적으로 제공하지 않았기에 에러 발생
				Collections.sort(list2);
				showList("정렬 후 리스트2: ", list2);	
		
//				정렬된 list에서 중복 제거
				list1 = removeDuplicate(list1);
				list2 = removeDuplicate(list2);
				showList("중복 제거 후 리스트1: ", list1);	
				showList("중복 제거 후 리스트1: ", list1);	
		
//				두 리스트 병합하여 새로운 리스트 생성
				List<String> list3 = new ArrayList<>();
				
//				방법3 : mergeList() 메서드 사용
//				장점 : 정렬된 리스트 병합 시 유용 -> 단점으로도 작용(병합 전 정렬이 이루어져 있어야함)
				list3 = mergeList(list1, list2);
				showList("merge 후 합병리스트: ", list3);	

				// ArrayList를 배열로 전환 (병합된 리스트를 배열로 변환)
				String[] st = list3.toArray(new String[list3.size()]);
				// binary search 구현
				// binSearch(st, st.length, "key");
				// 정렬된 list3을 file에 출력하는 코드 완성
				System.out.println("\n" + "file에 출력:");
//				버퍼 크기 설정
				int bufferSize = 10240;
				
//				파일에 쓸 버퍼 생성
				ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
				writeFile(list3, buffer);
				
//				파일 출력 스트림과 채널을 사용하여 파일에 데이터를 씀
				FileOutputStream file = new FileOutputStream("c.txt");
				FileChannel channel = file.getChannel();
				channel.write(buffer);
//				스트림과 채널 닫기
				file.close();
				
			} catch (IOException e) {
//				예외 발생 시 스택 트레이스 출력
				e.printStackTrace();
			}
		}
		
//		리스트의 데이터를 파일에 쓰는 메서드
		static void writeFile(List<String> list3, ByteBuffer buffer) {
			String b = " ";
			for (String sx : list3) {
				System.out.print(" " + sx);
//				요소를 바이트로 변환하여 버퍼에 추가
				buffer.put(sx.getBytes());
//				공백 추가
				buffer.put(b.getBytes());
			}
//			버퍼를 읽기 모드로 전환
			buffer.flip();
		}
}
