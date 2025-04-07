package com.example.api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
@ToString(exclude = {"messages"})
@EqualsAndHashCode(exclude = {"messages"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, unique=true)
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_online")
    private boolean isOnline;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user1")
    private List<Room> roomsAsUser1;

    @OneToMany(mappedBy = "user2")
    private List<Room> roomsAsUser2;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
}
