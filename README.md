## Build & run
### Build
Run Maven package command from the project root:
```bash
    mvn package
```
## Run
In the compiled artifacts directory `target/`, run:
```bash
    # replace $version with the version, ex. 1.0-SNAPSHOT
    java -jar financial-app-$version-jar-with-dependencies.jar
```
