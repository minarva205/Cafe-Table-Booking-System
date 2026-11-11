package com.aurabite.backend.service;

import com.aurabite.backend.dto.DashboardStatsDto;
import com.aurabite.backend.dto.OrderItemDto;
import com.aurabite.backend.dto.ReservationRequest;
import com.aurabite.backend.model.*;
import com.aurabite.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final ReviewRepository reviewRepository;
    private final ContactInquiryRepository contactInquiryRepository;

    @Transactional
    public Reservation createReservation(ReservationRequest request) {
        CafeTable table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Table ID: " + request.getTableId()));

        String bookingRef = "AB-" + (10000 + new Random().nextInt(90000));

        Reservation reservation = new Reservation();
        reservation.setBookingRef(bookingRef);
        reservation.setCustomerName(request.getCustomerName());
        reservation.setCustomerEmail(request.getCustomerEmail());
        reservation.setCustomerPhone(request.getCustomerPhone());
        reservation.setReservationDate(request.getReservationDate());
        reservation.setTimeSlot(request.getTimeSlot());
        reservation.setGuestCount(request.getGuestCount());
        reservation.setTable(table);
        reservation.setStatus("CONFIRMED");
        reservation.setSpecialRequests(request.getSpecialRequests());

        double preorderTotal = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        if (request.getPreorders() != null && !request.getPreorders().isEmpty()) {
            for (OrderItemDto itemDto : request.getPreorders()) {
                Optional<MenuItem> menuItemOpt = menuItemRepository.findById(itemDto.getMenuItemId());
                if (menuItemOpt.isPresent()) {
                    MenuItem menuItem = menuItemOpt.get();
                    double itemTotal = menuItem.getPrice() * itemDto.getQuantity();
                    preorderTotal += itemTotal;

                    OrderItem orderItem = new OrderItem();
                    orderItem.setReservation(reservation);
                    orderItem.setMenuItem(menuItem);
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setUnitPrice(menuItem.getPrice());
                    orderItems.add(orderItem);
                }
            }
        }

        reservation.setTotalPreorderAmount(preorderTotal);
        Reservation savedReservation = reservationRepository.save(reservation);

        if (!orderItems.isEmpty()) {
            orderItemRepository.saveAll(orderItems);
        }

        return savedReservation;
    }

    public Optional<Reservation> getByBookingRef(String bookingRef) {
        return reservationRepository.findByBookingRef(bookingRef);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation updateStatus(Long id, String status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + id));
        reservation.setStatus(status.toUpperCase());
        return reservationRepository.save(reservation);
    }

    public DashboardStatsDto getDashboardStats() {
        long totalBookings = reservationRepository.count();
        long todayBookings = reservationRepository.countTodayReservations();
        double totalRevenue = reservationRepository.calculateTotalRevenue();
        long totalTables = tableRepository.count();
        long availableTables = tableRepository.findByStatus("AVAILABLE").size();
        long totalMenuItems = menuItemRepository.count();
        double avgRating = reviewRepository.findAverageRating();
        long unreadInquiries = contactInquiryRepository.countByStatus("UNREAD");

        return DashboardStatsDto.builder()
                .totalBookings(totalBookings)
                .todayBookings(todayBookings)
                .totalRevenue(totalRevenue)
                .totalTables(totalTables)
                .availableTables(availableTables)
                .totalMenuItems(totalMenuItems)
                .averageRating(Math.round(avgRating * 10.0) / 10.0)
                .unreadInquiries(unreadInquiries)
                .build();
    }
}
