class Sensor extends GameObject{
    constructor(container, hero, x, y, width, height, bg, border, borderColor){
        // 부모클래스에는 속도가 있지만 센서는 속도가 필요없기 때문에, 부모의 생성자 super에만 속도를 명시하고 sensor의 클래스에선 제거
        super(container, x, y, width, height, 0, 0, bg, border, borderColor);

        // 부모의 코드에 존재하지 않는 것만 처리
        this.hero = hero;
    }


}