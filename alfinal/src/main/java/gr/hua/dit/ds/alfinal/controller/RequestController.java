package gr.hua.dit.ds.alfinal.controller;

import gr.hua.dit.ds.alfinal.dao.AppointmentDAO;
import gr.hua.dit.ds.alfinal.entity.Appointment;
import gr.hua.dit.ds.alfinal.entity.Request;
import gr.hua.dit.ds.alfinal.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @GetMapping("")
    public String showRequests(Model model){
        model.addAttribute("requests", requestService.getRequests());
        return "requests";
    }

    @GetMapping("/new")
    public String addRequest(Model model){
        Request request = new Request();
        model.addAttribute("request", request);
        return "add_request";
    }

    @GetMapping("{request_id}")
    public String editRequest(@PathVariable Integer request_id, Model model){
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        return "add_request";
    }

    @GetMapping("appointments/{request_id}")
    public String enrollAppointments(@PathVariable Integer request_id, Model model){
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        List<Appointment> appointments = appointmentDAO.getAppointments();
        List<Appointment> requestappointments = request.getAppointments();
        appointments.removeAll(requestappointments);
        model.addAttribute("appointments", appointments);
        return "add_appointments_to_request";
    }

    @PostMapping("appointments/{request_id}/{appointment_id}")
    public String enrollAppointment(@PathVariable Integer request_id, @PathVariable Integer appointment_id, Model model){
        System.out.println("inside post");
        Request request = requestService.getRequest(request_id);
        Appointment appointment = appointmentDAO.getAppointment(appointment_id);
        request.addAppointment(appointment);
        requestService.saveRequest(request);
        model.addAttribute("request", request);
        List<Appointment> appointments = appointmentDAO.getAppointments();
        model.addAttribute("appointments", appointments);
        return "requests";
    }

    @GetMapping("/appointment/{appointment_id}")
    public String getAppointmentRequests(@PathVariable Integer appointment_id, Model model){
        model.addAttribute("requests",requestService.getAppointmentRequests(appointment_id));
        return "requests";
    }
    @PostMapping("/new")
    public String saveCourse(Request request, Model model){
        requestService.saveRequest(request);
        model.addAttribute("requests", requestService.getRequests());
        return "requests";
    }


    @DeleteMapping("{request_id}")
    public String deleteRequest(@PathVariable Integer request_id){
        requestService.deleteRequest(request_id);
        return "requests";
    }



}
