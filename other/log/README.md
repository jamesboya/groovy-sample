* change logger format for better auditing
    * [date] [level] [host] [thread] [class] [sessionid] [message/exception]

* request logger:
    * log response time for each "sub" request: [url] [response time in ms]
    * log response content

* async http client polyfill
    * shutdown listener for clean up asyn http client
    * reduce the boilerplate of each request call (might include conversion)

* metrics
    * percentile on request: [url][method][status]
    * connection pool for database
    * connection monitor on async http client

* all class is intented to be used in other property

* technical requirement
    * use log4j2
    * use dropwizard (acutually micrometer is better but)


example:
* http://localhost:8080/api/sample/v1/users/1
* http://localhost:8080/api/metrics
* http://localhost:8080/api/parsecException
* http://localhost:8080/api/npe







others
* conversion in asynchttpclient