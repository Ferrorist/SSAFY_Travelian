import { localAxios } from "../utils/http-commons";

const local = localAxios();

const searchAttraction = (success, fail, sidoCode, contentTypeId, keyword) => {
  local
    .get(
      `/attraction/search?sidoCode=${sidoCode}&contentTypeid=${contentTypeId}&keyword=${keyword}`
    )
    .then(success)
    .catch(fail);
};

const getAttraction = (success, fail, contentId) => {
  local.get(`/attraction/${contentId}`).then(success).catch(fail);
};

export { searchAttraction, getAttraction };
