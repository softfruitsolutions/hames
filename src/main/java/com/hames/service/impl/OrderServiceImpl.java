package com.hames.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hames.service.GenericServiceImpl;
import com.hames.service.OrderService;

@Repository
public abstract class OrderServiceImpl<T> extends GenericServiceImpl<T> implements OrderService<T>{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
}
