// createToast, toastComponent: Dùng để hiển thị thông báo (toast) khi người dùng thêm sản phẩm vào giỏ hàng hoặc gặp lỗi.
// setTotalCartItemsQuantity: Cập nhật tổng số sản phẩm trong giỏ hàng trên giao diện (header.js)

import createToast, { toastComponent } from "./toast.js";
import { setTotalCartItemsQuantity } from "./header.js";

// STATIC DATA
const currentUserIdMetaTag = document.querySelector(
  "meta[name='currentUserId']" // lấy thông tin người dùng hiện tại
);
const productIdMetaTag = document.querySelector("meta[name='productId']"); // lấy thông tin sản phẩm hiện tại
//
const quantityInput = document.querySelector("#cart-item-quantity"); // lấy thông tin số lượng sản phẩm
const productTitleElement = document.querySelector(".title"); // lấy thông tin tên sản phẩm

// MESSAGES // bổ sung thông báo khi thêm sản phẩm vào giỏ hàng
const REQUIRED_SIGNIN_MESSAGE = "Vui lòng đăng nhập để thực hiện thao tác!";
const SUCCESS_ADD_CART_ITEM_MESSAGE = (quantity, productTitle) =>
  `Đã thêm thành công ${quantity} sản phẩm ${productTitle} vào giỏ hàng!`;
const FAILED_ADD_CART_ITEM_MESSAGE = "Đã có lỗi truy vấn!";

// UTILS thêm sản phẩm vào giỏ hàng
async function _fetchPostAddCartItem() {
  // gửi request POST để thêm sản phẩm vào giỏ hàng
  const cartItemRequest = {
    userId: currentUserIdMetaTag.content, // id người dùng
    productId: productIdMetaTag.content, // id sản phẩm
    quantity: quantityInput.value, // số lượng sản phẩm
  };

  const response = await fetch("/cartItem", {
    method: "POST",
    headers: {
      Accept: "application/json", // chấp nhận dữ liệu trả về dạng json
      "Content-Type": "application/json", // gửi dữ liệu dạng json
    },
    body: JSON.stringify(cartItemRequest), // chuyển dữ liệu thành dạng json
  });
  return [response.status, await response.json()]; // trả về status và dữ liệu json
}

// EVENT HANDLERS
function noneSigninEvent() {
  // thông báo khi chưa đăng nhập
  createToast(toastComponent(REQUIRED_SIGNIN_MESSAGE));
}

async function buyNowBtnEvent() {
  // thêm sản phẩm vào giỏ hàng và chuyển hướng đến trang giỏ hàng
  const [status] = await _fetchPostAddCartItem();
  if (status === 200) {
    window.location.href = "/cart";
  } else if (status === 404) {
    createToast(toastComponent(FAILED_ADD_CART_ITEM_MESSAGE, "danger"));
  }
}

async function addCartItemBtnEvent() {
  // thêm sản phẩm vào giỏ hàng
  const [status] = await _fetchPostAddCartItem();
  if (status === 200) {
    createToast(
      toastComponent(
        // thông báo thêm sản phẩm vào giỏ hàng thành công
        SUCCESS_ADD_CART_ITEM_MESSAGE(
          // hiển thị thông báo thành công
          quantityInput.value, // số lượng sản phẩm
          productTitleElement.innerText // tên sản phẩm
        ),
        "success"
      )
    );
    setTotalCartItemsQuantity(quantityInput.value); // cập nhật tổng số sản phẩm trong giỏ hàng
  } else if (status === 404) {
    createToast(toastComponent(FAILED_ADD_CART_ITEM_MESSAGE, "danger"));
  }
}

// MAIN
const buyNowBtn = document.querySelector("#buy-now"); // lấy thông tin nút mua ngay
const addCartItemBtn = document.querySelector("#add-cart-item"); // lấy thông tin nút thêm vào giỏ hàng

if (currentUserIdMetaTag) {
  // kiểm tra người dùng đã đăng nhập chưa
  buyNowBtn.addEventListener("click", buyNowBtnEvent); // thêm sự kiện click vào nút mua ngay
  addCartItemBtn.addEventListener("click", addCartItemBtnEvent);
} else {
  buyNowBtn.addEventListener("click", noneSigninEvent); // thông báo khi chưa đăng nhập
  addCartItemBtn.addEventListener("click", noneSigninEvent);
}
