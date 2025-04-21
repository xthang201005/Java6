package edu.poly.lab03.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class student {
    
       String FullName;

       //  @NotBlank(message = "Email không được để trống")
       //  @Email(message = "Email không đúng định dạng")
       String Email;

       Double Mark;
       Boolean gender;
       String country;
}
