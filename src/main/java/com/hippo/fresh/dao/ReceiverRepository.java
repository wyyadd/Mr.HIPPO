package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {

    List<Receiver> findAllByUserId(Long id);

}
