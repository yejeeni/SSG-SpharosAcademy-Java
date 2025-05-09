// 달력을 이루는 하나의 셀을 정의
class Cell{
    constructor(container, width, height, bg, border, bdColor, num){
        this.container = container;
        this.div = document.createElement("div");
        this.width = width;
        this.height = height;
        this.bg = bg; // 배경색
        this.border = border; // 경계선 두께
        this.bdColor = bdColor; // 경계선 색상
        this.num = num; // 셀에 출력될 날짜

        // style
        this.div.style.width = this.width+"px";
        this.div.style.height = this.height+"px";
        this.div.style.background = this.bg;
        this.div.style.border = `${this.border}px solid ${this.bdColor}`;
        this.div.style.display = "inline-block";
        this.div.innerHTML = this.num;

        // 컨테이너에 자식 요소 부착
        this.container.appendChild(this.div);
    }
}