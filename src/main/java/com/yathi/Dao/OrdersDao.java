package com.yathi.Dao;

import com.yathi.model.Orders;
import com.yathi.repository.OrdersRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation= Propagation.SUPPORTS, readOnly = true)
public class OrdersDao {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	//	Create an Order
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false)
	public Orders save(Orders order) {
//        System.out.println(Orders.BrickType);
        Orders orders = ordersRepository.save(order);
//        if(orders.getId() == 2){
//            throw new RuntimeException("AN Item is already placed with this id.");
//        }
		return orders;
	}

    public Orders selectById(Long id) {
        System.out.println("Getting called");
        Orders orders = ordersRepository.selectById(id);
        System.out.println("in dao:"+orders);
        return orders;
    }
	
	//	Get All Orders
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false)
	public List<Orders> findAll(){
		return ordersRepository.findAll();
	}

    //	Get All Orders Except dispatch
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false)
    public List<Orders> selectAll(){
        return ordersRepository.selectAll();
    }

	//	Get Single Order
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false)
	public Orders findOne(Long OrderId){
		return ordersRepository.findOne(OrderId);
	}
	
	//	Delete Order By ID
    @Transactional(propagation= Propagation.REQUIRED, readOnly = false)
	public void delete(Orders order) {
		ordersRepository.delete(order);
	}
}
