/**
 * ES6부터 상속 지원
 */

class Duck extends Bird{
    /* 
    js와 java 둘 다 상속관계에서 자식의 인스턴스가 초기화되기 전에 부모의 인스턴스 초기화가 선행돼야 한다.
    js는 자식의 클래스에서 생성자를 정의하기만 해도 무조건 부모의 생성자 호출을 의무사항으로 명시해놓음
    */
    constructor(color, age){
        // 부모 초기화를 반드시 선행
        super(color, age); // super(): 부모의 constructor()

        this.color = color;
        this.age = age

        console.log("Duck",this.color, this.age);
    }

    fly(){
        console.log("오리가 날음");
    }
}