## This is not ready yet, please see this issue: [Firebase multi-tenancy with play framework depends on header value of HTTP request](https://stackoverflow.com/questions/44647753)

### This is your a Play application to test firebase multi-tenant
=================================

This file will be packaged with your application when using `sbt dist` or run `sbt run`.

There are several demonstration files available in this template.

### Controllers
===========

- ApplicationController.java:

  Shows how to call firebase with a simple HTTP request : http://localhost:9000/api/testFirebase.


### Components
==========

- Module.java:

  Shows how to use Guice to bind all the components needed by your application.

### Filters
=======

- Filters.java:

  Creates the list of HTTP filters used by your application.

- ExampleFilter.java

  A simple filter that adds a header to every response.

### Actions
=======

- ActionCreator.java:

  Where to intercept requests and read headers.

## Configuration

1. Load sample data that provided inside [taxes-data-export.json](https://github.com/almothafar/play-with-multi-tenant-firebase/blob/master/taxes-data-export.json) into your firebase database project
2. Add your firebase configuration inside `conf/firebase` folder, namign the file with exactly your `project_id` that provided from firebase
3. inside `application.conf` provide list of your `WEBSITE_ID`s with the value for its `project_id`.