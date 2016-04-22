part of app;

class BasicDao {
    var dataSource;

    BasicDao(DataSource ds) {
        dataSource = ds;
    }

    void execute(void func(Collection collection)) async {
        Db database = dataSource.database;
        await database.open();
        var collection = database.collection(this._table);

        await func(collection);

        database.close();
    }

    void query(List<Place> func(Collection collection)) async {
        Db database = dataSource.database;
        await database.open();
        var collection = database.collection(this._table);

        List<Place> results = await func(collection);

        database.close();

        return results;
    }
}