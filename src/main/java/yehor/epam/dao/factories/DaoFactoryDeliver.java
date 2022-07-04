package yehor.epam.dao.factories;

/**
 * Class delivering appropriate DaoFactory
 */
public class DaoFactoryDeliver {

    private DaoFactoryDeliver() {
    }

    /**
     * Holder for stream-safety singleton
     */
    private static final class FactoryDeliverHolder {
        private static final DaoFactoryDeliver factoryDeliver = new DaoFactoryDeliver();
    }

    /**
     * Get DaoFactoryDeliver
     * @return DaoFactoryDeliver
     */
    public static DaoFactoryDeliver getInstance() {
        return FactoryDeliverHolder.factoryDeliver;
    }

    /**
     * Call general specified factory
     * @return MySQLFactory
     */
    public DaoFactory getFactory() {
        return createMySQLFactory();
    }

    public MySQLFactory createMySQLFactory() {
        return new MySQLFactory();
    }
}
