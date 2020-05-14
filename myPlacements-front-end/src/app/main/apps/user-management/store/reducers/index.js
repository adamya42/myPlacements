import { combineReducers } from "redux";
import users from "./users.reducer";
import user from "./user.reducer";
import multiUser from "./multiuser.reducer";

const reducer = combineReducers({
  users,
  user,
  multiUser,
});

export default reducer;
