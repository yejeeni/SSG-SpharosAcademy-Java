class GameObject{
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        this.container = container;
        this.div = document.createElement("div"); // div를 이미지로 대체하고 싶으면, div의 background에 이미지를 넣으면 됨
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.bg = bg;
        this.border = border;
        this.borderColor = borderColor;

        // style 및 조립
        this.div.style.position = "absolute";
        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";
        this.div.style.width = this.width+"px";
        this.div.style.height = this.height+"px";
        this.div.style.background = this.bg;
        this.div.style.border = `${this.border}px solid ${this.borderColor}`;

        this.container.appendChild(this.div);
    }

    // 오브젝트의 변하게 될 물리량 계산
    tick(){}
    // 변화된 물리량을 그래픽에 적용
    render(){}
}