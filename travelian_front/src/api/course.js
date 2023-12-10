import { localAxios } from "../utils/http-commons";

const local = localAxios();

export const getBoards = (success, fail) => {
  local.get(`/board`).then(success).catch(fail);
};

// course list
export const getCourses = (success, fail, offset) => {
  local.get(`/course?offset=${offset}`).then(success).catch(fail);
};

// search course list
export const getSearchCourses = (success, fail, keyword, option, offset) => {
  local
    .get(`/course/search/${keyword}?option=${option}&offset=${offset}`)
    .then(success)
    .catch(fail);
};

// course create
export const postCourse = (success, fail, course, attractions) => {
  const attractionCourseDto = {};
  attractionCourseDto.course = course;
  attractionCourseDto.attractions = attractions; //어레이 리스트
  console.log(JSON.stringify(attractionCourseDto));

  local
    .post(`/course/attractions`, JSON.stringify(attractionCourseDto))
    .then(success)
    .catch(fail);
};

// course detail
export const getCourse = (success, fail, courseId) => {
  local.get(`/course/${courseId}`).then(success).catch(fail);
};

export const getCourseAttraction = (success, fail, courseId) => {
  local.get(`/course/attractions/${courseId}`).then(success).catch(fail);
};

// course delete
export const deleteCourse = (success, fail, courseId) => {
  local.delete(`/course/${courseId}`).then(success).catch(fail);
};

// export const getByName = (success, fail, name) => {
//   local.get(`/board/name/${name}`).then(success).catch(fail);
// };

// export const getByTitle = (success, fail, title) => {
//   local.get(`/board/title/${title}`).then(success).catch(fail);
// };

export const updateHit = (success, fail, courseId) => {
  local.put(`/course/${courseId}/hit`).then(success).catch(fail);
};

export const sendCourse = (success, fail, courseId, email) => {
  local.post(`/course/mail/${courseId}`, email).then(success).catch(fail);
};

export const likeCourse = (success, fail, courseId) => {
  local.post(`/course/like/${courseId}`).then(success).catch(fail);
};
