package projetoif.pi.projeto.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "TB_USER")
public class UserModel implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID UserId;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {

		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return false;
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
public UUID getUserI() {
	return UserId;	
}
public void setUserId(UUID userId) {
	this.UserId = userId;
}
public void setUserName(String username) {
this.username = username;
}
public void setUserPassword(String password) {
this.password = password;
}
}
