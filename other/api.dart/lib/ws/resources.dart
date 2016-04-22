part of app;

@app.Group("/resources")
class Resources {
    Config config;

    Resources(Config this.config);

    @app.Route("/version")
    Map<String, num> version() {
        return {
            "version": 1.0
        };
    }

	@app.Route("/hello/:name")
    Map<String, String> getUser(String name) {
        return {
            "hello": name
        };
    }

    @app.Route("/service/environment")
    Map<String, String> getService() {
        return config.environment;
    }
}

