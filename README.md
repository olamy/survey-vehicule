## Vehicule Survey

You need Maven 3.x + installed and a JDK 1.6 minimum.
Just clone the project and use the command: mvn clean package (this will run unit test and build a jar)

An auto executable jar will be available in the ./target directory.

Simply run analysis report on the provided data file using:

java -jar target/survey-vehicule-1.0-SNAPSHOT.jar src/test/vehicle-survey-coding-challenge-sample-data.txt

The first and only argument is a path to a data file.

To be able to add easily new analysis report, the architecture is based on ServiceLoader mechanism so adding a new report
is only a matter of implementing the interface VehiculeRecordAnalysis and add the implementation class fqcn in the appropriate
META-INF/services file
