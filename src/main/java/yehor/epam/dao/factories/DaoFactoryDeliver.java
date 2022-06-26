package yehor.epam.dao.factories;

public class DaoFactoryDeliver {

    private DaoFactoryDeliver() {
    }

    private static final class FactoryDeliverHolder {
        private static final DaoFactoryDeliver factoryDeliver = new DaoFactoryDeliver();
    }

    public static DaoFactoryDeliver getInstance() {
        return FactoryDeliverHolder.factoryDeliver;
    }

    public DAOFactory getFactory() {
        return createMySQLFactory();
    }

    public MySQLFactory createMySQLFactory() {
        return new MySQLFactory();
    }
}
