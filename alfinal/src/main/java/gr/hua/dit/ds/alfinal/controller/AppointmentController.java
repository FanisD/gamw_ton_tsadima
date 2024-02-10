package gr.hua.dit.ds.alfinal.controller;

import gr.hua.dit.ds.alfinal.dao.AppointmentDAO;
import gr.hua.dit.ds.alfinal.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @GetMapping("")
    public String showAppointments(Model model){
        model.addAttribute("appointments", appointmentDAO.getAppointments());
        return "appointments";
    }

    @GetMapping("/new")
    public String addAppointment(Model model){
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);

        return "add_appointment";

    }

    @GetMapping("{appointment_id}")
    public String editAppointment(@PathVariable Integer appointment_id, Model model){
        Appointment appointment = appointmentDAO.getAppointment(appointment_id);
        model.addAttribute("appointment", appointment);
        return "add_appointment";

    }

    @PostMapping("/new")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment, Model model) {
        appointmentDAO.saveAppointment(appointment);
        model.addAttribute("appointments", appointmentDAO.getAppointments());
        return "appointments";
    }

    @DeleteMapping("{appointment_id}")
    public String deleteAppointment(@PathVariable Integer appointment_id){
        appointmentDAO.deleteAppointment(appointment_id);
        return "appointments";
    }



}
