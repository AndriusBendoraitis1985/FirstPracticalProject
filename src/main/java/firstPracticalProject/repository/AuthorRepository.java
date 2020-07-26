package firstPracticalProject.repository;

import firstPracticalProject.model.Author;
import firstPracticalProject.services.SessionManager;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public static void createAuthor(Author author){
        Transaction transaction = null;
        try {
            Session session = SessionManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            session.close();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback(); }
            e.printStackTrace();
        }
    }
    public static void deleteAuthor(Author author){
        Transaction transaction = null;
        try {
            Session session = SessionManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(author);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback(); }
            e.printStackTrace();
        }
    }
    public static void updateAuthor(Author author){
        Transaction transaction = null;
        try {
            Session session = SessionManager.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback(); }
            e.printStackTrace();
        }
    }
}
