<script setup>
import { ref, computed, onMounted } from "vue";
const images = [
  "src/assets/images/beach.jpg",
  "src/assets/images/busan_gangseo.jpg",
  "src/assets/images/gwangan-night.jpg",
];

const timer = ref(null);
const currentIndex = ref(0);

const startSlide = () => {
  timer.value = setInterval(next, 2000);
};

const next = () => {
  clearInterval(timer.value);
  currentIndex.value += 1;
  startSlide();
}

const prev = () => {
  clearInterval(timer.value);
  currentIndex.value -= 1;
  startSlide();
}

const currentImg = computed(() => {
  return images[Math.abs(currentIndex.value) % images.length];
});

onMounted(() => {
  startSlide();
});
</script>

<template>
  <div class="hero_area">
    <section class="slider_section">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <div class="detail_box">
              <h1>
                Travelian <br />
                Travelian <br />
                Travelian
              </h1>
              <p>
                여행의 경계를 넘어 🔮<br />여정의 순간을 담아내는 새로운 여행
                동반자, "Travelian"입니다
              </p>
            </div>
          </div>
          <div class="col-lg-5 col-md-6 offset-lg-1">
            <div class="img_content">
              <TransitionGroup name="move" tag="div" class="img_container">
                <div v-for="i in [currentIndex]" :key="i" class="img-box">
                  <img :src="currentImg" />
                </div>
                <a class="carousel-control-prev" @click="prev">
                  <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" @click="next">
                  <span class="sr-only">Next</span>
                </a>
              </TransitionGroup>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<style scoped>
.move-enter-active,
.move-leave-action {
  transition: all 1.5s ease;
  visibility: visible;
  opacity: 1;
}

.move-enter,
.move-leave-to {
  visibility: hidden;
  position: absolute;
  opacity: 0;
}
</style>
