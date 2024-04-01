package dev.Demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class register {

    @Id
    private String id;

    @NotNull( message = "userName cannot be null")
    private String userName;

    @NotNull( message = "Email cannot be null")
    private String email;

    @NotNull( message = "password cannot be null")
    private String password;

    @NotNull ( message = "confirm_password cannot be null")
    private String confirm_password;

    private Date createdAt;

    private Date updatedAt;

}
