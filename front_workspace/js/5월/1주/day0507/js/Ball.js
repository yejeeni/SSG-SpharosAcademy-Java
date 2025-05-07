class Ball{
    constructor(container, x, y, width, height, velX, velY, bg){
        this.container = container; // 공을 부착할 위치
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX; // x축 속도
        this.velY = velY; // y축 속도
        this.bg = bg; // 색상

        // style
        this.div.style.position = "absolute";
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";
        this.div.style.width = this.width+"px";
        this.div.style.height = this.height+"px";
        this.div.style.backgroundColor = this.bg;
        this.div.style.borderRadius = 50+"%";

        // container는 호출 시 결정
        this.container.appendChild(this.div);
    }    

    // 공의 움직임을 정의하는 메서드
    move(){
        this.x += this.velX;
        this.y += this.velY;

        // 공이 벽에 닿으면 방향 전환
        if(this.y <= 0 || this.y >= 600 - this.height){
            this.velY *= -1;
        }
        if(this.x <= 0 || this.x >= 600 - this.width){
            this.velX *= -1;
        }
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";

        
    }
}