# Opis słowny klas i przypadków użycia

## Opis klas:
Pierwszym, niezbędnym krokiem do zrealizowania naszego projektu jest zdefiniowanie potrzebnych klas:
- klasa abstrakcyjna Field jest potrzebna do opisu komórek planszy. Ona definiuje rozmiar komórki (atrybut size) oraz możliwość zmiany jej wartości (atrybut isActive). Po niej dziedziczą jeszcze 3 klasy: Blank, Fill, Sums - gdyż w Kakuro wyróżniamy 3 rodzaje komórek;
- klasa Blank definiuje czarne komórki, które w grze występują jako elementy pasywne;
- klasa Fill definiuje białe komórki, w których gracz może ustawiać wartości typu int od 1 do 9. Te wartości są zapisywane i używane do sprawdzania wyniku gry;
- klasa Sums definiuje pasywne komórki, na których są podane sumy wartości liczb w białych komórkach, które gracz musi otrzymać;
- klasa BoardGenerator jest potrzebna do generowania planszy do gry;
- klasa Solver jest przeznaczona do obliczenia poprawnego rozwiązania tej gry;
- klasa Tester sprawdza czy nasze rozwiązanie jest takie same jak to, które generuje klasa Solver;
- klasa KakuroGUI pełni funkcję interfejsu graficznego, czyli odpowiada za wyświetlanie wszystkiego na ekran oraz za interakcję gracza z systemem;
- klasa ReadAndWrite odpowiada za zapis oraz odczyt informacji, podanej przez gracza;
- klasa UserControls pozwala na użycie klawiatury w celu przejścia pomiędzy komórkami;
- klasa StopWatch będzie pokazywała czas, który minął od początku naszej gry (czyli stoper). Po ukończeniu gry stoper zatrzymuje się, a przy rozpoczęciu nowej gry - resetuje się i zaczyna od nowa liczyć czas.

## Opis przypadków użycia:
Zaprojektowaliśmy także diagram przypadków użycia. Najpierw gracz rozpoczyna nową grę i wybiera poziom trudności. Wtedy generuje się plansza. Od tego momentu w dowolnej chwili gracz może się poddać.
Jeśli skorzysta z tej możliwości, gra pokaże poprawne rozwiązanie. Po wypełnieniu wszystkich białych komórek gracz może sprawdzić, czy jego rozwiązanie jest dobre. Jeżeli jest, to otrzymuje powiadomienie, gratulujące zwycięstwa. W przeciwnym razie wyświetli się informacja o tym, że rozwiązanie gracza jest niepoprawne. W takim wypadku, gracz może kontynuować rozwiązywanie łamigłówki, dopóki nie poda jej poprawnego wyniku, co jest równoznaczne z tym, że nie ma ograniczenia w liczbie prób. Gra jest zaprojektowana tak, że niezależnie od postępowania gracza, w końcu otrzyma poprawne rozwiązanie do wygenerowanej zagadki.
