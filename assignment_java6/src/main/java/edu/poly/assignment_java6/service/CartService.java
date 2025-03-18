package edu.poly.assignment_java6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.assignment_java6.model.GioHang;
import edu.poly.assignment_java6.repository.GioHangRepository;

@Service
public class CartService {
	@Autowired
	GioHangRepository gioHangRepository;

		//kiểm tra login
	public GioHang getCartById(Integer id) {
		return gioHangRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Giỏ hàng không tồn tại!"));
	}
}
