package com.aurabite.backend.config;

import com.aurabite.backend.model.*;
import com.aurabite.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TableRepository tableRepository;
    private final MenuItemRepository menuItemRepository;
    private final ReviewRepository reviewRepository;
    private final ContactInquiryRepository contactInquiryRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {
        if (tableRepository.count() == 0) {
            initTables();
        }
        if (menuItemRepository.count() == 0) {
            initMenuItems();
        }
        if (reviewRepository.count() == 0) {
            initReviews();
        }
        if (contactInquiryRepository.count() == 0) {
            initInquiries();
        }
        if (reservationRepository.count() == 0) {
            initSampleReservations();
        }
    }

    private void initTables() {
        tableRepository.saveAll(Arrays.asList(
                new CafeTable(null, "T-01", 2, "Window View", "AVAILABLE", 35.0, "Panaromic glass view, power sockets, plush velvet seats"),
                new CafeTable(null, "T-02", 2, "Window View", "AVAILABLE", 35.0, "Garden sunset view, ideal for couples"),
                new CafeTable(null, "T-03", 4, "Window View", "RESERVED", 60.0, "Corner booth with street perspective"),
                new CafeTable(null, "T-04", 4, "Main Dining", "AVAILABLE", 50.0, "Central chandelier ambient lighting"),
                new CafeTable(null, "T-05", 6, "Main Dining", "AVAILABLE", 80.0, "Spacious oakwood table for family/friends"),
                new CafeTable(null, "T-06", 4, "Main Dining", "AVAILABLE", 50.0, "Acoustic wood paneling, quiet zone"),
                new CafeTable(null, "T-07", 2, "Patio Garden", "AVAILABLE", 40.0, "Open air, vertical garden view, fairy lights"),
                new CafeTable(null, "T-08", 4, "Patio Garden", "AVAILABLE", 65.0, "Heated terrace seating with fountain backdrop"),
                new CafeTable(null, "T-09", 8, "VIP Lounge", "AVAILABLE", 150.0, "Private leather booth, dedicated server, wine chiller"),
                new CafeTable(null, "T-10", 10, "VIP Lounge", "RESERVED", 200.0, "Soundproof executive dining chamber with projector")
        ));
    }

    private void initMenuItems() {
        menuItemRepository.saveAll(Arrays.asList(
                new MenuItem(null, "Velvet Gold Espresso", "Signature triple-shot Ethiopian Yirgacheffe with edible 24k gold leaf touch.", 8.50, "Coffee & Brews", "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?auto=format&fit=crop&w=600&q=80", true, true, "Chef's Pick", 4.9, 5),
                new MenuItem(null, "Smoked Vanilla Cold Brew", "Slow steeped for 24 hours over hickory wood smoke and topped with sea salt cream.", 7.00, "Coffee & Brews", "https://images.unsplash.com/photo-1517701604599-bb29b565090c?auto=format&fit=crop&w=600&q=80", true, false, "Vegan Option", 4.8, 5),
                new MenuItem(null, "Rose Pistachio Latte", "Organic steamed oat milk blended with damask rose extract and crushed Iranian pistachios.", 7.50, "Coffee & Brews", "https://images.unsplash.com/photo-1534778101976-62847782c213?auto=format&fit=crop&w=600&q=80", true, false, "Vegetarian", 4.7, 7),
                new MenuItem(null, "Truffle Mushroom Croissant", "Flaky French butter croissant stuffed with wild forest mushrooms, gruyere, and black truffle drizzle.", 14.00, "Bakery & Pastries", "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&w=600&q=80", true, true, "Chef's Pick", 4.9, 12),
                new MenuItem(null, "Wild Blueberry Brioche Toast", "House brioche topped with whipped mascarpone, fresh blueberries, and lavender honey.", 12.50, "Bakery & Pastries", "https://images.unsplash.com/photo-1484723091479-00321ed05544?auto=format&fit=crop&w=600&q=80", true, false, "Vegetarian", 4.8, 10),
                new MenuItem(null, "Avocado Tartine with Poached Eggs", "Sourdough toast layered with crushed Hass avocado, heirloom tomatoes, za'atar, and organic eggs.", 16.00, "Artisanal Mains", "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=600&q=80", true, false, "Vegetarian", 4.8, 15),
                new MenuItem(null, "Pan-Seared Salmon Grain Bowl", "Norwegian salmon over wild quinoas, roasted sweet potato, kale, and lemon-tahini dressing.", 22.00, "Artisanal Mains", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&w=600&q=80", true, true, "Gluten-Free", 4.9, 20),
                new MenuItem(null, "Wagyu Beef Sliders & Truffle Fries", "Three mini Wagyu beef patties with smoked cheddar, caramelized onion, and garlic aioli.", 24.00, "Artisanal Mains", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=600&q=80", true, true, "Chef's Pick", 5.0, 18),
                new MenuItem(null, "Elderflower & Dragonfruit Fizz", "Sparkling botanical elixir infused with white peach, dragonfruit, and wild mint.", 9.00, "Beverages & Mocktails", "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?auto=format&fit=crop&w=600&q=80", true, false, "Vegan", 4.9, 5),
                new MenuItem(null, "Matcha Citrus Spritzer", "Ceremonial grade Uji matcha shaken with fresh yuzu juice, agave, and fever-tree tonic.", 8.50, "Beverages & Mocktails", "https://images.unsplash.com/photo-1536935338788-846bb9981813?auto=format&fit=crop&w=600&q=80", true, false, "Vegan", 4.7, 5),
                new MenuItem(null, "Dark Chocolate Lava Sphere", "Valrhona 70% dark chocolate dome filled with salted caramel, cracked over warm vanilla bean gelato.", 15.00, "Desserts", "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=600&q=80", true, true, "Chef's Pick", 5.0, 15),
                new MenuItem(null, "Saffron & Pistachio Panna Cotta", "Silky Italian cream infused with saffron threads, topped with crushed pistachios & pomegranate.", 13.00, "Desserts", "https://images.unsplash.com/photo-1587314168485-3236d6710814?auto=format&fit=crop&w=600&q=80", true, false, "Gluten-Free", 4.8, 10)
        ));
    }

    private void initReviews() {
        reviewRepository.saveAll(Arrays.asList(
                new Review(null, "Aarav Sharma", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=120&q=80", 5, "AuraBite redefined our anniversary celebration! The patio table under the fairy lights was enchanting, and the Truffle Croissant was divine.", "Verified Guest", LocalDateTime.now().minusDays(2)),
                new Review(null, "Sophia Reynolds", "https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&w=120&q=80", 5, "Best coffee experience in town. The Velvet Gold Espresso is worth every penny. Impeccable service and smooth online table booking!", "Food Critic", LocalDateTime.now().minusDays(5)),
                new Review(null, "Vikram Malhotra", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=120&q=80", 5, "Booked the VIP Lounge for our corporate dinner. Soundproofing was great, projector worked seamlessly, and Wagyu Sliders were top notch.", "VIP Member", LocalDateTime.now().minusDays(9))
        ));
    }

    private void initInquiries() {
        contactInquiryRepository.save(new ContactInquiry(null, "Ananya Gupta", "ananya@example.com", "+91 9876543210", "Private Event Catering Request", "Hi AuraBite team, we are planning a private rooftop high tea event for 25 guests next month. Do you offer custom catering packages?", "UNREAD", LocalDateTime.now().minusHours(3)));
    }

    private void initSampleReservations() {
        CafeTable table3 = tableRepository.findByTableNumber("T-03").orElse(null);
        if (table3 != null) {
            Reservation res = new Reservation();
            res.setBookingRef("AB-10294");
            res.setCustomerName("Rohan Mehta");
            res.setCustomerEmail("rohan@example.com");
            res.setCustomerPhone("+91 9988776655");
            res.setReservationDate(LocalDate.now());
            res.setTimeSlot("19:00 - 21:00");
            res.setGuestCount(4);
            res.setTable(table3);
            res.setStatus("CONFIRMED");
            res.setSpecialRequests("Candlelight setup for birthday celebration");
            res.setTotalPreorderAmount(45.50);
            reservationRepository.save(res);
        }
    }
}
