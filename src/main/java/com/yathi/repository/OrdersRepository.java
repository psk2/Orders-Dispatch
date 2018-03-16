package com.yathi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yathi.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT id,bricks,brickType FROM orders", nativeQuery = true)
    List<Orders> selectAll();

    @Query(value = "SELECT id,bricks,brick_Type FROM orders Where id = ?1", nativeQuery = true)
    Orders selectById(Long id);

}
