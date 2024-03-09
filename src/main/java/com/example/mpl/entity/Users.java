package com.example.mpl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class Users implements Serializable {

    private static final long serialVersionUID = 7630269547387051923L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    @NotNull(message = "First Name should not ne null")
    @NotEmpty(message = "First Name should not be Empty")
    private String firstName;

    @Column(name = "lastname")
    @NotNull(message = "Last Name should not ne null")
    @NotEmpty(message = "Last Name should not be Empty")
    private String lastName;

    @Column(name = "username", unique = true)
    @NotNull(message = "Username should not ne null")
    @NotEmpty(message = "Username should not be Empty")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password should not ne null")
    @NotEmpty(message = "Password should not be Empty")
    private String password;

    @Column(name = "enabled",columnDefinition = "char(1) default 1")
    private boolean enabled;

    @Column(name = "accountNonExpired",columnDefinition = "char(1) default 1")
    private boolean accountNonExpired;

    @Column(name = "credentialsNonExpired",columnDefinition = "char(1) default 1")
    private boolean credentialsNonExpired;

    @Column(name = "accountNonLocked",columnDefinition = "char(1) default 1")
    private boolean accountNonLocked;

    @Column(name = "creationDateTime")
    private Date createDateTime;

    @Column(name = "updatedDateTime")
    private Date updateDateTime;

    @Column(name = "lastOnlineDateTime")
    private Date lastOnline;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })

    @Fetch(value= FetchMode.SELECT)
    private Set<Authorities> authorities = new HashSet<>();

}
