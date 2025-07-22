/**
 * 이미지 썸네일 클래스 정의
 */
class ProductImg {
	constructor(container, file, src, width, height) {
		this.container = container;
		this.file = file;
		this.src = src;
		this.width = width;
		this.height = height;

		this.wrapper = document.createElement("div");
		this.header = document.createElement("div");
		this.img = document.createElement("img");
		this.img.src = this.src;

		// style
		this.img.style.width = this.width + "px";
		this.img.style.height = this.width + "px";

		this.wrapper.style.width = this.width + "px";
		this.wrapper.style.height = this.height + 20 + "px";
		this.wrapper.style.display = "inline-block";
		this.wrapper.style.margin = 2 + "px";

		this.header.innerHTML = "<a href='#'>X</a>";
		this.header.style.textAlign = "right";

		// 조립
		this.wrapper.appendChild(this.header);
		this.wrapper.appendChild(this.img);
		this.container.appendChild(this.wrapper);

		// header에 클릭 이벤트 연결
		this.header.addEventListener("click", (e) => {
			// 여기서 람다의 this는 자신의 상위 스코프인 Product로 접근
			e.preventDefault(); // 이벤트 발생 시의 기본 특징을 제거(스크롤이 최상단으로 이동하지 않도록)
			this.remove();
		});
	}

	// 컨테이너에 붙어있는 요소를 화면에서 삭제
	remove() {
		// 화면에서 제거
		this.container.removeChild(this.wrapper);
		
		// 데이터 배열에서 제거
		// file이 복사 배열인 selectedFile의 몇 번째 인덱스인지 계산 후, 같은 위치의 file을 제거
		let index = selectedFile.indexOf(this.file);
		selectedFile.splice(index, 1);
	}

}