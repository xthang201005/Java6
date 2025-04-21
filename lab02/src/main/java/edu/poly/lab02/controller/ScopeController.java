package edu.poly.lab02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ScopeController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpSession session;
    @Autowired
    ServletContext servletContext;

    @RequestMapping("/scope")
    public String hello(Model model ) {
        model.addAttribute("a", " i am  in model");
        request.setAttribute("b", " i am in request");
        session.setAttribute("c", " i am in session");
        servletContext.setAttribute("d", " i am in servletContext");
        return "scope";
    }
    model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);
model.addAttribute("sanphams", sanPhamPage.getContent()); // Danh sách user
				model.addAttribute("totalProducts", sanPhamPage.getTotalElements()); // Danh sách user
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("totalPages", sanPhamPage.getTotalPages());
				model.addAttribute("currentPage", page); // Trang hiện tại
				model.addAttribute("order", order);
				model.addAttribute("priceRanges", priceRanges);
				model.addAttribute("loais", loaiService.getAllLoai(0, 5));
				model.addAttribute("loai", loai);

}
