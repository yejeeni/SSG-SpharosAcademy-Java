const { createApp, reactive, ref, onMounted, computed, nextTick } = Vue;

createApp({
    setup() {
        // 상태 관리
        const restaurants = reactive([]);
        const searchWord = ref("");
        const selectedCategory = ref("전체");
        const sidebarCollapsed = ref(false);
        const showInfoPanel = ref(false);
        const selectedRestaurant = ref(null);
        const selectedIndex = ref(-1);

        // 지도 관련 변수들
        let map = null;
        let geocoder = null;
        const markers = reactive([]);
        const overlays = reactive([]);

        // 필터링된 레스토랑 목록
        const filteredRestaurants = computed(() => {
            let filtered = restaurants;

            // 카테고리 필터
            if (selectedCategory.value !== '전체') {
                filtered = restaurants.filter(r => r.category === selectedCategory.value);
            }

            // 검색어 필터
            if (searchWord.value.trim()) {
                filtered = filtered.filter(r =>
                    r.mainTitle.toLowerCase().includes(searchWord.value.toLowerCase()) ||
                    r.place.toLowerCase().includes(searchWord.value.toLowerCase())
                );
            }

            return filtered;
        });

        /**
         * 카카오맵 로드 확인 후 초기화
         */
        const waitForKakaoAndInit = () => {
            if (typeof kakao !== 'undefined' && kakao.maps && kakao.maps.Map) {
                console.log('카카오맵 API 사용 가능');
                try {
                    initMap();
                    getRestaurants();
                } catch (error) {
                    console.error('초기화 실패:', error);
                }
            } else {
                console.log('카카오맵 API 로딩 중');
                setTimeout(waitForKakaoAndInit, 200); // 대기 시간 증가
            }
        };

        /**
         * 지도 초기화
         */
        const initMap = () => {
            try {
                console.log('🗺️ 지도 초기화 시작');

                const container = document.getElementById('map');
                if (!container) {
                    console.error('map 컨테이너를 찾을 수 없음');
                    return;
                }

                const options = {
                    center: new kakao.maps.LatLng(35.108, 129.036),
                    level: 5
                };

                map = new kakao.maps.Map(container, options);
                geocoder = new kakao.maps.services.Geocoder();

                // 지도 컨트롤 추가
                const mapTypeControl = new kakao.maps.MapTypeControl();
                map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

                const zoomControl = new kakao.maps.ZoomControl();
                map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

                // 지도 클릭 시 패널 닫기
                kakao.maps.event.addListener(map, 'click', () => {
                    closeInfoPanel();
                });

                console.log('지도 초기화 완료');
            } catch (error) {
                console.error('지도 초기화 실패:', error);
            }
        };

        /**
         * 서버에서 레스토랑 목록 가져오기
         */
        const getRestaurants = async () => {
            try {
                console.log('서버에서 데이터 요청');

                const response = await $.ajax({
                    url: "/list",
                    method: "GET",
                    timeout: 10000 // 10초 타임아웃
                });

                console.log("음식점 목록 조회 성공:", response);

                // 기존 데이터 초기화
                restaurants.splice(0);

                // 새 데이터 추가
                if (Array.isArray(response)) {
                    response.forEach(element => {
                        restaurants.push(element);
                    });
                } else {
                    console.warn('응답 데이터가 배열이 아닙니다:', response);
                }

                // 지도 마커 설정
                setMapMarkers(restaurants);

            } catch (error) {
                console.error("데이터 로드 실패:", error);

                // 에러 발생 시 더미 데이터 사용 (개발용)
                if (error.status === 403) {
                    console.log('인증 오류 - 더미 데이터 사용');
                    loadDummyData();
                }
            }
        };

        /**
         * 더미 데이터 로드 (개발/테스트용)
         */
        const loadDummyData = () => {
            const dummyData = [
                {
                    mainTitle: "맛집",
                    place: "서울시 강남구 테헤란로 123",
                    category: "한식",
                    mainImgThumb: "https://via.placeholder.com/80x80?text=Restaurant1",
                    url: "https://example.com",
                    itemcntnts: "정말 맛있는 한식 전문점입니다.",
                    lat: 37.5665,
                    lng: 126.9780
                },
                {
                    mainTitle: "스프링 부트 카페",
                    place: "서울시 서초구 강남대로 456",
                    category: "디저트",
                    mainImgThumb: "https://via.placeholder.com/80x80?text=Restaurant2",
                    url: "https://example.com",
                    itemcntnts: "개발자들이 사랑하는 카페입니다.",
                    lat: 37.5000,
                    lng: 127.0000
                }
            ];

            restaurants.splice(0);
            dummyData.forEach(item => restaurants.push(item));
            setMapMarkers(restaurants);

            console.log('더미 데이터 로드 완료');
        };

        /**
         * 지도 마커 설정
         */
        const setMapMarkers = (data) => {
            if (!map) {
                console.warn('지도가 초기화되지 않았습니다');
                return;
            }

            console.log('마커 설정 시작:', data.length, '개');

            // 기존 마커/오버레이 제거
            clearMarkers();

            for (let i = 0; i < data.length; i++) {
                try {
                    // 서버에서 받은 좌표 데이터 사용
                    const lat = data[i].lat || data[i].latitude;
                    const lng = data[i].lng || data[i].longitude;

                    if (!lat || !lng) {
                        console.warn('좌표 데이터가 없습니다:', data[i].mainTitle);
                        continue;
                    }

                    const coords = new kakao.maps.LatLng(lat, lng);

                    // 마커 생성
                    const marker = new kakao.maps.Marker({
                        position: coords,
                        map: map
                    });

                    // 커스텀 오버레이 생성
                    const overlay = new kakao.maps.CustomOverlay({
                        position: coords,
                        content: `<div class="custom-overlay">${data[i].mainTitle}</div>`,
                        yAnchor: 1.3
                    });

                    markers.push(marker);
                    overlays.push(overlay);

                    // 마커 클릭 이벤트
                    kakao.maps.event.addListener(marker, 'click', () => {
                        selectRestaurant(data[i], i);
                    });

                } catch (error) {
                    console.error('마커 생성 실패:', data[i].mainTitle, error);
                }
            }

            console.log('마커 설정 완료:', markers.length, '개');
        };

        /**
         * 주소를 좌표로 변환 (필요시 사용)
         */
        const getCoordsByAddress = (address) => {
            return new Promise((resolve, reject) => {
                if (!geocoder) {
                    reject(new Error('지오코더가 초기화되지 않았습니다'));
                    return;
                }

                geocoder.addressSearch(address, (result, status) => {
                    if (status === kakao.maps.services.Status.OK) {
                        const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                        resolve(coords);
                    } else {
                        reject(new Error('주소 변환 실패: ' + address));
                    }
                });
            });
        };

        /**
         * 기존 마커/오버레이 제거
         */
        const clearMarkers = () => {
            markers.forEach(marker => marker.setMap(null));
            overlays.forEach(overlay => overlay.setMap(null));
            markers.splice(0);
            overlays.splice(0);
        };

        /**
         * 레스토랑 선택 (카드 클릭)
         */
        const selectRestaurant = (restaurant, index) => {
            selectedIndex.value = index;
            selectedRestaurant.value = restaurant;
            showInfoPanel.value = true;

            if (markers[index]) {
                // 오프셋 이동
                const markerPosition = markers[index].getPosition();

                // 현재 지도 범위 가져오기
                const bounds = map.getBounds();
                const swLatLng = bounds.getSouthWest();
                const neLatLng = bounds.getNorthEast();

                // 경도 범위 좌우 폭 계산
                const lngSpan = neLatLng.getLng() - swLatLng.getLng();

                // 오른쪽에서 지정한 만큼의 지점으로
                const offsetLng = markerPosition.getLng() - (lngSpan * 0.26);
                const offsetPosition = new kakao.maps.LatLng(markerPosition.getLat(), offsetLng);

                map.panTo(offsetPosition);
                showOverlay(index);
            }

            // 선택된 카드가 보이도록 스크롤
            nextTick(() => {
                const selectedCard = document.querySelector(`.result-card:nth-child(${index + 1})`);
                if (selectedCard) {
                    selectedCard.scrollIntoView({ behavior: 'smooth', block: 'center' });
                }
            });
        };

        /**
         * 오버레이 표시
         */
        const showOverlay = (index) => {
            // 모든 오버레이 숨기기
            overlays.forEach(overlay => overlay.setMap(null));

            // 선택된 오버레이만 표시
            if (overlays[index]) {
                overlays[index].setMap(map);
            }
        };

        /**
         * 사이드바 토글
         */
        const toggleSidebar = () => {
            sidebarCollapsed.value = !sidebarCollapsed.value;
        };

        /**
         * 카테고리 필터링
         */
        const filterByCategory = (category) => {
            console.log('카테고리:', category);

            selectedCategory.value = category;
            closeInfoPanel();

            nextTick(() => {
                setMapMarkers(filteredRestaurants.value);
            });
        };

        /**
         * 검색 실행
         */
        const searchRestaurants = () => {
            console.log('검색:', searchWord.value);

            nextTick(() => {
                setMapMarkers(filteredRestaurants.value);
            });
        };

        /**
         * 정보 패널 닫기
         */
        const closeInfoPanel = () => {
            showInfoPanel.value = false;
            selectedRestaurant.value = null;
            selectedIndex.value = -1;

            // 모든 오버레이 숨기기
            overlays.forEach(overlay => overlay.setMap(null));

            console.log('정보 패널 닫기');
        };

        /**
         * 컴포넌트 마운트 시 실행
         */
        onMounted(() => {
            console.log('Vue 앱 마운트 완료');
            waitForKakaoAndInit();
        });

        // 반환할 데이터와 메서드
        return {
            // 상태
            restaurants,
            searchWord,
            selectedCategory,
            sidebarCollapsed,
            showInfoPanel,
            selectedRestaurant,
            selectedIndex,

            // 계산된 값
            filteredRestaurants,

            // 메서드
            selectRestaurant,
            toggleSidebar,
            filterByCategory,
            searchRestaurants,
            closeInfoPanel
        };
    }
}).mount("#app");