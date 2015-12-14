package db;

import dao.exception.DAOException;
import db.exception.TransactionException;

public class TransactionManager {
    public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
        try {
            return transaction.execute();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TransactionException(e);
        }
    }
}