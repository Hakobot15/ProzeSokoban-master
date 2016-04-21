# ProzeSokoban-master
Kacper zmienilem zrodlo player z player.png->player.gif
Dzieki temu bedziemy mogli rysowac player bezposrednio na floorze i goal.
Bedzie tez mozna narysowac plynne przejscie(w sensie przejscie z 1 kwadratu do 2 kwadratu moze sie odbywac np w 15 krokach rysowania);
W takiej implementacji mozemy zczytac z levelloader miejsca skrzynek, celow i scian.
Cele a skrzynki:
bedizemy sprawdzac czy lista skrzynek pokrywa sie z lista celow jezeli tak return true -> level finish.
skrzynki a player -> bedziemy sprawdzac czy na drodze jest skrzynka jezeli jest to sprawdzanie czy na kolejny polu nie ma skrzynki, sciany
jezeli jest to ruch niemozliwy, jezeli nic nie ma to przesuwamy skrzynke i  gracza,(ponadto mozemy do levelloader dodac ze odczytywanie 
mapy bedzie wygladac tak ze gdy odczytamy ze jest skrzynka to do mapy wczyta nam ze to jest podloga -> narysuje nam to, a wtedy na tym
narysujemy skrzynke(znika problem co sie bedzie dzialo w momencie gdy przesuniemy skrzynke czy gracza)
Czyli w podstawowej weresji bedzie nam rysowac cele, podloge i sciany.
Wydaje mi sie ze taki sposob implementacji bedzie o wiele latwiejszy.
W sobote sam do tego siade bo narazie czeasu nie mam :)
