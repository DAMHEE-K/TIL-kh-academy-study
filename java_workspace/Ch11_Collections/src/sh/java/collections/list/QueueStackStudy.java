package sh.java.collections.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueStackStudy {

	public static void main(String[] args) {
		
		QueueStackStudy study = new QueueStackStudy();
//		study.test1();
		study.test2();
	}

	/**
	 * Stack
	 * - FILO / LIFO 자료구조 (First In Last Out / Last In First Out)
	 * - 요소 추가 Stack#push
	 * - 요소 제거 Stack#pop
	 * - 요소 확인 Stack#peek (요소를 제거하지 않고, 최상위 요소 확인)
	 * - 자바메소드호출스택, 브라우져 앞으로가기/뒤로가기 관리
	 */
	private void test2() {
		Stack<Integer> stack = new Stack<>();
		stack.push(30);
		stack.push(17);
		stack.push(55);
		stack.push(63);
		
		System.out.println(stack.pop()); // 마지막 요소 제거 후 반환
		System.out.println(stack.pop()); // 마지막 요소 제거 후 반환
		System.out.println(stack.peek()); // 마지막 요소 제거 후 반환
		
		while(!stack.isEmpty()) {
			Integer elem = stack.pop();
			System.out.println(elem);
		}
		System.out.println(stack);
	}

	/**
	 * Queue
	 * - FIFO 자료구조 (First In First Out)
	 * - 맨 뒤에만 요소 추가 Queue#offer
	 * - 맨 앞에서만 요소 제거 Queue#poll
	 */
	private void test1() {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(3);
		queue.offer(2);
		queue.offer(5);
		
//		System.out.println(queue.poll()); // 0번지 요소 제거
//		System.out.println(queue.poll()); // 0번지 요소 제거
//		System.out.println(queue);
		
		while(!queue.isEmpty()) {
			Integer elem = queue.poll(); // 맨 처음에 넣은 놈부터 순서대로 꺼냄
			System.out.println(elem);
		}
		System.out.println(queue);
	}

}
