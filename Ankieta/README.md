## Build & run
### Build
Run Maven package command from the Maven project root `Ankieta/`:
```bash
    mvn package
```
## Run
In the compiled artifacts directory `Ankieta/target/`, run:
```bash
    # replace $version with the version, ex. 1.0-SNAPSHOT
    java -jar Ankieta-$version-jar-with-dependencies.jar
```
