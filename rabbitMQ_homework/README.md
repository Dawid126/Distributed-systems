Organizacja zimowej wyprawy górskiej lub wysokogórskiej to trudne zadanie, w szczególności w zakresie zebrania odpowiedniego sprzętu. Niektóre ekipy biorą ze sobą tlen, inne nie dopuszczają takiej możliwości; jedni turyści korzystają z zestawu puchowej odzieży, inni wolą morsować. Niezależnie od potrzeb, obsługa zleceń na sprzęt do wypraw górskich to niełatwe zadanie, które wymaga zapewnienia odpowiedniej komunikacji.

Zaimplementuj, z użyciem RabbitMQ, system pośredniczący pomiędzy ekipami zmierzającymi na wyprawę górską (Ekipa), a dostawcami sprzętu górskiego (Dostawca). Ekipy mogą zamawiać różne typy sprzętu, natomiast każdy Dostawca posiada swoją listę dostępnych u niego typów sprzętu.



Dostawcy w wyniku porozumienia, określili następujące zasady sprzedaży:

- ceny poszczególnych typów sprzętów są takie same u wszystkich Dostawców (w związku z czym nie są uwzględniane w systemie rozdzielania zamówień)

- zlecenia powinny być rozkładane pomiędzy Dostawców w sposób zrównoważony

- dane zlecenie nie może trafić do więcej niż jednego Dostawcy

- zlecenia identyfikowane są przez nazwę Ekipy oraz wewnętrzny numer zlecenia nadawany przez Dostawcę

- po wykonaniu zlecenia Dostawca wysyła potwierdzenie do Ekipy





Dostosowanie się do unijnych wymagań w zakresie oprogramowania wymaga, aby do projektu załączona została dokumentacja w postaci schematu działania systemu. Schemat powinien uwzględniać:

- użytkowników, exchange'e, kolejki, klucze użyte przy wiązaniach

- schemat musi mieć postać elektroniczną, nie może to być skan odręcznego rysunku



Zadanie należy zaprezentować w następującym scenariuszu:

- omówić schemat

- uruchomić 2 ekipy (nadając im przy tym wybrane nazwy)

- uruchomić 2 dostawców (nadając im przy tym wybrane nazwy), z których:

	pierwszy obsługuje 2 typy sprzętu: tlen i buty; drugi obsługuje dwa typy sprzętu: tlen i plecak;


- przesłać z Ekipy 1 następującą serię zleceń: 

	'tlen', 'tlen', 'buty', 'buty', 'plecak', 'plecak'


- zaprezentować po kolei, co wypisali: Ekipa 1, Ekipa 2, Dostawca 1, Dostawca 2

- w ramach testów systemu przyjmujemy, że zlecenia obsługiwane są natychmiast

- każda operacja (wysłanie i odebranie zlecenia) powinna być wypisana


<img src="schemat.png" alt="schema" />

