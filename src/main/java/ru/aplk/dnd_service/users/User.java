package ru.aplk.dnd_service.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import ru.aplk.dnd_service.tables.characters.Character;

import java.util.Collection;
import java.util.List;
/////АААААААААААААААААААААААААААААА
///// НЕ ЗАБУДЬ
/////  НАСТЯ
///// ПЕРСОНАЖИ
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Column(name = "position_id")
    private Long positionId;
    //@ElementCollection
    //private List<Character> languages; //РАЗОБРАТЬСЯ С ПеРСОНАЖЕМ!!!


    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    @Override
    public String getUsername() {
        return email;
    }


    public String getEmail() {
        return email;
    }

//    public List<Language> getLanguages() { return languages;}

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}










