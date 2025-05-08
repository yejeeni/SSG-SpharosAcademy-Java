/**
 * 주인공 정의
 */
class Hero{
    // ES6의 클래스는 멤버 변수를 생성자에 둬야 함
    constructor(container, x, y, width, height, velX, velY){
        // injection: 외부에서 전달된 데이터를 나의 객체에 보관하는 기법
        this.container = container;
        this.img = document.createElement("img");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.idx = 1; // 이미지 배열의 인덱스 변수
        // 주인공의 sprite 이미지명 배열 선언
        this.imgArray = [];
        for(let i=1; i<=18; i++){
            this.imgArray.push(`../../../images/hero/image${i}.png`);
        }

        // style
        this.img.src = "../../../images/hero/image1.png";
        this.img.style.position = "absolute";
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
        this.img.style.width = this.width+"px";
        this.img.style.height = this.height+"px";

        this.container.appendChild(this.img);

        // 움직임 시작
        this.doIdle();
    }

    // 주인공 스탠딩 모션
    // 게임루프와 상관없이 자체적으로 무한루프로 움직임을 표현
    doIdle(){
        if (this.idx >= 17){
            this.idx = 1;
        } else{
            this.idx++;
        }
        this.img.src = this.imgArray[this.idx];
        setTimeout(()=>{
            // 람다는 this를 가질 수 없으므로, 여기서의 this는 상위스코프를 가리킴
            this.doIdle();
        }, 50);
    }

    // 상하좌우 움직임 동작 정의
    move(){
        // 물리적 변화량
        this.x += this.velX;
        this.y += this.velY;
        console.log(this.x, this.y);

        // 변화된 물리량을 화면에 반영 (렌더링)
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
    }
}