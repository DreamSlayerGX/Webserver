# Webserver

Implemented a simple webserver for a Car Shop. The Server communicates only with json-messages over HTTPS with GET and POST. The endpoints supported is GET /employees, GET /carmodles, POST /carmodels, and GET /total_sales. The POST will need a json message in order for function properly.

The server is implemented i JAVA and needs an argument for the port it will listen to. As default argument (args[0]), the server will listen to port **8888**. This can be changed in Eclipse with *run* -> *run configurations* -> *arguments*.

To run the server, simply run the class *CarShopServer.java* and connect using *localhost:xxxx*, where *xxxx* is the port number (default 8888).

The server has basic data implemented, which is used primarily for testing. In other words, it is not a fresh, empty server. If the user wants to errase the data, she can remove "TESTING" as second argument when running the server. The data is found in *data.json* which is provided in the repo.

The file *CarShopClient.java* is a client for testing in the IDE and **NOT** part of the main program.

The supplied JAR-file is used to create JSON-objects in the server.
