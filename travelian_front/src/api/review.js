import { localAxios } from "../utils/http-commons";

const local = localAxios();

// review list
export const getReviews = (success, fail, contentId) => {
  local.get(`/review/list/${contentId}`).then(success).catch(fail);
};

// reveiw detail
export const getReview = (success, fail, reviewId) => {
  local.get(`/review/articleNo/${reviewId}`).then(success).catch(fail);
};

// reveiw create
export const postReview = (success, fail, review) => {
  local.post(`/review`, JSON.stringify(review)).then(success).catch(fail);
};

// review modify
export const putReview = (success, fail, review) => {
  local
    .put(`/review/${review.reviewId}`, JSON.stringify(review))
    .then(success)
    .catch(fail);
};

// reveiw delete
export const deleteReview = (success, fail, reviewId) => {
  local.delete(`/review/${reviewId}`).then(success).catch(fail);
};
