package com.perdidoseachados.perdidoseachados.Repository;

import com.perdidoseachados.perdidoseachados.Entidades.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
}
