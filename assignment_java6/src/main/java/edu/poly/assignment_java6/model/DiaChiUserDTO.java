    package edu.poly.assignment_java6.model;
    import org.springframework.stereotype.Component;
    
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Component //Thêm annotation @Component vào class để Spring có thể quét và quản lý nó:

    public class DiaChiUserDTO {
        private String userId;  // Đổi từ Long -> String

        private String thanhPho;
        private String quanHuyen;
        private String phuongXa;
        private String diaChiCuThe;
    }
