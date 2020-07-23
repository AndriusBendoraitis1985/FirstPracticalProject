package firstPracticalProject.services;

import firstPracticalProject.model.Author;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class AuthorRepository {

    public static Author getAuthorById(int id) {
        Session session = SessionManager.getSessionFactory().openSession();
        Author author = session.find(Author.class, id);
        session.close();
        return author;
    }

    public static List<Author> getAuthorsList() {
        String getAllAuthorsHQLQuery = "FROM Author";

        Session session = SessionManager.getSessionFactory().openSession();
        Query selectAllAuthorsQuery = session.createQuery(getAllAuthorsHQLQuery);
        List<Author> authorList = selectAllAuthorsQuery.getResultList();
        session.close();

        return authorList;
    }
}
