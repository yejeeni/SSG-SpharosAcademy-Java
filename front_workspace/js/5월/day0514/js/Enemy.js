class Enemy extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        // js는 반드시 super() 명시
        super(container, src, x, y, width, height, velX, velY);
    }

    // 부모로부터 상속받은 tick, render 오버라이딩
    tick(){
        this.x += this.velX
    }

    render(){
        this.img.style.left = this.x+"px";
    }
}