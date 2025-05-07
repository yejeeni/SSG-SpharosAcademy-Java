/* 
    현실의 총알 정의 
    모든 클래스 안에는 변수(상태)와 함수(동작, 상태변화)만 작성
*/

class Bullet{
    // 생성자 메서드는 new 연산자 뒤에서 호출됨 (ex, new Bullet();)
    constructor(bg, y){
        // Bullet이 보유할 속성(변수) 선언
        this.div = document.createElement("div");
        this.x = 0; // 총알의 초기 위치
        this.y = y;
        this.velX = 10; // 총알의 속도
        this.bg = bg; // 매개변수로 넘어온 데이터를 클래스 변수에 보관

        // 총알의 스타일
        this.div.style.width = 20+"px";
        this.div.style.height = 20+"px";
        this.div.style.borderRadius = 50+"%";
        this.div.style.background = bg;
        this.div.style.position = "absolute";
        this.div.style.left = this.x+"px";
        this.div.style.top = y+"px";
        
        document.body.appendChild(this.div);

        console.log("new Bullet");
    }

    // 총알의 움직임을 표현하는 메서드
    move(){
        this.x += this.velX;
        this.div.style.left = this.x+"px";
    }
}