package edu.poly.assignment_java5.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import edu.poly.assignment_java5.model.KhachHangVipDTO;
import edu.poly.assignment_java5.model.SanPham;
import edu.poly.assignment_java5.model.Users;
import edu.poly.assignment_java5.service.HoaDonService;
import edu.poly.assignment_java5.service.LoaiService;
import edu.poly.assignment_java5.service.SanPhamService;


@Controller
public class SanPhamController {
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	private HttpSession session;
	@Autowired
	LoaiService loaiService;
	 @Autowired
    private HoaDonService hoaDonService;

	@GetMapping("/admin/sanpham")
	public String sanPhamManager(Model model, @RequestParam(defaultValue = "0", name = "page") int page) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || !currentUser.isVaitro()) {
			return "redirect:/";
		}

		Page<SanPham> sanPhamPage = sanPhamService.getAllSanPham(page, 5);

		model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
		model.addAttribute("currentPage", page); // Trang hiện tại
		model.addAttribute("totalPages", sanPhamPage.getTotalPages());
		return "admin/sanpham/sanphamManager";
	}

	@GetMapping("/admin/sanpham/create")
	public String userCreate(Model model, @ModelAttribute("sanpham") SanPham sanPham) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || !currentUser.isVaitro()) {
			return "redirect:/";
		}
		model.addAttribute("loais", loaiService.getAllLoai(0, 100));
		return "admin/sanpham/createSanPham";
	}

	@PostMapping("/admin/sanpham/create")
	public String sanphamInsert(Model model, @ModelAttribute("sanpham") SanPham sanPham,
			@RequestParam("image") MultipartFile image) {
		try {
			Users currentUser = (Users) session.getAttribute("currentUser");
			if (currentUser == null || !currentUser.isVaitro()) {
				return "redirect:/";
			}
			sanPhamService.create(sanPham, image);
			model.addAttribute("successMessage", "Tạo sản phẩm thành công");
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		model.addAttribute("loais", loaiService.getAllLoai(0, 100));
		return "admin/sanpham/createSanPham";
	}

	@GetMapping("/admin/sanpham/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || !currentUser.isVaitro()) {
			return "redirect:/";
		}
		try {
			SanPham sanPham = sanPhamService.getSanPhamById(id);
			model.addAttribute("sanpham", sanPham);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		model.addAttribute("loais", loaiService.getAllLoai(0, 100));
		return "admin/sanpham/updateSanPham";
	}
	

	@PostMapping("/admin/sanpham/update/{id}")
	public String updateUser(Model model, @PathVariable("id") Integer id,
			@ModelAttribute("sanpham") SanPham updatedSanPham,
			@RequestParam(name = "image", required = false) MultipartFile image) {
		try {
			Users currentUser = (Users) session.getAttribute("currentUser");
			if (currentUser == null || !currentUser.isVaitro()) {
				return "redirect:/";
			}
			model.addAttribute("sanpham", sanPhamService.updateSanPham(id, updatedSanPham, image));
			model.addAttribute("successMessage", "Cập nhật sản phẩm thành công");
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		model.addAttribute("loais", loaiService.getAllLoai(0, 100));
		return "admin/sanpham/updateSanPham";
	}

	@GetMapping("/admin/sanpham/delete/{id}")
	public String deleteSanPham(RedirectAttributes redirectAttributes, @PathVariable("id") Integer id) {
		try {
			sanPhamService.deleteSanPham(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/admin/sanpham";
	}


	@GetMapping("/admin/thongke")
	@ResponseBody
	public List<Object[]> getThongKeDoanhThu() {
    return sanPhamService.getThongKeDoanhThuTheoLoai();

	}
	@GetMapping("/admin/thongke-view")
public String viewThongKe(Model model) {
    model.addAttribute("thongKeList", sanPhamService.getThongKeDoanhThuTheoLoai());
    return "admin/thongke/thongke";
}
///admin/khachhang-vip
@GetMapping("/admin/khachhang-vip")
public String viewTop10KhachHangVip(Model model) {
    // Lấy danh sách top 10 khách hàng VIP từ service
    List<KhachHangVipDTO> khachHangVipList = hoaDonService.getTop10KhachHangVip();

    // Đưa danh sách vào model để truyền sang view
    model.addAttribute("khachHangVipList", khachHangVipList);

    // Trả về tên view để hiển thị danh sách khách hàng VIP
    return "admin/thongke/khachhangvip";
}




}
