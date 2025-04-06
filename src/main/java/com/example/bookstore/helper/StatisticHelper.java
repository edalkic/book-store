package com.example.bookstore.helper;

import com.example.bookstore.database.entity.Order;
import com.example.bookstore.database.entity.OrderItem;
import com.example.bookstore.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticHelper {

    public Map<String, List<Order>> getMonthOrderMap(List<Order> orders) {

        Map<String, List<Order>> monthOrderMap = new HashMap<>();

        for (Order order : orders) {

            String monthName = TimeUtil.getMonthNameFromDate(order.getCreationTime());
            List<Order> ordersByMonth = new ArrayList<>();
            if (monthOrderMap.containsKey(monthName))
                ordersByMonth = monthOrderMap.get(monthName);

            ordersByMonth.add(order);
            monthOrderMap.put(monthName, ordersByMonth);
        }
        return monthOrderMap;
    }

    public int calculateTotalBookCountByOrderItems(Set<OrderItem> orderItems) {

        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity).sum();

    }

    public Double calculateTotalAmountByOrders(List<Order> orders) {
        return orders.stream()
                .mapToDouble(Order::getTotalAmount).sum();
    }
}
