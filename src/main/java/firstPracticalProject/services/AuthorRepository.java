package firstPracticalProject.services;

import firstPracticalProject.model.Author;
import org.hibernate.Session;

public class AuthorRepository {

    public static Author getAuthorById(int id) {
        Session session = SessionManager.getSessionFactory().openSession();
        Author author = session.find(Author.class, id);
        session.close();
        return author;
    }
}
