<script setup>
import { ref } from "vue";
import TripViewMap from "@/components/common/maps/TripViewMap.vue";
import { TripViewStore } from "@/stores/tripView";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { searchAttraction } from "@/api/attraction.js";

const { VITE_LOCATION_BASE_URL, VITE_LOCATION_SERVICE_KEY } = import.meta.env;

let prev_areaCode = null,
  prev_contentCode = null,
  prev_searchWord = null;
const router = useRouter();
const areaCode = ref(0);
const contentCode = ref(0);
const searchWord = ref("");

const pageNo = ref(1);
const isLoading = ref(false);
const hasSearched = ref(false);

const TripStore = TripViewStore();
const { getAttraction } = storeToRefs(TripStore);
const { setAttraction } = TripStore;

const table_fields = [
  { key: "firstImage", label: "대표이미지" },
  { key: "title", label: "관광지명" },
  { key: "addr1", label: "주소" },
];

// 관광지 검색
const SearchAttraction = () => {
  let search_id = document.getElementById("search-area");
  let search_content_id = document.getElementById("search-content-id");
  let search_keyword = document.getElementById("search-keyword");
  let btn_search = document.getElementById("btn-search");

  const success = (response) => {
    prev_areaCode = areaCode.value;
    prev_contentCode = contentCode.value;
    prev_searchWord = searchWord.value;
    console.log(response.data);
    setAttraction(response.data);
    pageNo.value = 1;

    initState();
  };

  const fail = (error) => {
    alert("오류가 발생하였습니다.\n잠시 후 다시 시도해주세요.");
    console.error(error);

    initState();
  };

  const initState = () => {
    isLoading.value = false;
    search_id.readOnly = false;
    search_content_id.readOnly = false;
    search_keyword.readOnly = false;
    btn_search.disabled = false;
    search_keyword.placeholder = "Search";
  };

  if (
    prev_areaCode != areaCode.value ||
    prev_contentCode != contentCode.value ||
    prev_searchWord != searchWord.value
  ) {
    // 검색 결과가 나오기 전까지 수정을 막음.
    search_id.readOnly = true;
    search_content_id.readOnly = true;
    search_keyword.readOnly = true;
    search_keyword.placeholder = "Seaching...";
    btn_search.disabled = true;

    isLoading.value = true;
    hasSearched.value = true;
    searchAttraction(
      success,
      fail,
      areaCode.value,
      contentCode.value,
      searchWord.value
    );
  }
};

const rowClicked = (row) => {
  router.push({
    name: "AttractionDetail",
    params: {
      contentId: row.contentId,
    },
  });
};
</script>

<template>
  <div class="container">
    <div style="height: 70px"></div>
    <div class="row d-flex">
      <h1 class="title">
        Search
        <img
          src="@/assets/images/main_icon.png"
          style="width: 30px; height: 28px"
          alt="" />
      </h1>
    </div>
    <div class="col-md-12 flex-column">
      <div class="d-flex my-3">
        <select id="search-area" class="form-select me-2" v-model="areaCode">
          <option value="0" selected>지역</option>
          <option value="1">서울</option>
          <option value="2">인천</option>
          <option value="3">대전</option>
          <option value="4">대구</option>
          <option value="5">광주</option>
          <option value="6">부산</option>
          <option value="7">울산</option>
          <option value="8">세종특별자치시</option>
          <option value="31">경기도</option>
          <option value="32">강원특별자치도</option>
          <option value="33">충청북도</option>
          <option value="34">충청남도</option>
          <option value="35">경상북도</option>
          <option value="36">경상남도</option>
          <option value="37">전라북도</option>
          <option value="38">전라남도</option>
          <option value="39">제주도</option>
        </select>
        <select
          id="search-content-id"
          class="form-select me-2"
          v-model="contentCode">
          <option value="0" selected>유형</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>

        <div id="search-container">
          <input
            id="search-keyword"
            type="search"
            placeholder="Search"
            v-model="searchWord"
            @keyup.enter="SearchAttraction" />
          <button id="btn-search" @click="SearchAttraction">Search</button>
        </div>
      </div>

      <div>
        <div class="row">
          <div class="col-md-6">
            <TripViewMap
              class="mt-3"
              style="width: 100%; height: 500px"
              :attraction="getAttraction"
              :pageNo="pageNo" />
          </div>
          <div
            class="col-md-6"
            v-if="hasSearched && getAttraction.length > 0 && !isLoading">
            <b-table
              hover
              striped
              class="mt-3"
              id="attraction_table"
              :items="getAttraction"
              :per-page="5"
              :current-page="pageNo"
              :fields="table_fields"
              @row-clicked="rowClicked">
              <template v-slot:cell(firstImage)="data">
                <b-img
                  v-if="data.value"
                  :src="data.value"
                  style="width: 100px"></b-img>
              </template>
            </b-table>

            <b-pagination
              v-model="pageNo"
              :total-rows="getAttraction.length"
              :per-page="5"
              :limit="6"
              aria-controls="attraction_table"
              style="justify-content: center">
            </b-pagination>
          </div>
          <div
            class="col-md-6 spinner-div"
            v-else-if="hasSearched && isLoading">
            <b-spinner style="width: 6rem; height: 6rem"></b-spinner>
          </div>
        </div>
      </div>
    </div>
    <div style="height: 70px"></div>
  </div>
</template>

<style scoped>
#search-container {
  display: flex;
}
#search-keyword {
  background-color: #ffffff;
  border: 1px solid black;
  -webkit-box-flex: 2.5;
  -ms-flex: 2.5;
  flex: 2.5;
  outline: none;
  color: #000000;
  min-height: 42.4px;
  padding-left: 15px;
  width: 400px;
}
#btn-search {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  border: none;
  display: inline-block;
  padding: 10px 30px;
  background-color: #4b208c;
  color: #ffffff;
  border-radius: 0;
  font-size: 15px;
  text-transform: uppercase;
}

#search-area {
  border: 1px solid;
  border-radius: 0%;
}

#search-content-id {
  border: 1px solid;
  border-radius: 0%;
}

.spinner-div {
  display: flex;
  justify-content: center;
  align-items: center;
}
.spinner-border {
  color: #4b208c;
  --bs-spinner-animation-speed: 0.5s;
  --bs-spinner-border-width: 0.5em;
}
</style>
