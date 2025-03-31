package edu.poly.assignment_java6.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import edu.poly.assignment_java6.model.Users;
import edu.poly.assignment_java6.service.HoaDonService;
import edu.poly.assignment_java6.service.LoaiService;
import edu.poly.assignment_java6.service.SanPhamService;
import edu.poly.assignment_java6.service.UserService;

@Controller
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	LoaiService loaiService;
	@Autowired
	SanPhamService sanPhamService;
	@Autowired
	HoaDonService hoaDonService;
	@Autowired
	private HttpSession session;

	@GetMapping("/admin")
	public String home(Model model) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || currentUser.getVaitro() != 1) {
			return "redirect:/";
		}

		model.addAttribute("totalUsers", userService.getAllUsers(0, 1000).getTotalElements());
		model.addAttribute("totalCategories", loaiService.getAllLoai(0, 1000).getTotalElements());
		model.addAttribute("totalProducts", sanPhamService.getAllSanPham(0, 1000).getTotalElements());
		model.addAttribute("totalOrders", hoaDonService.countReceivedOrders());
		return "admin/user/userManager";		
	}

	@GetMapping("/admin/user")
	public String userManager(Model model, @RequestParam(defaultValue = "0", name = "page") int page, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || currentUser.getVaitro() != 1) {
			redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền truy cập vào trang này.");
			
			// Lấy trang trước đó từ Header Referer
			String referer = request.getHeader("Referer");
			return "redirect:" + (referer != null ? referer : "/admin/loai"); // Nếu không lấy được referer, mặc định về /admin/loai
		}
		Page<Users> userPage = userService.getAllUsers(page, 8);

		model.addAttribute("users", userPage.getContent()); // Danh sách user
		model.addAttribute("currentPage", page); // Trang hiện tại
		model.addAttribute("totalPages", userPage.getTotalPages());
		return "admin/user/userManager";
	}

	@GetMapping("/admin/user/create")
	public String userCreate(Model model, @ModelAttribute("user") Users user, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || currentUser.getVaitro() != 1) {
			redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền truy cập vào trang này.");
			
			// Lấy trang trước đó từ Header Referer
			String referer = request.getHeader("Referer");
			return "redirect:" + (referer != null ? referer : "/admin/loai"); // Nếu không lấy được referer, mặc định về /admin/loai
		}
		return "admin/user/createUser";
	}

	@PostMapping("/admin/user/create")
	public String userInsert(Model model, @ModelAttribute("user") Users user,@RequestParam("vaitro") int vaitro, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			Users currentUser = (Users) session.getAttribute("currentUser");
			if (currentUser == null || currentUser.getVaitro() != 1) {
				redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền truy cập vào trang này.");
				
				// Lấy trang trước đó từ Header Referer
				String referer = request.getHeader("Referer");
				return "redirect:" + (referer != null ? referer : "/admin/loai"); // Nếu không lấy được referer, mặc định về /admin/loai
			}
			user.setVaitro(vaitro);
			userService.register(user);
			model.addAttribute("successMessage", "Tạo tài khoản thành công");
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "admin/user/createUser";
	}

	@GetMapping("/admin/user/edit/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model , RedirectAttributes redirectAttributes, HttpServletRequest request) {
		Users currentUser = (Users) session.getAttribute("currentUser");
		if (currentUser == null || currentUser.getVaitro() != 1) {
			redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền truy cập vào trang này.");
			
			// Lấy trang trước đó từ Header Referer
			String referer = request.getHeader("Referer");
			return "redirect:" + (referer != null ? referer : "/admin/loai"); // Nếu không lấy được referer, mặc định về /admin/loai
		}
		try {
			Users user = userService.getUserById(id);
			model.addAttribute("user", user);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "admin/user/updateUser";
	}

	@PostMapping("/admin/user/update/{id}")
	public String updateUser(Model model, @PathVariable("id") String id, @ModelAttribute("user") Users updatedUser, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		try {
			Users currentUser = (Users) session.getAttribute("currentUser");
			if (currentUser == null || currentUser.getVaitro() != 1) {
				redirectAttributes.addFlashAttribute("errorMessage", "Bạn không có quyền truy cập vào trang này.");
				
				// Lấy trang trước đó từ Header Referer
				String referer = request.getHeader("Referer");
				return "redirect:" + (referer != null ? referer : "/admin/loai"); // Nếu không lấy được referer, mặc định về /admin/loai
			}
			userService.updateUser(id, updatedUser);
			model.addAttribute("successMessage", "Cập nhật tài khoản thành công");
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "admin/user/updateUser";
	}

	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(Model model, @PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		try {
			userService.deleteUser(id);
			redirectAttributes.addFlashAttribute("successMessage", "Xóa tài khoản thành công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/admin/user";
	}

}
