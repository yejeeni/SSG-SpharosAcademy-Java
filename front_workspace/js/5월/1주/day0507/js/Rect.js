class Rect {
    constructor(container, x, y, width, height, bg) {
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.container = container;
        this.a = 0.1;

        // 스타일 설정
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.backgroundColor = this.bg;

        // 화면에 부착
        this.container.appendChild(this.div);

        this.move();
    }
    
    move(){
        console.log("move()...");

        // 막대의 크기에 감속도 공식을 적용
        this.div.style.height = parseFloat(this.div.style.height) + this.a * (Math.random()*(400) - parseFloat(this.div.style.height)) + "px";

        setTimeout(()=>{
            this.move();
            // 여기서 this의 상위는 Rect 객체
        }, 10);
    }
}
