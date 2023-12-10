import { localAxios } from "@/utils/http-commons.js";

const local = localAxios();

const login = (user, success, fail) => {
  console.log(user);
  local.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
};

const regist = (user, success, fail) => {
  local.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail);
};

const checkId = (userId, success, fail) => {
  console.log(userId);
  console.log(typeof userId);
  local.post(`/user/Check`, userId).then(success).catch(fail);
};

const findpwd = (success, fail, user) => {
  local.post(`/user/pwd`, JSON.stringify(user)).then(success).catch(fail);
};
export { login, regist, checkId, findpwd };
