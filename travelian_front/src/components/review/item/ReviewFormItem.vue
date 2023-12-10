<script setup>
import { ref } from "vue";
const prop = defineProps({ type: String, review: Object });
const emit = defineEmits(["evtProcess"]);

const refTitle = ref(null);
const refContent = ref(null);

const validate1 = () => {
  let isValid = true;
  let errMsg = "";
  const review = prop.review;

  !review.subject
    ? ((isValid = false),
      (errMsg = "제목을 입력해주세요."),
      refTitle.value.focus())
    : !review.content
    ? ((isValid = false),
      (errMsg = "내용을 입력해주세요."),
      refContent.value.focus())
    : (isValid = true);

  if (!isValid) {
    alert(errMsg);
  } else {
    console.log(review);
    emit("evtProcess");
  }
};

const initParams = () => {
  prop.review.subject = "";
  prop.review.content = "";
};
</script>

<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-body">
        <div class="mb-3">
          <label for="title" class="form-label text-purple">제목:</label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            v-model="prop.review.subject"
            ref="refTitle" />
        </div>
        <div class="mb-3">
          <label for="content" class="form-label text-purple">내용:</label>
          <textarea
            class="form-control"
            id="content"
            name="content"
            rows="7"
            v-model="prop.review.content"
            ref="refContent"></textarea>
        </div>
        <div class="col-auto text-center">
          <button
            type="button"
            class="btn btn-purple mb-3"
            v-if="type === 'create' || type === 'modify'"
            v-on:click="validate1">
            {{ type === "create" ? "글 작성" : "글 수정" }}
          </button>
          <button
            type="reset"
            class="btn btn-danger mb-3 ms-2"
            @click="initParams">
            초기화
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 600px;
}

.card {
  border: none;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-body {
  background-color: #eae6f5;
  border-radius: 8px;
}

.text-purple {
  color: #8a2be2;
}

#content {
  resize: none;
}

.btn-purple {
  background-color: #8a2be2;
  border-color: #8a2be2;
  color: #fff;
}

.btn-purple:hover {
  background-color: #6a1b9a;
  border-color: #6a1b9a;
}

.btn-danger {
  background-color: #8b8b8b;
  border-color: #8b8b8b;
}

.btn-danger:hover {
  background-color: #3b3b3b;
  border-color: #3b3b3b;
}
.btn-purple:active {
  color: white;
  background-color: #310050;
  box-shadow: 0 5px rgb(195, 182, 207);
  transform: translateY(4px);
}
.btn-danger:active {
  color: white;
  background-color: #202020;
  box-shadow: 0 5px rgb(212, 212, 212);
  transform: translateY(4px);
}
</style>
