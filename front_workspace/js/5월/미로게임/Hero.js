class Hero extends GameObject {
    constructor(container, x, y, width, height, velX, velY, bg, border, borderColor){
        super(container, x, y, width, height, velX, velY, bg, border, borderColor);
        
        // 사방 4개의 센서를 보유
        // this.upSensor;
        this.rightSensor = new RightSensor(this.div, this, this.width-3, 3, 3, this.height-6, "blue", 0, "");
        // this.downSensor;
        // this.leftSensor;
    }


    /* 부모의 메서드 오버라이딩 */
    tick(){
        this.x += this.velX;
        this.y += this.velY;
    }

    render(){
        // 현재 화면에 등장한 히어로와 벽돌의 충돌 체크
        for (let i=0; i<brickArray.length; i++){
            for(let j=0; j<brickArray[i].length; j++){
                if (brickArray[i][j] != 0){
                    if (collisionCheck(this.div, brickArray[i][j].div)){
                        this.div.style.background = "pink";
                    }
                }
            }
        }

        this.div.style.left = this.x+"px";
        this.div.style.top = this.y+"px";
    }
}