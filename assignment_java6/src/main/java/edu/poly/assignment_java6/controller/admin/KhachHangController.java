// package edu.poly.assignment_java5.controller.admin;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import java.util.List;
// import  edu.poly.assignment_java5.model.KhachHangVipDTO;
// import edu.poly.assignment_java5.service.HoaDonService;

// @RestController
// @RequestMapping("/api/khach-hang")
// public class KhachHangController {

//     @Autowired
//     private HoaDonService hoaDonService;

//     @GetMapping("/top-vip")
//     public ResponseEntity<List<KhachHangVipDTO>> getTop10KhachHangVip() {
//         return ResponseEntity.ok(hoaDonService.getTop10KhachHangVip());
//     }


// }


// // package edu.poly.assignment_java5.controller.admin;

// // import java.time.LocalDate;
// // import java.util.List;
// // import java.util.Map;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.format.annotation.DateTimeFormat;
// // import org.springframework.stereotype.Controller;
// // import org.springframework.ui.Model;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.RequestParam;

// // import edu.poly.assignment_java5.model.Users;
// // import edu.poly.assignment_java5.service.LoaiService;
// // import edu.poly.assignment_java5.service.SanPhamService;
// // import jakarta.servlet.http.HttpSession;

// // @Controller
// // public class ThongKeController {
// //          @Autowired
// //     SanPhamService sanPhamService;
// //     @Autowired
// //     LoaiService loaiService;
// //     @Autowired
// //     private HttpSession session;

// //     // @GetMapping("/admin/thongke")
// //     // public String thongKeKinhDoanh(Model model,
// //     //                                @RequestParam(name = "loaiId", required = false) Integer loaiId,
// //     //                                @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
// //     //                                @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
// //     //     Users currentUser = (Users) session.getAttribute("currentUser");
// //     //     if (currentUser == null || !currentUser.isVaitro()) {
// //     //         return "redirect:/";
// //     //     }

// //     //     List<Map<String, Object>> thongKeList = sanPhamService.getThongKeKinhDoanh(loaiId, startDate, endDate);
// //     //     model.addAttribute("thongKeList", thongKeList);
// //     //     model.addAttribute("loais", loaiService.getAllLoai(0, 100)); // Lấy danh sách loại hàng để lọc
// //     //     return "admin/thongke/thongke";
// //     // }
// // }

