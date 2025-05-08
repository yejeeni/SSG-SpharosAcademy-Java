class Test{
// JS는 변수에 할당한 데이터의 종류 판단을 실행할 때마다 해석하는 인터프리터 언어
// 장점: 수시로 데이터를 바꾸면서 결과를 확인할 수 있음

// java는 자료형에 대한 판단을 실행 전 문법 검사 시 시도한 다음 그 결과를 파일로 저장해놓고, 프로그램 실행 시 두 번 다시 컴파일하지 않는 컴파일 언어
// 코드가 수정되면 다시 컴파일해야 함
    String name = "김예진";
    
    // 자바 클래스를 실행하려면 반드시 메인 함수 정의
    public static void main(String[] args){
        // System.out.println("hello world");

        for (int i=1; i<10; i++){
            System.out.println("2 * " + i + " = " + 2 * i);
        }
    }
}