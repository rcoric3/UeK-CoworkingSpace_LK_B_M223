package ch.zli.m223.controller;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;

@Path("/booking")
public class BookingController {

    @Inject
    BookingService bookingService;

    @POST
    @RolesAllowed({ "admin", "user" })
    @Path("/createBooking")
    @Tag(name = "createBooking", description = "Creats new Booking")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createNewBooking(Booking newBooking) {
        newBooking.setStatus("pending");
        bookingService.createBooking(newBooking);
        return newBooking.getId() + "." + " " + newBooking.getStatus();
    }

    @GET
    @RolesAllowed({ "admin", "user" })
    @Path("/checkBookingStatus/{id}")
    @Tag(name = "checkBookingStatus", description = "Checks the booking status")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Optional<Booking> checkBooking(@PathParam("id") Long id) {
        Optional<Booking> booking = bookingService.findBooking(id);
        if (booking.isPresent()) {
            return booking;
        } else {
            throw new NotFoundException("Buchung mit ID " + id + " nicht gefunden");
        }

    }

    @DELETE
    @RolesAllowed({ "admin", "user" })
    @Path("/cancelBooking/{bookingId}")
    @Tag(name = "cancelBooking", description = "Cancels Bookings")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteBooking(@PathParam("bookingId") Long id) {
        bookingService.deleteBooking(id);
    }

    @PUT
    @RolesAllowed({ "admin" })
    @Path("/manageBookingRequests/{bookingId}")
    @Tag(name = "manageBookingRequests", description = "Manage Booking Requests")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Booking manageBookingRequest(@PathParam("bookingId") Long id, Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @PUT
    @RolesAllowed({ "admin" })
    @Path("/changeStatus/{bookingId}")
    @Tag(name = "changeStatus", description = "Changes status of booking")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String changeStatus(@PathParam("bookingId") Long id, Booking booking) {
        booking.setStatus("Accepted");
        Booking newStatus = bookingService.changeStatus(id, booking);
        return newStatus.getStatus();
    }

}
