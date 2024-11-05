package hello.no1;

import static org.junit.jupiter.api.Assertions.*;

import hello.common.LinkedListInterface;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyLinkedListTest {
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5})
    @DisplayName("delete 메서드로 테스트")
    public void delete(int targetIndex){
        LinkedListInterface<String>myLinkList = new MyLinkedList<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));

        dest.forEach(myLinkList::add);
    //        for (String value : dest) {
    //            myLinkList.add(value);
    //        }
        myLinkList.delete(targetIndex);
        dest.remove(targetIndex);
        assertEquals(myLinkList.toString(), String.join(" -> ", dest));
    }

    @Test
    @DisplayName("delete 메서드 테스트 - 1개만 남았을 때")
    public void deleteTestIfOneValue(){
        LinkedListInterface<String>myLinkList = new MyLinkedList<>();
        myLinkList.add("1st");
        myLinkList.delete(0);
        assertEquals(myLinkList.toString(),"");
        assertEquals(myLinkList.getSize(),0);
    }

    @Test
    @DisplayName("delete 메서드 테스트 - 1개만 남았을 때")
    public void deleteAddDeleteTest(){
        LinkedListInterface<String>myLinkList = new MyLinkedList<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));

        dest.forEach(myLinkList::add);
        assertEquals(myLinkList.toString(), String.join(" -> ", dest));

        // 가장 마지막 삭제
        myLinkList.delete(dest.size() - 1);
        assertEquals(myLinkList.toString(),"1st -> 2st -> 3st -> 4st -> 5st");

        // 처음 삭제
        myLinkList.delete(0);
        assertEquals(myLinkList.toString(),"2st -> 3st -> 4st -> 5st");

        // 전체 삭제
        IntStream.range(0, myLinkList.getSize()).forEach(i->myLinkList.delete(0));
        assertEquals(myLinkList.toString(), "");

        // 전체 주가
        dest.forEach(myLinkList::add);
        assertEquals(myLinkList.toString(), String.join(" -> ", dest));

        // 전체 삭제
        IntStream.range(0, myLinkList.getSize()).forEach(i->myLinkList.delete(0));
        assertEquals(myLinkList.toString(), "");
    }

    @Test
    @DisplayName("get 메서드 테스트")
    public void getTest(){
        LinkedListInterface<String>myLinkList = new MyLinkedList<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));

        dest.forEach(myLinkList::add);
        for (int i = 0; i < dest.size(); i++) {
            assertEquals(myLinkList.get(i), dest.get(i));
        }
    }

    @Test
    @DisplayName("유효 범위 테스트")
    public void validRangeTest(){
        LinkedListInterface<String>myLinkList = new MyLinkedList<>();
        List<String> dest = new ArrayList<>(Arrays.asList("1st", "2st", "3st","4st", "5st", "6st"));

        // 무효한 인덱스 번호
        assertThrowsExactly(IndexOutOfBoundsException.class, ()->myLinkList.get(-1));


        // 유효한 인덱스이지만 아직 없는 인덱스 번호
        assertThrowsExactly(IndexOutOfBoundsException.class, ()->myLinkList.get(5));

        // 사이즈를 넘어서는 번호
        dest.forEach(myLinkList::add);
        assertEquals(myLinkList.get(myLinkList.getSize()-1), dest.get(dest.size()-1));
        assertThrowsExactly(IndexOutOfBoundsException.class, ()->myLinkList.get(myLinkList.getSize()));
    }
}