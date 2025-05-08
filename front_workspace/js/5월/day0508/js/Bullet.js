/**
 * 총알을 정의
 */
class Bullet {
    constructor(container, x, y, width, height, velX, velY){
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        // style
        this.div.src = "../../../images/verticalScrollerShootingGame/ball.png";
        this.div.style.position = "absolute";
        this.div.style.backgroundColor = "red";
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";
        this.div.style.width = this.width+"px";
        this.div.style.height = this.height+"px";
        this.div.style.borderRadius = 50+"%";
        this.div.style.filter = "blur(2px)";

        this.container.appendChild(this.div);
    }

    // 총알이 날아가는 기능 정의
    move(){
        // 총알이 화면 밖으로 나가는 경우, 메모리 관리를 위해 화면과 리스트에서 제거
        if (this.x >= 1500){
            this.container.removeChild(this.div); // 화면에서 제거

            // Bullet.js가 슈팅게임.html에 import되어있으므로, 슈팅게임.html의 전역변수 bulletArray에도 접근 가능
            // 현재 총알(자기자신)이 배열의 몇 번째 위치에 있는지 검색
            let index = bulletArray.indexOf(this);
            bulletArray.splice(index, 1);
        }

        this.x += this.velX;
        this.div.style.left = this.x+"px";
    }
}