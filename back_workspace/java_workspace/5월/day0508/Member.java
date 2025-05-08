class Member {
    static int age = 23; // 클래스 변수: 인스턴스가 아닌 원본 클래스가 보유한 변수. Member.age로 접근

    public static void talk(){
    }
}

public class UseMember{
    static String k = 8; // UseMember에 static으로 선언. UseMember 클래스가 보유

    public static void main(String[] args){
        Member.age = 7; // age는 클래스 변수이므로 실행된다.

        Member member = new Member();
        System.out.println(member.age);

        // k에 접근하는 방법 1
        UseMember.k = 9;

        // k에 접근하는 방법 2: main과 UseMember가 static이면서 같은 클래스 안에 있을 경우
        k = 10;
    }

}