package hello.no5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyStackTest {
    @Test
    @DisplayName("빈 스택에서 pop 또는 top 호출 시 예외 발생 테스트")
    void testEmptyStack() {
        StackInterface<String> myStack = new MyStack<>();

        // 빈 스택에서 pop과 top 호출 시 예외 발생 확인
        assertThrows(IllegalStateException.class, myStack::pop);
        assertThrows(IllegalStateException.class, myStack::top);

        // 빈 스택의 크기 확인
        assertEquals(0, myStack.getSize());
        assertEquals("", myStack.toString());
    }

    @Test
    @DisplayName("pop 테스트")
    void deleteTest() {
        StackInterface<String> myStack = new MyStack<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st", "4st", "5st", "6st"));

        // 모든 요소를 스택에 추가
        dest.forEach(myStack::push);

        // 각 요소를 pop하면서 검증 (스택은 LIFO이므로 역순으로 검증)
        for (int i = dest.size() - 1; i >= 0; i--) {
            assertEquals(dest.get(i), myStack.pop()); // pop된 값이 예상값과 같은지 확인
        }

        // 모든 요소가 pop된 후 스택이 비었는지 확인
        assertEquals(0, myStack.getSize());
        assertEquals("", myStack.toString());
    }

    @Test
    @DisplayName("전체 delete 후 전체 add 후 전체 delete 테스트")
    void testAddAndDeleteAll() {
        StackInterface<String> myStack = new MyStack<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st", "4st", "5st", "6st"));

        // 모든 요소를 스택에 추가
        dest.forEach(myStack::push);

        // 모든 요소를 pop하여 삭제 (LIFO 순서)
        for (int i = dest.size() - 1; i >= 0; i--) {
            assertEquals(dest.get(i), myStack.pop());
        }
        assertEquals(0, myStack.getSize());
        assertEquals("", myStack.toString());

        // 다시 모든 요소를 스택에 추가
        dest.forEach(myStack::push);

        // 다시 모든 요소를 pop하여 삭제
        for (int i = dest.size() - 1; i >= 0; i--) {
            assertEquals(dest.get(i), myStack.pop());
        }
        assertEquals(0, myStack.getSize());
        assertEquals("", myStack.toString());
    }

    @Test
    @DisplayName("top 후 pop 테스트")
    void deleteTestIfOneValue() {
        StackInterface<String> myStack = new MyStack<>();
        String dest = "1st";

        // 스택에 요소 추가
        myStack.push(dest);

        // top으로 값 확인 후 pop으로 제거
        assertEquals(dest, myStack.top());
        assertEquals(dest, myStack.pop());

        // 스택이 비었는지 확인
        assertEquals(0, myStack.getSize());
        assertEquals("", myStack.toString());
    }

}