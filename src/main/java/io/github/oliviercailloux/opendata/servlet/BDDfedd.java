package io.github.oliviercailloux.opendata.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.oliviercailloux.opendata.entity.Course;
import io.github.oliviercailloux.opendata.entity.CoursePart;
import io.github.oliviercailloux.opendata.entity.CourseType;
import io.github.oliviercailloux.opendata.entity.FunctionType;
import io.github.oliviercailloux.opendata.entity.Person;
import io.github.oliviercailloux.opendata.entity.TypeSpecialty;
import io.github.oliviercailloux.opendata.utils.JPAutil;
import io.github.oliviercailloux.opendata.utils.ServletHelper;

/**
 * Servlet implementation class BDDfedd
 */
@WebServlet("/BDDfedd")
public class BDDfedd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BDDfedd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final ServletOutputStream out = new ServletHelper().configureAndGetOutputStream(response);
		out.println("Start DAO opretation");
		out.flush();
		
		JPAutil j= new JPAutil();
		EntityManager entityManager=j.getEntityManager("Dauphine-Open-Data");

		// add the person
		Person p=   new Person();
			p.setFirstName("zakaria");
			List<String> emails =new ArrayList<String>();
			emails.add("benzait.zakaria@deauphine.eu");
			p.setEmails(emails);
			p.setFax("0758288267");
			p.setLastName("BENZAIT");
			p.setOffice("P45");
			p.setRole(FunctionType.MAITRE_DE_CONFERENCES);
	    Person p1=  new Person();
		    p1.setFirstName("Mohamed");
			List<String> emails2 =new ArrayList<String>();
			emails.add("benzait.mohamed@deauphine.eu");
			p1.setEmails(emails2);
			p1.setFax("0788288267");
			p1.setLastName("BENZAIT");
			p1.setOffice("P41");
			p1.setRole(FunctionType.ENS_VAC);
	    Person p2=  new Person();
		    p2.setFirstName("Ali");
			List<String> emails3 =new ArrayList<String>();
			emails.add("Ali.kadour@deauphine.eu");
			p2.setEmails(emails3);
			p2.setFax("0758288278");
			p2.setLastName("Kadour");
			p2.setOffice("P4");
		    p2.setRole(FunctionType.PROFESSEUR_DES_UNIVERSITES);

		// add all the courses int the BDD
		EntityTransaction tx = entityManager.getTransaction();
	    Course c=   new Course();
		    c.setCourseName("Java");
		    List<CoursePart >  cps= new ArrayList<CoursePart>();
		    CoursePart cp1 =  new CoursePart(CourseType.TP, 2);
		    cp1.setTeacher(p);
		    cps.add(cp1);
		    c.setCoursePart(cps);
		    c.setCredits(3);
		    c.setDescription("Cours pour JavaEE");
		    c.setLocale(new Locale("15/02/2018"));
		    c.setSpecialty(TypeSpecialty.M1_MIAGE_SITN);
	    Course c1=  new Course();
		    c1.setCourseName("Android");
		    List<CoursePart >  cps1= new ArrayList<CoursePart >();
		    CoursePart cp11 =  new CoursePart(CourseType.CM, 2);
		    cp11.setTeacher(p1);
		    cps1.add(cp11);
		    c1.setCoursePart(cps1);
		    c1.setCredits(3);
		    c1.setDescription("Cours CM pour Android");
		    c1.setLocale(new Locale("15/02/2018"));
		    c1.setSpecialty(TypeSpecialty.M2_MIAGE_SITN);
	   
	 	tx.begin();
		//entityManager.persist(p);
	 	//entityManager.persist(p1);
	 	entityManager.persist(p2);
	 	entityManager.persist(c);
	 	entityManager.persist(c1);
	 	tx.commit();
	 	out.println("End all operation");
		out.flush();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}