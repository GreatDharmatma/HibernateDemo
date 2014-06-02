import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.rapideagle.tutorial.hibernatedemo.entities.Department;
import org.rapideagle.tutorial.hibernatedemo.entities.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class HibernateDemo {
	
	static SessionFactory sf;
	
	public static void main(String[] args)
	{
		sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        
        List<Employee> empList = new LinkedList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try 
        {
			empList.add(new Employee(returnDepartment("Sales"), "FDS", "QSD", sdf.parse("05/08/1981"),"7584561"));
			empList.add(new Employee(returnDepartment("Sales"), "WIE", "QWE", sdf.parse("29/12/1983"),"2547685"));
			empList.add(new Employee(returnDepartment("HRD"), "ASD", "IOU", sdf.parse("23/11/1982"),"8764532"));
			empList.add(new Employee(returnDepartment("HRD"), "BNA", "LKA", sdf.parse("02/06/1983"),"2347963"));
			empList.add(new Employee(returnDepartment("Marketing"), "ASJ", "LJS", sdf.parse("14/05/1982"),"9412475"));
			empList.add(new Employee(returnDepartment("Marketing"), "ERQ", "WQE", sdf.parse("18/01/1980"),"1036981"));
			empList.add(new Employee(returnDepartment("Accounting"), "ABC", "LKJ", sdf.parse("21/02/1981"),"3245716"));
			empList.add(new Employee(returnDepartment("Accounting"), "KJS", "MNS", sdf.parse("12/06/1982"),"2343553"));
			empList.add(new Employee(returnDepartment("Administration"), "SDF", "FDG", sdf.parse("25/09/1981"),"1234123"));
			empList.add(new Employee(returnDepartment("Administration"), "WER", "JTY", sdf.parse("09/07/1983"),"5634221"));
		} 
        catch (ParseException e) 
        {
			e.printStackTrace();
		}
        
        for(Employee e : empList)
        {
        	session.merge(e);
        }
        
        String strSql ="from Employee o";
        Query query = session.createQuery(strSql);
        List<?> lst = query.list();
        for(Iterator<?> it=lst.iterator();it.hasNext();)
        {
        	Employee emp=(Employee)it.next();
        	System.out.println("Employee ID:" + emp.getEmployeeId());
            System.out.println("First Name: " + emp.getFirstname());
            System.out.println("Last Name: " + emp.getLastname());
            System.out.println("Cell Phone Number: " + emp.getCellPhone());
            System.out.println("Date of Birth: " + emp.getBirthDate().toString());
            System.out.println("Works in: Department ID: " + emp.getDepartment().getDepartmentId() + " | Department Name: " + emp.getDepartment().getDeptName());
            System.out.println();
         }
        
        tr.commit();
        System.out.println("Data displayed");
        sf.close();
	}
	
	public static Department returnDepartment(String deptName)
	{
        Session session = sf.openSession();
        org.hibernate.Transaction tr = session.beginTransaction();
        
        String strSql ="from Department o where o.deptName =:dn";
        Query query = session.createQuery(strSql);
        query.setParameter("dn", deptName);
        List<?> lst = query.list();
        Iterator<?> iter = lst.iterator();
        
        if (!iter.hasNext())
        {
            Department dept =  new Department(deptName);
            session.save(dept);
            
            tr.commit();
            session.close();
            
            return dept;
        }
        
        Department dept = (Department)iter.next();
        
        tr.commit();
        session.close(); 
        
        return dept;
	}
}