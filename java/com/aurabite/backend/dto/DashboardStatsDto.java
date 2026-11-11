package com.aurabite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDto {
    private long totalBookings;
    private long todayBookings;
    private double totalRevenue;
    private long totalTables;
    private long availableTables;
    private long totalMenuItems;
    private double averageRating;
    private long unreadInquiries;
}
