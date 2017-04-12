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

## csv data

files to download available at:
http://bossa.pl/notowania/pliki/eod/omega/

```
omegafun.zip (funds)

omegabp.zip (currencies)
```


```
path examples on linux:

/home/<user_name>/Bossa/funds/AGI001.txt

/home/<user_name>/Bossa/currencies/AUD.txt
```