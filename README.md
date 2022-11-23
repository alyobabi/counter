## Counter Spring Boot Application

method | description                                    
--- |------------------------------------------------
POST /counter/{name} | Create new counter                             |
PATCH /counter/{name} | Increment counter by name                      |
GET /counter/{name} | Get counter's value by name                    |
DELETE /counter/{name} | Delete counter by name                         |
GET /counter/all/sum | Get sum of all counters' values                |
GET /counter/all/names | Get all counters' name |

## Usage

- create package and then run:

  `mvn package`

  `docker run -p 9092:8080 -t counter:0.0.1-SNAPSHOT`

