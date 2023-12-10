<script setup>
import { useRouter } from "vue-router";
const prop = defineProps({ type: String, attraction: Object });
const attraction = prop.attraction;
const router = useRouter();
var isValid = true;

const getImg = () => {
  let imgLink = attraction.firstImage;
  if (imgLink === "") {
    if (attraction.category === "drink") {
      imgLink = "http://localhost:5173/src/assets/images/drink_none.jpg";
    } else if (attraction.category === "star") {
      imgLink = "http://localhost:5173/src/assets/images/star_none.jpg";
    } else if (attraction.category === "camping") {
      imgLink = "http://localhost:5173/src/assets/images/camping_none.jpg";
    } else {
      imgLink = "http://localhost:5173/src/assets/images/camping_none.jpg";
    }
  }

  return imgLink;
};

const readMore = () => {
  window.scrollTo(0, 0);
  router.push({
    name: "AttractionDetail",
    params: {
      contentId: attraction.contentId,
    },
  });
};
</script>

<template>
  <div class="col-4">
    <div class="card">
      <img :src="getImg()" />
      <div class="card-body">
        <h5 class="card-title">{{ attraction.title }}</h5>
        <p class="card-text">{{ attraction.addr1 }}</p>
        <button @click="readMore">자세히</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card {
  border-radius: 10px;
  margin-top: 5%;
  margin-bottom: 5%;
}
.card img {
  aspect-ratio: 1/1;
  border-radius: 10px 10px 0px 0px;
}
.card-body button {
  float: right;
  border: none;
  display: inline-block;
  padding: 12px 45px;
  background-color: rgb(108, 64, 175);
  color: #ffffff;
  border-radius: 10px;
  margin-top: 35px;
}

.card-body button:hover {
  background-color: rgb(192, 164, 233);
}
</style>
