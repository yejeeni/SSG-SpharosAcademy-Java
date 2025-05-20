class Cell {
    constructor(container, x, y, width, height, bg, borderColor, date) {
        /** 다이어리 관련 내용 */
        this.year;
        this.month;
        this.date = date;
        this.icon;

        this.container = container;
        this.div = document.createElement("div"); // 셀 자체 박스
        this.numDiv = document.createElement("div");; // 달력 날짜 출력할 영역
        this.titleDiv = document.createElement("div"); // 다이어리 제목
        this.iconDiv = document.createElement("div"); // 아이콘이 위치할 영역
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.borderColor = borderColor;
        this.date = date;

        // cell div의 스타일
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = this.bg;
        this.div.style.border = "1px solid " + this.borderColor;
        this.div.style.boxSizing = "border-box";
        this.div.style.borderRadius = "5px";

        this.div.style.boxSizing = "border-box"; // 보더, 마진 등으로 인한 박스 크기가 안쪽으로 늘어나게 
        //this.div.style.background = "yellow";

        // 다이어리제목 div 스타일
        this.titleDiv.style.width = 100 + "%";
        this.titleDiv.style.height = 25 + "px";
        //this.titleDiv.style.background = "blue";
        this.titleDiv.style.boxSizing = "border-box";

        // 아이콘 항목 div
        this.iconDiv.style.width = 100 + "%";
        this.iconDiv.style.height = 50 + "px";
        //this.iconDiv.style.backgroundColor = "green";
        this.iconDiv.style.boxSizing = "border-box";

        // 날짜 출력 div 스타일
        this.numDiv.style.width = 100 + "%";
        this.numDiv.style.height = 25 + "px";
        //this.numDiv.style.textAlign = "right";
        this.numDiv.style.padding = "0px 5px 0px 0px";
        this.numDiv.style.boxSizing = "border-box";

        // 셀에 div 3개 넣기
        this.div.appendChild(this.numDiv);
        this.div.appendChild(this.titleDiv);
        this.div.appendChild(this.iconDiv);

        this.container.appendChild(this.div);

        // 현재 셀, div에 클릭 이벤트 구현
        this.div.addEventListener("click", () => { // 람다는 this를 가질 수 없으므로, 바깥 상위 스코프인 객체를 가리키려면 람다를 사용
            // dialog 창을 띄워줌
            openDialog(this);
            
            // console.log(this);
        })
    }

    /* 셀에 보여질 날짜 대입 */
    setDate(year, month, date) {
        this.year = year;
        this.month = month;
        this.date = date;

        this.numDiv.innerText = date;
    }

    // 셀이 자신이 보유한 아이콘을 그리기
    randerIcon(src, width) {
        let img = document.createElement("img");
        img.src = src;
        img.style.width = width + "px";
        img.style.height = "auto"; // 필요에 따라 높이 자동 조절
        this.iconDiv.appendChild(img);
    }
}