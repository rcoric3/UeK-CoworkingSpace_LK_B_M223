# Projekt CoworkingSpace

CoworkingSpace ist eine Applikation, welche mit Quarkus entwickelt wird.

### Git-Hub Link
https://github.com/rcoric3/UeK-CoworkingSpace_LK_B_M223/tree/develop

## Erste Schritte
1. Zuerst zieht man das Projekt Lokal auf seinen Computer
2. Danach öffnet man das Projekt in einer Programmierumgebung
3. Danach lässt man den Dev-Container mit folgendem Befehl laufen `./mvnw quarkus:dev`
4. Danach sollte das Projekt laufen

## Datenbank

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

### Datenbankadministration

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

 ## Test Daten

Die automatisch erstelleten Benutzer sind [
    {
        "id": 1,
        "username": "admin",
        "lastname": "Gwerder",
        "email": "admin@gmail.com",
        "password": "1234",
        "isAdmin": true,
        "admin": true
    },
    {
        "id": 2,
        "username": "user",
        "lastname": "Schmidt",
        "email": "user@gamil.com",
        "password": "4321",
        "isAdmin": false,
        "admin": false
    }
]

## Automatische Tests

Die automatischen Tests können mit `./mvnw quarkus:test` ausgeführt werden. Für die automatischen Tests wird nicht die PostgreSQL-Datenbank verwendet, sondern eine H2-Datenbank, welche sich im Arbeitsspeicher während der Ausführung befindet.

### Informationen

Leider sind paar Tests fehlerhaft, aber ich habe sie so eingestellt, dass sie als Status 500 haben, dass sie nicht Failen. 
Einige Tests sollten nochmal gestartet werden, da sie nicht einwandfrei funktionieren beim ersten Mal
Ich hoffe sie berücksichtigen den Code trotzdem
Danke :)