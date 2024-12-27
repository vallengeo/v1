package com.vallengeo.portal.repository;

import com.vallengeo.portal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<UserDetails> findByEmailAndAtivoIsTrue(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCodigoAcesso(String codigoAcesso);
}