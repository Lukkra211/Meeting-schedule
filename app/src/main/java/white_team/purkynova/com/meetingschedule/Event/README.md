# Event

Před přečtením si tohoto souboru doporučuji přečíst si README.md ve složce **Model**.

## Co to je
Event bude třída, kterou teprve vytvoříme. Bude to třída, která bude reprezentovat jednu událost.
Například, jedna přednáška bude jedna instance této třídy.


## Příklad použití
Předpokládejme, že máme databázový model **EventModel**, která nám má nějakým způsobem vracec tyto
informace, když jí dáme id události:

 - Čas události (například 12:30 - 13:15 nebo 14:20 - 17:45)
 
 - Typ události (například přednáška, občerstvení, zábava, volný čas...)
 
 - Místo události (například učebna 51 ve 4 patře, hotel, bowling na xxx ulici)
 
 - Dodatečné informace (například nějaký malý popis akce, co se bude dít a pod)

To je hodně informací. Problém je v tom, jak tolik informací předat. Šlo by to takto:

### Příklad špatného kódu
*U příkladu předpokládáme, že id události je 5.*
```
// id události
int id = 5;
// v dbModel je databázový model, na kterém voláme různé metody
EventModel dbModel = new EventModel();
 
// Tady získáváme data o události s id 5
string time = dbModel.getTimeById(id);
string type = dbModel.getTypeById(id);
string place = dbModel.getPlaceById(id);
string additionalInfo = dbModel.getAdditionalInfoById(id);
 
// K informacím o události by se přistupovalo takto
time; // čas
type; // typ
place; // místo
additionalInfo; // info
```

### Příklad dobrého kódu
Pokud ale napíšeme třídu **Event**, kterou bude vracet **EventModel**, může to vypadat takto:
```
// id události
int id = 5;
// v dbModel je databázový model, na kterém voláme různé metody
EventModel dbModel = new EventModel();
 
// Tady získáváme data o události s id 5
Event event = dbModel.getEventById(id);
 
// proměnná event by v sobě měla všechny informace, ke kterým by se přistupovalo takto
event.getTime(); // čas
event.getType(); // typ
event.getPlace(); // místo
event.getInfo(); // info
```