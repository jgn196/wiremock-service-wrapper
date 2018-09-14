# WireMock Service #

A Windows service wrapper around the [WireMock](http://wiremock.org/) mock server.

## Prerequisites ##

You will need Gradle and JDK 1.8 to build the mock server.

You need the 64-bit JRE 1.8 to run the mock server.

The mock server has been written to run as a Windows service (using 
[WinRun4J](http://winrun4j.sourceforge.net/)).

## Build ##

To build the mock server run this command from the project root:

`gradlew distZip`

This will create mock server distribution Zip file in the `build/distributions` directory.

## Installation ##

**Warning:** This version of the mock server only works with the 64-bit version of the Java JRE.

1. Copy the distribution file onto the Windows server you want to install the mock server on.
2. Unzip the contents of the Zip file into an installation directory.
3. Edit the service.ini file (see below).
4. Run the `service.exe` (see below).

The service `Mock Service` will have appeared in the Windows *Services* listing.

### Editing Service INI file ###

Update the `vm.location` setting to point to the `jvm.dll` file in your JRE installation. 

You can also change the server port number in the ini file by updating the `arg.1` setting. 
If you remove this setting the default port 9090 will be used.

### Running Service.exe ###

Using an **Administrator** command prompt, run the `service.exe` with the following (case sensitive) 
command line:

`service.exe --WinRun4J:RegisterService`

For more information on the `service.exe` see the 
[WinRun4J documentation](http://winrun4j.sourceforge.net/).

### Updating the Service ###

To update the service: 

1. Stop it in the Windows *Services* UI.
2. Replace the `wiremock-service-wrapper...-all.jar` file.
3. Restart the service.

It is not necessary to remove the Windows service.

## Programming the Mock ##

The mock can be programmed with responses using its Java API or the public REST API (see 
the [WireMock documentation](http://wiremock.org/docs/)).

By default, the mock service runs on port 9090.