<script setup>
import { mapState } from "pinia";
import { ref, onMounted, watch } from "vue";
const { VITE_KAKAO_MAP_MARKERS_URL } = import.meta.env;
const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;
let map;
let imageSize, markerImage;
const markers = ref([]);
const length = ref(0);
const props = defineProps({
  attraction: Object,
  selectedAttraction: Object,
  pageNo: Number,
});

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
  };
  map = new kakao.maps.Map(container, options);
  imageSize = new kakao.maps.Size(24, 35);
  markerImage = new kakao.maps.MarkerImage(
    VITE_KAKAO_MAP_MARKERS_URL,
    imageSize
  );
};

watch(
  () => props.attraction.value,
  () => {
    length.value = props.attraction.length;
    const MapBound = new kakao.maps.LatLngBounds();
    deleteMarker();
    for (let i = 0; i < Math.min(length.value, 5); i++) {
      let position = new kakao.maps.LatLng(
        props.attraction[i].latitude,
        props.attraction[i].longitude
      );
      let marker = new kakao.maps.Marker({
        map: map,
        title: props.attraction[i].title,
        position: position,
        image: markerImage,
        clickable: true,
      });

      markers.value.push(marker);
      MapBound.extend(position);
    }

    loadMarker();
    map.setBounds(MapBound);
  },
  { deep: true }
);

watch(
  () => props.selectedAttraction,
  () => {
    console.log("selected!");
    console.log("selectedAttraction: ", props.selectedAttraction);
    let moveLatLng = new kakao.maps.LatLng(
      props.selectedAttraction.latitude,
      props.selectedAttraction.longitude
    );

    map.panTo(moveLatLng);
  },
  { deep: true }
);

watch(
  () => props.pageNo,
  () => {
    const MapBound = new kakao.maps.LatLngBounds();
    let pageNo = props.pageNo;
    deleteMarker();
    for (
      let i = (pageNo - 1) * 5;
      i < Math.min(length.value, pageNo * 5);
      i++
    ) {
      console.log(`${i}번째 area: `, props.attraction[i]);
      let position = new kakao.maps.LatLng(
        props.attraction[i].latitude,
        props.attraction[i].longitude
      );
      let marker = new kakao.maps.Marker({
        map: map,
        title: props.attraction[i].title,
        position: position,
        image: markerImage,
        clickable: true,
      });

      markers.value.push(marker);
      MapBound.extend(position);
    }

    loadMarker();
    map.setBounds(MapBound);
  },
  { deep: true }
);

const loadMarker = () => {
  markers.value.forEach((marker) => {
    marker.setMap(map);
  });
};

const deleteMarker = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => {
      marker.setMap(null);
    });
  }
  markers.value = [];
};
</script>

<template>
  <div id="map" style="border-radius: 20px"></div>
</template>

<style scoped></style>
