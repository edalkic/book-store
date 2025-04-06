package com.example.bookstore.controller;

import com.example.bookstore.dto.StatisticDto;
import com.example.bookstore.dto.base.BaseResponse;
import com.example.bookstore.helper.ResponseHelper;
import com.example.bookstore.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/getMonthlyOrderStatistic")
    public ResponseEntity<BaseResponse<List<StatisticDto>>> getMonthlyOrderStatistic() {
        try {
            List<StatisticDto> monthlyOrderStatistic = statisticService.getMonthlyOrderStatistic();
            return ResponseHelper.success(monthlyOrderStatistic);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while get monthly order statistic", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
