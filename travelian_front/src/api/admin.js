import { localAxios } from "../utils/http-commons";

const local = localAxios();

const getUserList = (success, fail) => {
  local.get(`/admin`).then(success).catch(fail);
};

const createUser = (success, fail, User) => {
  local.post(`/admin`, JSON.stringify(User)).then(success).catch(fail);
}
export { getUserList, createUser };
