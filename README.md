### DMM-finance 
##### financial-app.jar library
---
####gif Build
Run Maven package command from the project root:
```bash
    mvn package
```
#### Run
In the compiled artifacts directory `target/`, run:
```bash
    # replace $version with the version, ex. 1.0-SNAPSHOT
    java -jar financial-app-$version-jar-with-dependencies.jar
```

#### csv data

files to download available at:
http://bossa.pl/notowania/pliki/eod/omega/

```
omegafun.zip (funds)

omegabp.zip (currencies)
```


```
CSV data import supports both: 

1) explicit file paths:

/home/<user_name>/bossa/funds/AGI001.txt

/home/<user_name>/bossa/currencies/AUD.txt

...

2) auto-generated file paths (by default Investment folders)

/home/<user_name>/bossa/funds/

/home/<user_name>/bossa/currencies/
```
Settings available in Configuration.json file.