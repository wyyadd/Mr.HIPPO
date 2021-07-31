package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {

    //根据用户id查找该用户所有收件人信息
    List<Receiver> findAllByUserIdOrderByUpdateTimeDesc(Long aLong);

    //根据用户id查找该用户所有收件人部分信息：id、name、address、phone
    @Query(value = "select id,name,address,phone from receiver x where x.user_id =?1 ",nativeQuery = true)
    List<Map<String,Object>> findSomeInformationByUserId(Long id);


    @Override
    Optional<Receiver> findById(Long aLong);

}