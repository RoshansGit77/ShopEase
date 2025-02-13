/*
 * package com.ecom.config;
 * 
 * import java.util.Collection;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.ecom.entity.UserDetail;
 * 
 * public class CustomUser implements UserDetails {
 * 
 * private UserDetail user;
 * 
 * public CustomUser(UserDetail user) { super(); this.user = user; }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
 * TODO Auto-generated method stub return null; }
 * 
 * @Override public String getPassword() { // TODO Auto-generated method stub
 * return user.getPassword(); }
 * 
 * @Override public String getUsername() { // TODO Auto-generated method stub
 * return user.getEmail(); }
 * 
 * public boolean isAccountNonExpired() { // TODO Auto-generated method stub
 * return true; }
 * 
 * public boolean isAccountNotLocked() { // TODO Auto-generated method stub
 * return false; }
 * 
 * public boolean isCredentialsNonExpired() { // TODO Auto-generated method stub
 * return false; }
 * 
 * public boolean isEnabled() { // TODO Auto-generated method stub return false;
 * }
 * 
 * }
 */