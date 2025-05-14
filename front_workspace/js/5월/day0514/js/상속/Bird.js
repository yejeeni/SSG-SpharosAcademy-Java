// 모든 새들의 어버이 격인 일반적인 새
class Bird{
    constructor(color, age){
        this.color = color;
        this.age = age;

        console.log("Bird 생성자 호출");
        console.log("Bird", this.color, this.age);
    }

    eat(){
        console.log("새가 먹이 먹음");
    }
    fly(){
        console.log("새가 날음");
    }
}