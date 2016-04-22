part of app;

class DataSource {
    static final Map<String, DataSource> connectionPool = <String, DataSource>{};
    static final Logger log = new Logger("DataSource");

    Config config;
    var database;

    factory DataSource(String name, Config config) {
        connectionPool ??= [];
        connectionPool[name] ??= new DataSource._internal(name, config);

        return connectionPool[name];
    }

    DataSource._internal(String name, Config config) {
        String connectStr = "mongodb://${config.database['host']}:${config.database['port']}/${name}";
        database = new Db(connectStr);

        log.fine(connectStr);
    }
}

