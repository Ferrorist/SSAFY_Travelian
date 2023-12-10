<script setup>
import { onMounted, watch, computed } from "vue";
const { VITE_KAKAO_MAP_MARKERS_URL } = import.meta.env;
const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;
let map;
let imageSize, markerImage;
let marker;

const props = defineProps({ attraction: Object });

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
  console.log("attraction map: ", props.attraction);
  loadMarker();
};

// computed(() => {
//   loadMarker();
// })

// watch(
//   () => props.attraction,
//   () => {
//     loadMarker();
//   }
// );

const loadMarker = () => {
  marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(
      props.attraction.latitude,
      props.attraction.longitude
    ),
    image: markerImage,
  });

  marker.setMap(map);
  map.setCenter(
    new kakao.maps.LatLng(props.attraction.latitude, props.attraction.longitude)
  );
};
</script>

<template>
  <div id="map" style="border-radius: 20px"></div>
</template>

<style scoped></style>
