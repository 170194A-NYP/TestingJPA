package control;

import beans.employee;
import entities.EmployeeEntity;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
public class employeeController {
    private final String persistance_name = "JPATest";


    public employeeController(){
    }

    public String addEmployee(employee person) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(person.getName());
        employee.setAge(person.getAge());
        employee.setEmail(person.getEmail());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistance_name);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        emf.close();

        return "second_page=?faces-redirect=true";
    }

}
