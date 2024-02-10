package gr.hua.dit.ds.alfinal.service;

import gr.hua.dit.ds.alfinal.dao.AppointmentDAO;
import gr.hua.dit.ds.alfinal.entity.Appointment;
import gr.hua.dit.ds.alfinal.entity.Request;
import gr.hua.dit.ds.alfinal.repository.RequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Transactional
    public List<Request> getRequests(){
        return requestRepository.findAll();
    }

    @Transactional
    public void saveRequest(Request request){
        requestRepository.save(request);
    }

    @Transactional
    public void deleteRequest(Integer requestId) {
        requestRepository.deleteById(requestId);
    }

    @Transactional
    public Request getRequest(Integer requestId) {
        return requestRepository.findById(requestId).get();
    }

    public List<Request> getAppointmentRequests(Integer appointmentId){
        Appointment appointment = appointmentDAO.getAppointment(appointmentId);
        return appointment.getRequests();
    }

}
