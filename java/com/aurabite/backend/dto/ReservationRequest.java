package com.aurabite.backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationRequest {
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDate reservationDate;
    private String timeSlot;
    private Integer guestCount;
    private Long tableId;
    private String specialRequests;
    private List<OrderItemDto> preorders;
}
