package control;

import beans.employee;
import entities.EmployeeEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.*;
import java.util.List;

@ManagedBean
@SessionScoped
public class employeeController {
    private final String persistance_name = "JPATest";
    public List<employee> employees;

    public employeeController(){
    }

    public void loadEmployees(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();

        Query queryobj = em.createQuery("Select s from EmployeeEntity s");
        employees = queryobj.getResultList();
    }

    public String addEmployee(employee person) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(person.getName());
        employee.setAge(person.getAge());
        employee.setEmail(person.getEmail());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        emf.close();

        return "index?faces-redirect=true";
    }

    public String deleteEmployee(int employee) {
        System.out.print("ID : " + employee);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATest");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        EmployeeEntity deleteThisObject = em.find(EmployeeEntity.class, employee);
        em.remove(deleteThisObject);
        em.getTransaction().commit();
        em.close();
        emf.close();

        return "index?faces-redirect=true";
    }

    public List<employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<employee> employees) {
        this.employees = employees;
    }
}
