package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Override
    Optional<Order> findById(Long aLong);
}
