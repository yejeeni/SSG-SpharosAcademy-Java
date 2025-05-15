/*
    n 이하의 랜덤한 정수값을 반환하는 함수
    ex) n = 1000이면 0~1000 반환
*/
function getRandom(n){
    return parseInt(Math.random()*(n+1));
}

/**
 * 지정한 범위 내의 랜덤한 정수값을 반환하는 함수
 */
function getRandomByRange(min, max){
    return min + parseInt(Math.random()*(max - min + 1));
}

/**
 * 한 자리 수 정수 n에 대한 0 처리
 */
function zeroString(n) {
    let str = n;
    if (n > 0 && n < 10){
        str = "0" + n;
    }
    return str;
}

/**
 * 입력한 연, 월의 시작 요일 구하기
 * (월은 0부터 시작)
 * ex) 2025년 5월을 원할 경우, getStartDay(2025, 4)
 */
function getStartDay(yy, mm) {
    let d = new Date(yy, mm, 1); // yy년 mm월 1일
    return d.getDay();
}

/**
 * 입력한 월의 마지막 날 구하기 
 * (월은 0부터 시작)
 * ex) getLastDate(원하는 연도, 원하는 월)
 */
function getLastDate(yy, mm) {
    let d = new Date(yy, mm + 1, 0);
    return d.getDate();
}

/**
 * 영어 또는 한국어로 요일을 변환하여 반환
 * ex) convertDay(2, "eng") -> Tue
 */ 
function convertDay(n, lang) {
    let korArray = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    let EngArray = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    
    let day = (lang == "kor")? korArray[n] : EngArray[n];

    return day; 
}

/**
 * 오브젝트 충돌 검사 함수
 */
function collisionCheck(me, target){
    // 나에 대한 수치
    let me_x = parseInt(me.style.left);
    let me_y = parseInt(me.style.top);
    let me_width = parseInt(me.style.width);
    let me_height = parseInt(me.style.height);

    // 상대의 수치
    let target_x = parseInt(target.style.left);
    let target_y = parseInt(target.style.top);
    let target_width = parseInt(target.style.width);
    let target_height = parseInt(target.style.height);

    return !((me_x + me_width < target_x) || (me_x > target_x + target_width) || (me_y + me_height < target_y) || (me_y > target_y + target_height));
}