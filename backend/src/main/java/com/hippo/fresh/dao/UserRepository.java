package com.hippo.fresh.dao;

import com.hippo.fresh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


//Dao层继承 JpaRepository<T,ID>
// T:entity层的数据类名
// x：实体类主键的类型
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    public List<User> findAll();

    @Override
    public Optional<User> findById(Long id);


    public Optional<User> findByUsername(String username);

}
