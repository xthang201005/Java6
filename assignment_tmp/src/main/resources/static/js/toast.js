// COMPONENTS

export function toastComponent(message, color = "primary") { 
  return `
    <div class="toast align-items-center text-white bg-${color} border-0" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
          ${message}
        </div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
    </div>
  `; // trả về component toast với message và color tùy chọn
}

// UTILS // hiển thị toast
function _showToast() { // hiển thị toast
  const hiddenToastElements = [].slice.call(document.querySelectorAll(".toast.hide")); // lấy thông tin toast ẩn
  hiddenToastElements.forEach((hiddenToastElement) => hiddenToastElement.remove()); // xóa toast ẩn

  const toastElements = [].slice.call(document.querySelectorAll(".toast:not(.hide)"));// lấy thông tin toast
  const toasts = toastElements.map((toastElement) => new bootstrap.Toast(toastElement)); // tạo toast
  toasts.forEach((toast) => toast.show()); // hiển thị toast
}

// MAIN
function createToast(toastComponent) { // tạo toast
  const toastContainerElement = document.querySelector(".toast-container"); // lấy thông tin container toast
  toastContainerElement.insertAdjacentHTML("beforeend", toastComponent); // thêm toast vào container
  _showToast(); // hiển thị toast
}

export default createToast; // xuất hàm tạo toast
