import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const TripViewStore = defineStore("TripViewStore", () => {
  const attraction = ref([]);
  const selected = ref({});

  const getAttraction = computed(() => {
    return attraction.value;
  });

  const getSelected = computed(() => {
    return selected.value;
  });

  // attraction 값 변경
  const setAttraction = (data) => {
    attraction.value = data;
  };

  const selectedAttraction = (data) => {
    console.log("selectedAttraction's data: ", data);
    selected.value = data;
    console.log("selected.value: ", selected.value);
  };

  return {
    attraction,
    selected,
    getAttraction,
    getSelected,
    setAttraction,
    selectedAttraction,
  };
},
{
  persist: {
    storage: localStorage,
  }
});
