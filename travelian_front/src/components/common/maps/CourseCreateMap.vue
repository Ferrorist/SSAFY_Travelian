<script setup>
// https://apis.map.kakao.com/web/sample/keywordList/
import { ref, onMounted, watch } from "vue";
import { searchAttraction } from "@/api/attraction.js";
const prop = defineProps({ attraction: Object });
const emit = defineEmits(["addAttraction"]);
let map, infowindow;
const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;
const { VITE_LOCATION_BASE_URL, VITE_LOCATION_SERVICE_KEY } = import.meta.env;
const { VITE_KAKAO_MAP_MARKERS_URL } = import.meta.env;

const keyword = ref("");
const data_length = ref(0);
const areas = ref([]);
const markers = ref([]);
const pageNo = ref(0);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const Map_script = document.createElement("script");
    Map_script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
    Map_script.onload = () =>
      kakao.maps.load(() => {
        initMap();
      });
    document.head.appendChild(Map_script);
  }
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(37.500613, 127.036431),
    level: 5,
    disableDoubleClickZoom: true, // 더블클릭으로 지도 zoom 하는 기능 끄기
  };
  map = new kakao.maps.Map(container, options);
  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
};

// 관광지 검색 시 필요한 query문
const getQueryString = (numOfRows, pgNo) => {
  let queryString = `serviceKey=${VITE_LOCATION_SERVICE_KEY}&numOfRows=${numOfRows}&pageNo=${pgNo}&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  queryString += "&keyword=" + keyword.value;

  return queryString;
};

// 관광지 검색
const SearchAttraction = (pgNo) => {
  const numOfRows = 10;
  let service = `searchKeyword1`;

  let searchUrl =
    VITE_LOCATION_BASE_URL + service + "?" + getQueryString(numOfRows, pgNo);

  const success = (response) => {
    console.log(typeof response.data);
    areas.value = response.data;
    console.log("areas.value: ", areas.value);
  };

  const fail = (error) => {
    alert("서버 오류! 잠시 후 다시 시도바랍니다.");
    console.error(error);
  };

  searchAttraction(success, fail, "", "", keyword.value);
};

const searchPlaces = (pgNo) => {
  if (!keyword.value.replace(/^\s+|\s+$/g, "")) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  // Spinner 넣기
  addSpinner();

  // REST API를 이용하여 장소 검색
  SearchAttraction(pgNo);
};

const addSpinner = () => {
  const menuEl = document.getElementById("menu_wrap");
  const listEl = document.getElementById("placesList");
  const fragment = document.createDocumentFragment();

  removeAllChildNods(listEl);

  let div = document.createElement("div");
  div.setAttribute("class", "spinner-border text-secondary");
  listEl.setAttribute(
    "style",
    "display:flex; justify-content: center; align-items: center; height: 80%;"
  );

  fragment.appendChild(div);
  menuEl.setAttribute("style", "overflow: hidden; touch-action: none;");
  listEl.appendChild(fragment);
};

// 검색 결과 목록, 마커를 표시하는 함수
watch(
  () => areas.value,
  () => {
    const listEl = document.getElementById("placesList");
    const menuEl = document.getElementById("menu_wrap");
    const fragment = document.createDocumentFragment();
    const MapBound = new kakao.maps.LatLngBounds();

    removeAllChildNods(listEl);

    removeMarker();

    menuEl.removeAttribute("style");
    listEl.removeAttribute("style");

    for (let i = 0; i < areas.value.length; i++) {
      let position = new kakao.maps.LatLng(
        areas.value[i].latitude,
        areas.value[i].longitude
      );
      let marker = makeMarker(position);
      let itemEl = makeListItem2(areas.value[i], i);
      MapBound.extend(position);

      (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
          displayInfowindow(marker, title);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          infowindow.close();
        });

        itemEl.onmouseover = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
          infowindow.close();
        };
      })(marker, areas.value[i].title);

      fragment.appendChild(itemEl);
    }

    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    map.setBounds(MapBound);
  }
);

const makeMarker = (position) => {
  let imageSize = new kakao.maps.Size(24, 35);
  let markerImage = new kakao.maps.MarkerImage(
    VITE_KAKAO_MAP_MARKERS_URL,
    imageSize
  );
  let marker = new kakao.maps.Marker({
    position: position,
    image: markerImage,
    clickable: true,
  });

  marker.setMap(map);
  markers.value.push(marker);

  return marker;
};

const makeListItem = (area) => {
  let listItem = document.createElement("li");

  let item_div = document.createElement("div");
  let place_name = document.createElement("h6");
  place_name.innerHTML = area.title;

  let place_address = document.createElement("span");
  place_address.innerHTML = area.addr1;

  item_div.appendChild(place_name);
  item_div.appendChild(place_address);
  listItem.appendChild(item_div);

  listItem.setAttribute("class", "item my-3");

  return listItem;
};

const makeListItem2 = (area, index) => {
  let listItem = document.createElement("li");

  let item_div = document.createElement("div");
  let item_index = document.createElement("p");
  item_index.setAttribute("id", "item_index");
  item_index.innerHTML = index;
  item_index.style.display = "none";

  let place_name = document.createElement("h6");
  place_name.innerHTML = area.title;

  let place_address = document.createElement("span");
  place_address.innerHTML = area.addr1;

  item_div.appendChild(item_index);
  item_div.appendChild(place_name);
  item_div.appendChild(place_address);

  item_div.addEventListener("click", () => {
    emit("addAttraction", areas.value[Number(item_index.innerHTML)]);
    map.relayout();
  });

  listItem.appendChild(item_div);

  listItem.setAttribute("class", "item my-3");

  return listItem;
};

// 장소검색 완료 시 호출되는 callback
// const placesSearchCB = (data, status, pagination) => {
//   if (status === kakao.maps.services.Status.OK) {
//     // 정상적으로 검색 완료 시 검색 목록과 마커 표시
//     displayPlaces(data);

//     console.log("pagination: ", pagination);

//     // page navigation
//     displayPagination(pagination);
//   } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
//     alert("검색 결과가 존재하지 않습니다.");
//     return;
//   } else if (status === kakao.maps.services.Status.ERROR) {
//     alert("검색 결과 중 오류가 발생했습니다.");
//     return;
//   }
// };

// 검색 결과 목록, 마커를 표시하는 함수
// const displayPlaces = (places) => {
//   let listEl = document.getElementById("placesList"),
//     menuEl = document.getElementById("menu_wrap"),
//     fragment = document.createDocumentFragment(),
//     bounds = new kakao.maps.LatLngBounds(),
//     listStr = "";

//   removeAllChildNods(listEl); // 검색 결과 목록 내용 제거

//   removeMarker(); // 지도에 표시되고 있는 마커 제거

//   for (let i = 0; i < places.length; i++) {
//     let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
//       marker = addMarker(placePosition, i),
//       itemEl = getListItem(i, places[i]);
//     bounds.extend(placePosition);

//     (function (marker, title) {
//       kakao.maps.event.addListener(marker, "mouseover", function () {
//         displayInfowindow(marker, title);
//       });

//       kakao.maps.event.addListener(marker, "mouseout", function () {
//         infowindow.close();
//       });

//       itemEl.onmouseover = function () {
//         displayInfowindow(marker, title);
//       };

//       itemEl.onmouseout = function () {
//         infowindow.close();
//       };
//     })(marker, places[i].place_name);

//     fragment.appendChild(itemEl);
//   }

//   listEl.appendChild(fragment);
//   menuEl.scrollTop = 0;

//   map.setBounds(bounds);
// };

// 검색결과 항목을 element로 반환
// const getListItem = (index, places) => {
//   let el = document.createElement("li");
//   let itemStr =
//     `<span class="markerbg marker_">${index + 1}</span>` +
//     '<div class="info">' +
//     `<h5>${places.place_name}</h5>`;

//   if (places.road_address_name) {
//     itemStr += `<span>${places.road_address_name}</span>`;
//     itemStr += `<span>${places.address_name}</span>`;
//   } else {
//     itemStr += `<span>${places.address_name}</span>`;
//   }

//   itemStr += `<span class="tel">${places.phone}</span></div>`;

//   el.innerHTML = itemStr;
//   el.className = "item";

//   return el;
// };

// // 마커 생성 후 지도 위에 표시
// const addMarker = (position, idx, title) => {
//   let imageSrc =
//     "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png";
//   let imageSize = new kakao.maps.Size(36, 37),
//     imgOptions = {
//       spriteSize: new kakao.maps.Size(36, 691),
//       spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10),
//       offset: new kakao.maps.Point(13, 37),
//     },
//     markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
//   let marker = new kakao.maps.Marker({
//     position: position,
//     image: markerImage,
//   });

//   marker.setMap(map);
//   markers.value.push(marker);

//   return marker;
// };

const displayPagination = (pagination) => {
  let paginationEl = document.getElementById("pagination");
  let fragment = document.createDocumentFragment();

  while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild(paginationEl.lastChild);
  }

  for (let i = 1; i <= pagination.last; i++) {
    let el = document.createElement("a");
    el.innerHTML = i;

    if (i === pagination.current) {
      el.className = "on";
    } else {
      el.onclick = (function (i) {
        return function () {
          pagination.gotoPage(i);
        };
      })(i);
    }

    fragment.appendChild(el);
  }

  paginationEl.appendChild(fragment);
};

// 검색결과 목록 또는 마커 클릭 시 호출되는 함수
// infowindow에 장소명 표시
const displayInfowindow = (marker, title) => {
  let content = `<div style="padding: 5px; z-index: 1;">${title}</div>`;
  infowindow.setContent(content);
  infowindow.open(map, marker);
};

// 지도의 모든 마커 제거
const removeMarker = () => {
  markers.value.forEach((marker) => {
    marker.setMap(null);
  });
  markers.value = [];
};

// 검색 결과 목록의 자식 element 제거
const removeAllChildNods = (el) => {
  while (el.hasChildNodes()) {
    el.removeChild(el.lastChild);
  }
};
</script>

<template>
  <div class="map_wrap">
    <div
      id="map"
      style="
        width: 100%;
        height: 100%;
        position: relative;
        overflow: hidden;
      "></div>
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          <div>
            <input
              type="text"
              v-model="keyword"
              placeholder=" 키워드 입력"
              @keyup.enter="searchPlaces(1)" />
            <button @click="searchPlaces(1)">검색하기</button>
          </div>
        </div>
      </div>
      <hr />
      <ul id="placesList"></ul>
      <div id="pagination"></div>
    </div>
  </div>
</template>

<style scoped>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h6,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>
