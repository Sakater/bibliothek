# bibliothek

Am leichtesten ist das Programm über IntelliJ zum laufen zu bringen.

Benötigt wird eine MySql-Datenbank, die Tabellen werden beim Programmstart von Spring selber erstellt. 
Die Verbindung zur DB muss in den .properties/.yml eingetragen werden.
Das Progrmm läuft auf dem Port 8080.

Es muss in der build/run konfiguratien im IDE die "com.example.bibliothek.BibliothekApplication" eingetragen werden.
Java 17 im IDE wird benötigt.


Seiten:
localhost:8080 --> öffnet die Homepage (noch nicht bearbeitet)
localhost:8080/loadall --> öffnet eine HTML-Seite, auf der alle Bucheinträge der DB aufgelistet werden
localhost:8080/addbook --> öffnet eine HTML-Seite, auf der man ein Buch der Datenbank hinzufügen kann

API's:
kann in der Datei src/main/java/com.example.bibliothek/books/BooksController.java eingesehen werden.
Die Struktur einer API Json-Datei ist unter src/main/java/com.example.bibliothek/books/BooksRequest einsehbar.
