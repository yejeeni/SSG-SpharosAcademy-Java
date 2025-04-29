/*
    n 이하의 랜덤한 정수값을 반환하는 함수
    ex) n = 1000이면 0~1000 반환
*/
function getRandom(n){
    return parseInt(Math.random()*(n+1));
}