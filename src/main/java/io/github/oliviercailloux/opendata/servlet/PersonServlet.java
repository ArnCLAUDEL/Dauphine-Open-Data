package io.github.oliviercailloux.opendata.servlet;

import io.github.oliviercailloux.opendata.entity.Person;
import io.github.oliviercailloux.opendata.mapper.JsonMapper;
import io.github.oliviercailloux.opendata.utils.JPAutil;
import io.github.oliviercailloux.opendata.utils.ServletHelper;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Thomas & Sebastien on 08/02/2018.
 */
@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    // getPerson(id):Person
    /**
     * Object for DB access
     */
    @Inject
    private JPAutil jpaUtil;

    /**
     * mapper json to object and object to json
     */
    @Inject
    private JsonMapper jsonMapper;


    private static final Logger LOGGER = Logger.getLogger(PersonServlet.class.getName());


    /**
     * return all Person (JSON format)
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        @SuppressWarnings("resource")
        final ServletOutputStream out = new ServletHelper().configureAndGetOutputStream(response);
        // Add content type (JSON format)
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        EntityManager entityManager = jpaUtil.getEntityManager("Dauphine-Open-Data");

        LOGGER.info("we retrieve the list of persons");

        // get All the Person
        List<Person> persons = entityManager.createQuery("select p from Person p").getResultList();

        if (persons != null) {
            LOGGER.info(" list of persons size : " + persons.size());
        }

        // convert to JSON
        String json = jsonMapper.convertObjectToJson(persons);
        out.println(json);
        out.flush();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}