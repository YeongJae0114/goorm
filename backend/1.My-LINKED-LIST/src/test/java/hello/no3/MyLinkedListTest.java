package hello.no3;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {

    @Test
    @DisplayName("forEach 표현식, 메서드 테스트")
    void forEachTest(){
        LinkedListIterableInterface<String> myLinkedList = new MyLinkedList<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));

        forEachExpression(dest, myLinkedList);
        forEachMethod(myLinkedList, dest);
    }

    private void forEachExpression(List<String> dest, LinkedListIterableInterface<String>myLinkedList){
        int cnt = 0;
        dest.forEach(myLinkedList::add);
        for (String s : myLinkedList) {
            assertEquals(s, dest.get(cnt));
            cnt++;
        }
    }

    private void forEachMethod(LinkedListIterableInterface<String>myLinkedList,List<String> dest){
        AtomicInteger atomicInteger = new AtomicInteger(0);
        myLinkedList.forEach(s->assertEquals(s, dest.get(atomicInteger.incrementAndGet()-1)));
    }

}