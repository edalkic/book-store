package com.example.bookstore.service;

import com.example.bookstore.database.entity.Order;
import com.example.bookstore.database.repository.OrderRepository;
import com.example.bookstore.dto.StatisticDto;
import com.example.bookstore.helper.StatisticHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final OrderRepository orderRepository;
    private final StatisticHelper statisticHelper;

    public List<StatisticDto> getMonthlyOrderStatistic() {

        List<Order> orders = orderRepository.findAll();
        Map<String, List<Order>> monthOrderMap = statisticHelper.getMonthOrderMap(orders);

        List<StatisticDto> statisticDtoList = new ArrayList<>();
        for (Map.Entry<String, List<Order>> monthOrderEntry : monthOrderMap.entrySet()) {
            String monthName = monthOrderEntry.getKey();
            List<Order> ordersByMonth = monthOrderEntry.getValue();
            StatisticDto statisticDto = getStatisticDto(monthName, ordersByMonth);
            statisticDtoList.add(statisticDto);
        }

        return statisticDtoList;
    }

    private StatisticDto getStatisticDto(String monthName, List<Order> ordersByMonth) {

        StatisticDto statisticDto = new StatisticDto();
        statisticDto.monthName = monthName;
        statisticDto.totalOrderCount = ordersByMonth.size();
        int totalBookCount = 0;
        for (Order order : ordersByMonth) {
            totalBookCount += statisticHelper.calculateTotalBookCountByOrderItems(order.getOrderItems());
        }
        statisticDto.totalBookCount = totalBookCount;
        statisticDto.totalPurchasedAmount = statisticHelper.calculateTotalAmountByOrders(ordersByMonth);

        return statisticDto;
    }

}
