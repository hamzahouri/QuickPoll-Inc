package org.houri.QuickPoll.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USERNAME")
    @NotEmpty
    private String username;
    @Column(name = "PASSWORD")
    @NotEmpty
    @JsonIgnore
    private String password;
    @Column(name = "FIRST_NAME")
    @NotEmpty
    private String firstname;
    @Column(name = "LAST_NAME")
    @NotEmpty
    private String lastname;
    @Column(name="ADMIN", columnDefinition="char(3)")
    @Type(type="yes_no")
    @NotEmpty
    private boolean admin;
}
