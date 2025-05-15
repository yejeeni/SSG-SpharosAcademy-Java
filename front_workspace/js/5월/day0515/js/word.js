// 단어를 정의

class Word{
    constructor(container, x, y, text, color){
        this.container = container;
        this.span = document.createElement("span"); // span은 inline이라 너비가 컨텐츠만큼만 확보(wrapping)
        this.x = x;
        this.y = y;
        this.text = text; // 이 span이 포함하는 단어
        this.color = color; // 글자색

        // 스타일, 조립
        this.span.style.display = "inline-block";
        this.span.style.position = "absolute";
        this.span.style.left = this.x+"px";
        this.span.style.top = this.y+"px";
        this.span.innerText = this.text;
        this.span.style.color = this.color;

        this.container.appendChild(this.span);
    }

    // 물리량 변화
    tick(){
        this.y += 7;
    }

    // 그래픽 처리
    render(){
        this.span.style.top = this.y+"px";
    }
}