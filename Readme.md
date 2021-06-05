# IoC Server

Results of a simple experiment to create a library that provides fast REST server setup with minimum impact on your 
code style. Full frameworks tend to force a particular way of writing and testing code and IoC Server aims to avoid 
that as much as possible.

At this point not recommended being taken seriously and used in anything other than experiments where you need a fast
start. 

## Usage

Essentially all you have to do is define your endpoints by extending the Endpoint class and register them in the 
endpoints Registry. Afterwards you simply instantiate the Server and run it in your Main class. At that point 
the Server takes over and routes incoming requests to the appropriate registered Endpoint.

An Endpoint is registered by specifying a Uri definition that describes your expected parameters using Regex.
The Uri definition requires you to define names for all the parameters in 2 ways depending on them being path or 
query params.

For example: `/hello/(id=\d{2})?name=(\w*)` defines an Endpoint at `/hello/` followed by a path param *id* which 
has to be a **double digit**, and a query parameter, *name* which has to be a **word**. In case the params are 
provided in an incorrect format the request will come back as a ***404***.

After you extend Endpoints class you have a choice of overriding the default methods for:
* get
* post
* put
* delete
* head
* patch

Overriding these will allow your Endpoint to process the corresponding requests, should a request come using a method 
which is not implemented by you, it will respond with ***501***.

Each of the methods above responds with a *Response* object, and you can use the existing *SimpleResponse* class or
create your own implementation as appropriate.

In case you need to respond with errors, the Server contains an IocServerException class you can extend in any of your
custom exceptions and throw them instead of the Response object from your endpoints.

Additionally, the Endpoint methods will provide you with a Map of Uri params, the body (which has to come in ***json***
format), and Headers. The headers are an Immutable Map however in case you need to add headers to your Response, you can
do so by overriding the `getHeaders` method. The Server will add your custom headers to the response.

## Importing

The library is available via jitpack, gradle example:

````
   repositories {
        jcenter()
        maven { url "https://jitpack.io" }
   }
   dependencies {
         implementation 'com.github.asutalo:ioc-server:1.0.0'
   }
````