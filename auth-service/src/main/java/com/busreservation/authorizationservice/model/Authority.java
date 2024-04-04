package com.busreservation.authorizationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "authority_id")
    private Integer id;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public Authority(String authority) {
        this.authority = authority;
    }
}
