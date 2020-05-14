import { combineReducers } from "redux";
import products from "./products.reducer";
import product from "./product.reducer";
import orders from "./orders.reducer";
import order from "./order.reducer";
import multiUser from "./multiuser.reducer";

const reducer = combineReducers({
  products,
  product,
  orders,
  order,
  multiUser,
});

export default reducer;
