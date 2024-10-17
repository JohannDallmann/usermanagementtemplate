# usermanagementtemplate


# Keycloak
Starten von Keycloak
1. Keycloak herunterladen, zip in C:\Projekte entpacken

2. Alias in gitbash erstellen, um Befehl schneller ausführen zu können. Im Anschluss gitbash neu starten. JAVA_HOME muss auf verwendete jdk-version verweisen
```
echo "alias keycloak='/c/Projekte/keycloak-26.0.0/bin/kc.sh start-dev'" >> ~/.bashrc
```
3. Unter C:\Projekte\keycloak-26.0.0\conf unter HTTP port einrichten: http-port=8090
4. Keycloak über gitbash vor Verwendung starten

Einrichten von Keycloak
1. Keycloak ist unter gesetztem port (localhost:8090) erreichbar
2. Initial admin-Account erstellen. Hier: admin mit PW "admin"
3. realm und client anlegen/importieren (gute Erklärung: https://www.youtube.com/watch?v=vmEWywGzWbA)
4. über dropdown kann realm ausgewählt werden, unter clients liegt usermanagementtemplate-client und kann angepasst werden
5. in "client scopes" öffentliche Attribute auswählen, in "realm roles" Rollen anlegen (z.B. User/Admin) 
6. in "User" konkrete Nutzer anlegen (unter credentials Passwort anlegen). Hier: admin1 und user1 mit PW "test"
7. Unter "Role mapping" Nutzern Rolle zuweisen (Realm oder Client: Client, wenn nur für die konkrete Anwendung)
8. Realm roles können Client roles zugewiesen werden (bei Filter muss client-Name angegeben werden, sonst werden die Rollen nicht gefunnden).
    Dem client-admin kann auch client-user zugewiesen werden, damit er auch alle dessen Rechte hat.
9. Unter  http://localhost:8090/realms/usermanagementtemplate/.well-known/openid-configuration können Informationen zu Realm ausgelesen werden. Mit Token-Endpoint kann Token eingeholt werden.
10. Über postman kann mit POST http://localhost:8090/realms/usermanagementtemplate/protocol/openid-connect/token Token für User eingeholt werden. Body vom Typ x-www-form-encoded beinhaltet Attribute: 
    - key: grant_type, value: password
    - key: client_id, value: usermanagementtemplate-client
    - key: username, value: admin1
    - key: passowrd, value: test
    Wichtig: Mail und Name des Users müssen gesetzt sein, sonst gibt es einen bad request
11. Token-Details über jwt.io auswerten

Konfigurationsdateien
1. In application.yml müssen spring.application.name, issuer-uri, server.port, resource-id für Projekt konfiguriert werden.
2. Über postman kann der Endpoint angesprochen werden: GET http://localhost:8080/helloworld/user (Authorization: gültigen Token einfügen)