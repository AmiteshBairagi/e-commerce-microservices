package com.amitesh.order_service.Repository;

import com.amitesh.order_service.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "Select * from orders  where user_id = :userId",nativeQuery = true)
    List<Order> findByUserId(int userId);
}
