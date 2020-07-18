package firstPracticalProject.services;

        import firstPracticalProject.model.Author;
        import firstPracticalProject.model.Book;
        import org.hibernate.SessionFactory;
        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
        import org.hibernate.cfg.Configuration;
        import org.hibernate.cfg.Environment;
        import org.hibernate.service.ServiceRegistry;

        import java.util.Properties;

public class SessionManager {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/first_practical_project");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Author.class);
                configuration.addAnnotatedClass(Book.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                sessionFactory.openSession();
            } catch (Exception e) {
                throw new RuntimeException("Failed to open session", e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}