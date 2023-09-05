package ch.zli.m223.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;

@Path("/booking")
public class BookingController {

    @Inject
    BookingService bookingService;

    @POST
    @RolesAllowed({"admin", "user"})
    @Path("/createBooking")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking createNewBooking(Booking booking) {
        bookingService.createBooking(booking);
        return booking;
    }

    @GET
    @RolesAllowed({"admin", "user"})
    @Path("/checkBookingStatus/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Booking> checkBooking(@PathParam("id") Long id) {
        Optional<Booking> booking = bookingService.findBooking(id);
        if (booking.isPresent()) {
            return booking;
        } else {
            throw new NotFoundException("Buchung mit ID " + id + " nicht gefunden");
        }
    }

    @DELETE
    @RolesAllowed({"admin", "user"})
    @Path("/cancelBooking/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteBooking(@PathParam("bookingId") Long id) {
        bookingService.deleteBooking(id);
    }

    @PUT
    @RolesAllowed({"admin"})
    @Path("/manageBookingRequests/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking manageBookingRequest(@PathParam("bookingId") Long id, Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @PUT
    @RolesAllowed({"admin", "user"})
    @Path("/changeStatus/{bookingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking changeStatus(@PathParam("bookingId") Long id, Booking booking) {
        return bookingService.changeStatus(id, booking);
    }

}
