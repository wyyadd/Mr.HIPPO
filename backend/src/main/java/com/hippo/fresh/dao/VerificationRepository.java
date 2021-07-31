package com.hippo.fresh.dao;

import com.hippo.fresh.entity.User;
import com.hippo.fresh.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, Long> {

}
