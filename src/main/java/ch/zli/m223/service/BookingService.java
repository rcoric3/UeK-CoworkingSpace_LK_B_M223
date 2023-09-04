package ch.zli.m223.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Booking;

@ApplicationScoped
public class BookingService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Booking createBooking(Booking booking) {
        entityManager.persist(booking);
        return booking;
    }
    @Transactional
    public Optional<Booking> findBooking(Long bookingId) {
       Booking booking = entityManager.find(Booking.class, bookingId);
        return Optional.ofNullable(booking);
    }
    @Transactional
    public void deleteBooking(Long bookingId) {
        Booking bookingToDelete = entityManager.find(Booking.class, bookingId);
        if (bookingToDelete != null) {
            entityManager.remove(bookingToDelete);
        }
    }
    @Transactional
    public Booking updateBooking(Long bookingId, Booking updatedBooking) {
        Booking existingBooking = entityManager.find(Booking.class,bookingId);
        if (existingBooking != null) {
            existingBooking.setStatus(updatedBooking.getStatus());
            existingBooking.setType(updatedBooking.getType());
            existingBooking.setDate(updatedBooking.getDate());
            return existingBooking;
        }
        return updatedBooking;
    }
}
