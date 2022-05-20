Šachy přes síť:

Spuštění:

java ChessOverNetwork.Main <port> pro server
java ChessOverNetwork.Main <ip_adress> <port> pro klienta

e.g.

Server: java ChessOverNetwork.Main 6666
Client: java ChessOverNetwork.Main localhost 6666

Samotné hraní:

3 povolené inputy:
    1) from to
    2) from to piece
    3) help -h
from, to ve formátu , řádek ve formě znaku abecedy, sloupec v rozsahu 1-8,
příklad e2 e4, tedy figurka na e2 postoupí na e4. Provede se jenom pokud je krok validní.

Pro castling je zadat tah jako, pozici krále kde stojí a kde by stál pokud je casling validní pohyb v dané pozici.




