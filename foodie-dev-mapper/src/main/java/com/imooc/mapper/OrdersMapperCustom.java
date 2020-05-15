package com.imooc.mapper;


import com.imooc.pojo.OrderStatus;
import com.imooc.pojo.vo.center.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapperCustom {

    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

    int getMyOrderStatusCounts(@Param("paramsMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String, Object> map);

}