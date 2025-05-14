/*
게임에 등장할 모든 객체의 최상위 객체
공통적인 속성과 기능의 중복 작성을 피함 -> 재사용성
*/
class GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        this.img.src = src;
        this.img.style.width = this.width+"px";
        this.img.style.height = this.height+"px";
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
        this.img.style.position = "absolute";

        this.container.appendChild(this.img);
    }

    // 중복되는 메서드를 부모클래스에 정의하는 것은 올바른 개발 방식이나, 모든 객체들의 움직임을 부모가 예측할 수 없음

     /**
     * 물리량을 변화시키는 함수
     */
    tick(){}; // java: {몸체}가 없는 메서드를 추상 메서드라고 함 (ex, tick();). js는 {}는 두어야 함.

    /**
     * 변화된 물리량을 화면에 적용
     */
    render(){}
}