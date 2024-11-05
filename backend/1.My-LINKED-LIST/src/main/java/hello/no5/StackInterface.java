package hello.no5;

public interface StackInterface <T>{
    void push(T item);  // 요소를 스택의 상단에 추가
    T pop();            // 스택의 상단에서 요소를 제거하고 반환
    T top();           // 스택의 상단 요소를 반환 (제거하지 않음)
    int getSize();
}
