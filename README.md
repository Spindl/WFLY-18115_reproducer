# WFLY-18115_reproducer
A simple reproducer for the WildFly Bug WFLY-18115

It uses the latest available WildFly version `28.0.1.Final`.

## How to run the reproducer
1) Build everything by running `mvn clean install`
2) Then there are three different cases you can test:
   1) Without configuration the subsystem works
      1) Run the server with `java -jar server/target/server-local-SNAPSHOT-bootable.jar`
      2) Run the client with `java client/src/main/java/com/nts/reproducer/client/Main.java`
      3) The client should receive `204 <No Body>` from the server
   2) When configuring a value valid for the subsystem
      1) Run the server with `java -jar server/target/server-local-SNAPSHOT-bootable.jar --cli-script=sampler-type-valid-for-subsystem.cli`
      2) Run the client with `java client/src/main/java/com/nts/reproducer/client/Main.java`
      3) The client should receive a `500 <html><head><title>ERROR</title><style>` response from the server
      4) In the server log you can find the cause: `Caused by: io.opentelemetry.sdk.autoconfigure.spi.ConfigurationException: Unrecognized value for otel.traces.sampler: on`
   3) When configuring a value valid for opentelemetry
      1) Run the server with `java -jar .\server\target\server-local-SNAPSHOT-bootable.jar --cli-script=sampler-type-valid-for-opentelemetry.cli`
      2) The server won't start because the configured value is not accepted by the subsystem

## Description
The reproducer just creates a server including the opentelemetry subsystem, containing a ReST resource.

The resource can be found here, and opening it in the browser is sufficient: 
`http://localhost:8080/rest/reproducer/trigger`

The purpose of the resource just is to trigger the opentelemetry-server-filter, which in order triggers 
the OpenTelemetry object to be created and configured. This causes the bug to manifest itself. 

The reproducer also contains a client which just calls the endpoint to trigger the bug.