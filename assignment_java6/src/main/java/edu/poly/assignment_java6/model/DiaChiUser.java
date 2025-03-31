package edu.poly.assignment_java6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

  
@Entity
@Table(name = "DiaChiKhachHang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiaChiUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDiaChi;

    @ManyToOne
    @JoinColumn(name = "id_user") 
    private Users user; 

    private String thanhPho;
    private String quanHuyen;
    private String phuongXa;
    private String diaChiCuThe;
}

