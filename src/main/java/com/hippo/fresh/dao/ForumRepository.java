package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Favorite;
import com.hippo.fresh.entity.Forum;
import com.tdunning.math.stats.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum, Long> {

    Page<Forum> findAll(Pageable pageable);
}
