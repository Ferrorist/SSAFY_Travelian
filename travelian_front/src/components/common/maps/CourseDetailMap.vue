<script setup>
import { ref, onMounted, watch } from "vue";
const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;
const { VITE_KAKAO_MAP_MARKERS_URL } = import.meta.env;
let map, infowindow;
let imageSize, markerImage;
const markers = ref([]);
const props = defineProps({ attractions: Object });

onMounted(() => {
  const Map_script = document.createElement("script");
  Map_script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
  Map_script.onload = () =>
    kakao.maps.load(() => {
      initMap();
    });
  document.head.appendChild(Map_script);
});

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(37.500613, 127.036431),
    level: 5,
  };
  map = new kakao.maps.Map(container, options);
  imageSize = new kakao.maps.Size(24, 35);
  markerImage = new kakao.maps.MarkerImage(
    VITE_KAKAO_MAP_MARKERS_URL,
    imageSize
  );
  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  loadMarker();
};

const loadMarker = () => {
  const MapBound = new kakao.maps.LatLngBounds();

  props.attractions.forEach((area) => {
    let position = new kakao.maps.LatLng(area.latitude, area.longitude);

    let marker = makeMarker(position);
    MapBound.extend(position);
    (function (marker, title) {
      kakao.maps.event.addListener(marker, "mouseover", () =>
        displayInfowindow(marker, title)
      );
      kakao.maps.event.addListener(marker, "mouseout", () =>
        infowindow.close()
      );
      kakao.maps.event.addListener(marker, "click", () => {
        const url =
          window.location.href.split("/course")[0] +
          `/detail/${area.contentId}`;
        window.open(url);
      });
    })(marker, area.title);
  });

  map.setBounds(MapBound);
};

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

const displayInfowindow = (marker, title) => {
  let content = `<div style="padding: 5px; z-index: 1; min-height: 40px; font-size: 13px; display: flex; justify-content: center;">${title}</div>`;
  infowindow.setContent(content);
  infowindow.open(map, marker);
};
</script>

<template>
  <div id="map"></div>
</template>

<style scoped></style>
