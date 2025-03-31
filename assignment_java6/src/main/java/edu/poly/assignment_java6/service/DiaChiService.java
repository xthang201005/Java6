package edu.poly.assignment_java6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.assignment_java6.model.DiaChiUser;
import edu.poly.assignment_java6.model.DiaChiUserDTO;
import edu.poly.assignment_java6.model.Users;
import edu.poly.assignment_java6.repository.DiaChiRepository;
import edu.poly.assignment_java6.repository.UsersRepository;


@Service
public class DiaChiService {

    @Autowired
    private DiaChiRepository diaChiRepository;
    // @Autowired
    // private AddressRepository addressRepository;

    @Autowired  
private UsersRepository usersRepository; // Thêm UsersRepository để tìm user

public void saveAddress(DiaChiUserDTO addressDTO) {
    DiaChiUser address = new DiaChiUser();

    Optional<Users> userOptional = usersRepository.findById(addressDTO.getUserId()); // Chuyển sang String
    if (userOptional.isPresent()) {
        address.setUser(userOptional.get());
    } else {
        throw new RuntimeException("User không tồn tại với ID: " + addressDTO.getUserId());
    }

    address.setThanhPho(addressDTO.getThanhPho());
    address.setQuanHuyen(addressDTO.getQuanHuyen());
    address.setPhuongXa(addressDTO.getPhuongXa());
    address.setDiaChiCuThe(addressDTO.getDiaChiCuThe());

    diaChiRepository.save(address);
}
}
