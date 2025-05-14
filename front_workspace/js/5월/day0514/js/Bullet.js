class Bullet extends GameObject{
    constructor(container, src, x, y, width, height, velX, velY){
        super(container, src, x, y, width, height, velX, velY);
    }

    // 화면에서 총알을 제거한 후, 배열에서도 삭제하는 메서드
    removeObject(array, target){
        this.container.removeChild(array[array.indexOf(target)].img); // this: 나의 인스턴스를 가리키는 대명사
        
        array.splice(array.indexOf(target), 1);
    }

    tick(){
        this.x += this.velX;
           
    }

    render(){
        // 총알이 움직일 때마다 총알과 적군과의 충돌 체크를 해서 제거
        for (let i=0; i<enemyArray.length; i++){
            let enemy = enemyArray[i]; // 적군 하나
            if (collisionCheck(this.img, enemy.img)){ // 충돌한 경우
                this.removeObject(enemyArray, enemy); // 적군 제거
                this.removeObject(bulletArray, this); // 총알 제거

                setScore(10);
            }
        }
        
        // 적군에 충돌하지 않은 총알은 자동 제거
        if (this.x > 1400){
            // 화면에서 제거
            this.removeObject(bulletArray, this);

            // 배열에서 제거
            // let index = bulletArray.indexOf(this);
            // bulletArray.splice(index, 1);
        }

        // 부모의 img를 상속받아옴
        this.img.style.left = this.x+"px";
    }
}