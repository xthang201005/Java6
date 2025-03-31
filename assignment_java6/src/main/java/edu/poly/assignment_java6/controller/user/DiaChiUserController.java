// package edu.poly.assignment_java6.controller.user;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import edu.poly.assignment_java6.model.DiaChiUser;
// import edu.poly.assignment_java6.model.DiaChiUserDTO;
// import edu.poly.assignment_java6.service.DiaChiUserService;

// @RestController
// @RequestMapping("/api/diachi")
// @CrossOrigin("*") // Cho phép gọi API từ client khác domain
// public class DiaChiUserController {
//      @Autowired
//     private DiaChiUserService diaChiKhachHangService;

//     @PostMapping("/add")
//     public ResponseEntity<?> addDiaChi(@RequestBody DiaChiUserDTO dto) {
//         try {
//             DiaChiUser diaChi = diaChiKhachHangService.addDiaChi(dto);
//             return ResponseEntity.ok(diaChi);
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }
// }
