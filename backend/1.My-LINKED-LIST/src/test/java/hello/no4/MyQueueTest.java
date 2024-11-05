package hello.no4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyQueueTest {
    @Test
    @DisplayName("빈 큐에서 pop 또는 peek 호출 시 예외 발생 테스트")
    void testEmptyQueue() {
        QueueInterface<String> myQueue = new MyQueue<>();

        // 빈 큐에서 pop과 peek을 호출하면 예외가 발생해야 함
        assertThrows(IllegalStateException.class, myQueue::pop);
        assertThrows(IllegalStateException.class, myQueue::peek);

        // 빈 큐의 크기 확인
        assertEquals(0, myQueue.getSize());
        assertEquals("", myQueue.toString());
    }


    @Test
    @DisplayName("pop 테스트")
    void deleteTest(){
        QueueInterface<String> myQueue = new MyQueue<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));
        // 모든 요소를 큐에 추가
        dest.forEach(myQueue::push);

        // 각 요소를 pop하면서 검증
        for (String expected : dest) {
            assertEquals(expected, myQueue.pop()); // pop된 값이 예상값과 같은지 확인
        }

        // 모든 요소가 pop된 후 큐가 비었는지 확인
        assertEquals(0, myQueue.getSize());
        assertEquals("", myQueue.toString());
    }

    @Test
    @DisplayName("전체 delete 후 전체 add 후 전체 delete 테스트")
    void testAddAndDeleteAll() {
        QueueInterface<String> myQueue = new MyQueue<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));

        // 모든 요소 추가
        dest.forEach(myQueue::push);

        // 모든 요소 삭제 후 크기 확인
        for (String expected : dest) {
            assertEquals(expected, myQueue.pop());
        }
        assertEquals(0, myQueue.getSize());
        assertEquals("", myQueue.toString());

        // 다시 모든 요소 추가
        dest.forEach(myQueue::push);

        // 다시 모든 요소 삭제 후 크기 확인
        for (String expected : dest) {
            assertEquals(expected, myQueue.pop());
        }
        assertEquals(0, myQueue.getSize());
        assertEquals("", myQueue.toString());
    }

    @Test
    @DisplayName("peek 후 pop 테스트")
    void deleteTestIfOneValue(){
        QueueInterface<String> myQueue = new MyQueue<>();
        String dest = "1st";
        myQueue.push(dest);
        assertEquals(myQueue.peek(), dest);
        assertEquals(myQueue.pop(), dest);
        assertEquals(myQueue.toString(), "");
    }

}