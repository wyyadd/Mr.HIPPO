package com.hippo.fresh.dao;

import com.hippo.fresh.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product>,PagingAndSortingRepository<Product,Long>{

    //根据id判断商品是否存在
    @Override
    boolean existsById(Long aLong);

    //根据单个商品id返回商品的需要信息
    @Query(value = "select id,name,picture,picture_url,category_first,category_second,price,stock,detail,status,sales_amount,score from product x where x.id =?1 ",nativeQuery = true)
    Map<String,Object> findSomeInformationById(Long id);

    @Override
    List<Product> findAllById(Iterable<Long> iterable);

    //根据参数获取商品列表
   // @Query(value = "select id,name,picture,price,stock,salesAmount from Product")
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);


//自定义过滤信息
    public static Specification<Product> getSpec(String productName, String categoryFirst, String categorySecond, int sort, int order, int upperBound, int lowerBound){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Root<OrderImpl> order = criteria.from(OrderImpl.class);
                List<Predicate> predicates = new ArrayList<Predicate>();
                //种类信息过滤
                if(categoryFirst != null)
                    predicates.add(criteriaBuilder.equal(root.get("categoryFirst").as(String.class),categoryFirst));
                if(categorySecond != null)
                    predicates.add(criteriaBuilder.equal(root.get("categorySecond").as(String.class),categorySecond));
                //商品名信息过滤
                if(productName != null)
                    predicates.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+productName+"%"));
                //排序类型，排序方式信息指定
//                switch (sort){
//                    case 1:{
//                        if(order == 1)
//                            query.orderBy(criteriaBuilder.desc(root.get("salesAmount")));
//                        else
//                            query.orderBy(criteriaBuilder.asc(root.get("salesAmount")));
//                    } break;
//                    case 2:{
//                        if(order == 1)
//                            query.orderBy(criteriaBuilder.desc(root.get("price")));
//                        else
//                            query.orderBy(criteriaBuilder.asc(root.get("price")));
//                    }break;
//                    case 3:{
//                        if(order == 1)
//                            query.orderBy(criteriaBuilder.desc(root.get("stock")));
//                        else
//                            query.orderBy(criteriaBuilder.asc(root.get("stock")));
//                    }break;
//                    default:query.orderBy(criteriaBuilder.desc(root.get("salesAmount")));
//                }
                //价格上下界指定
                if(upperBound != -1)
                    predicates.add(criteriaBuilder.lessThan(root.get("price"),upperBound));
                if(lowerBound != -1)
                    predicates.add(criteriaBuilder.greaterThan(root.get("price"),lowerBound));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}

