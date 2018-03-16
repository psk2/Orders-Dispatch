package com.yathi.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.yathi.exceptions.ErrorDetails;
import com.yathi.exceptions.InvalidBrickTypeException;
import com.yathi.exceptions.OrderAlreadyDispatchedException;
import com.yathi.exceptions.OrderNotFoundException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yathi.model.Orders;
import com.yathi.Dao.OrdersDao;

@RestController
@RequestMapping("/")
public class OrdersController {

	@Autowired
	OrdersDao ordersDao;
	ErrorDetails error;
	// Create an Order
	@PostMapping("/orders/create")
	public ResponseEntity<Orders> createOrder(@Valid @RequestBody Orders order) throws InvalidFormatException {
//		Orders orders = null;

		System.out.println(order.getBrickType());

		Orders orders = ordersDao.save(order);
		orders = ordersDao.selectById(orders.getId());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("StatusCode", String.valueOf(200));
		return new ResponseEntity<Orders>(orders, responseHeaders, HttpStatus.OK);
	}

	// Get All Orders
	@GetMapping("/orders/all")
	public ResponseEntity<List<Orders>> getAllOrders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("status", String.valueOf(204));
		return new ResponseEntity<List<Orders>>(ordersDao.findAll(), responseHeaders, HttpStatus.CREATED);
	}

	// Get Order By ID
	@GetMapping("/orders/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable(value = "id") Long OrderId) {
		Orders order = ordersDao.findOne(OrderId);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("status", String.valueOf(400));
		if (order == null) {
			throw new OrderNotFoundException("No order details forund for the given id-" + OrderId);
		}
		return ResponseEntity.ok().body(order);
	}

	// Update an Order
	@PutMapping("/orders/{id}")
	public ResponseEntity<Orders> updateOrder(@PathVariable(value = "id") Long OrderId,
			@Valid @RequestBody Orders orderDetails) {
		Orders order = ordersDao.findOne(OrderId);
		System.out.println(order.getBricks());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("status", String.valueOf(400));
		if (order == null) {
			return new ResponseEntity("Not Found",responseHeaders,HttpStatus.NOT_FOUND);
		}

		else if(order.isDispatch()){
			throw new OrderAlreadyDispatchedException("Order has already been Dispatched");
		}
		order.setBricks(orderDetails.getBricks());
		Orders updateOrders = ordersDao.save(order);
		return ResponseEntity.ok().body(updateOrders);
	}

	// Dispatch an Order
	@PutMapping("/dispatch/{id}")
	public ResponseEntity<Orders> updateDispatch(@PathVariable(value = "id") Long OrderId) {
		Orders order = ordersDao.findOne(OrderId);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("status", String.valueOf(204));
		if (order == null) {
			throw new OrderNotFoundException("id-" + OrderId);
		}

		else if(order.isDispatch()){
			throw new OrderAlreadyDispatchedException("Order has already been Dispatched");
		}

		else {
			order.setBricks(order.getBricks());
			order.setDispatch(true);
			Orders updateOrders = ordersDao.save(order);
			return new ResponseEntity<Orders>(updateOrders, responseHeaders, HttpStatus.OK);
		}
	}

}
