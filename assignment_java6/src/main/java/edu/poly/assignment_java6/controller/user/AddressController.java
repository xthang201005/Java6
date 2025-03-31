package edu.poly.assignment_java6.controller.user;

import edu.poly.assignment_java6.model.DiaChiUser;
import edu.poly.assignment_java6.model.DiaChiUserDTO;
import edu.poly.assignment_java6.repository.DiaChiRepository;
import edu.poly.assignment_java6.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private DiaChiService addressService;
    @Autowired
    private DiaChiRepository addressRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DiaChiUser>> getUserAddresses(@PathVariable Long userId) {
    return ResponseEntity.ok(addressRepository.findByUser_IdUser(userId.toString()));
    
}


    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveAddress(@RequestBody DiaChiUserDTO addressDTO) {
        addressService.saveAddress(addressDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Địa chỉ đã được lưu thành công!");
        return ResponseEntity.ok(response);
    }
    
}

