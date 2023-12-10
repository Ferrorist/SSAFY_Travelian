import { localAxios } from "../utils/http-commons";

const local = localAxios();

// theme list
export const getThemeList = (success, fail, category) => {
  local
    .get(`/attraction/category/${category}?offset=0`)
    .then(success)
    .catch(fail);
};

// board list
export const getBoards = (success, fail) => {
  local.get(`/board`).then(success).catch(fail);
};

// board detail
export const getBoard = (success, fail, articleNo) => {
  local.get(`/board/articleNo/${articleNo}`).then(success).catch(fail);
};

// board create
export const postBoard = (success, fail, board) => {
  local.post(`/board`, JSON.stringify(board)).then(success).catch(fail);
};

// board modify
export const putBoard = (success, fail, board) => {
  local
    .put(`/board/${board.articleNo}`, JSON.stringify(board))
    .then(success)
    .catch(fail);
};

// board delete
export const deleteBoard = (success, fail, articleNo) => {
  local.delete(`/board/${articleNo}`).then(success).catch(fail);
};

export const getByName = (success, fail, name) => {
  local.get(`/board/name/${name}`).then(success).catch(fail);
};

export const getByTitle = (success, fail, title) => {
  local.get(`/board/title/${title}`).then(success).catch(fail);
};

export const updateHit = (success, fail, articleNo) => {
  local.put(`/board/${articleNo}/hit`).then(success).catch(fail);
};
