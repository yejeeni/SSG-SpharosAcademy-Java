class Hero extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        // js는 반드시 super() 명시
        super(container, src, x, y, width, height, velX, velY);
    }

    // 부모가 완성하지 않은 메서드를 자식이 커스텀: 메서드 오버라이딩
     /**
     * 물리량을 변화시키는 함수
     */
    tick(){
        this.x += this.velX;
        this.y += this.velY;        
    }

    /**
     * 변화된 물리량을 화면에 적용
     */
    render(){
        // 부모의 img를 상속받아옴
        this.img.style.left = this.x+"px";
        this.img.style.top = this.y+"px";
    }
}