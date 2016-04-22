part of app;

class Config {
    Map<String, String> database;
    Map<String, String> environment;

    String get host => environment["OPENSHIFT_DIY_IP"] ?? '0.0.0.0';
    int get port => environment["OPENSHIFT_DIY_PORT"] 
                        ? int.parse(environment["OPENSHIFT_DIY_PORT"]) 
                        : 8080;

    Config() {
        this.environment = Platform.environment;

        this._handleDataBase();
    }

    _handleDataBase() {
        database = {
                "host": this.environment["DATABASE_HOST"] ?? "localhost",
                "port": this.environment["DATABASE_PORT"] ?? 27017
            };
    }
}