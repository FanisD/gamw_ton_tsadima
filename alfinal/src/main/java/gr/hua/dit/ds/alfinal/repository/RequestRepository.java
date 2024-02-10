package gr.hua.dit.ds.alfinal.repository;

import gr.hua.dit.ds.alfinal.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
