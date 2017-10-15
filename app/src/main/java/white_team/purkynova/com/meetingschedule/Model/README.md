# Model


## Co to je
Model je v programování součást zdrojového kódu, která se stará o data. V našem případě budeme
z nějaké databáze získávat data, například:

 - jaké přednášky se budou dít ve středu a v jakých učebnách? 
 
 - kdy se bude v pátek konat večeře a jaké o této akci máš informace?
 
 - kdy bude přednáška o databázích, v jaké učebně a kdo přednáší?
 
 
## Proč
Aby nebyl ve zdrojáku bordel a aby byl přehledný, chceme, aby se v naší aplikaci nepotkal v jednom
souboru kód pro práci s aktivitami a kód pro tahání dat z databáze. Chceme, aby kód pro aktivity
byl v nějakých souborech, a kód pro databázi v jiných souborech. A tohle je přesně je model.


## Jak to vypadá
Model v androidu vypadá tak, že máte jednu větší třídu, například **EventModel**. Tato třída pak
nabízí metody, například *getEventByDay*, která, kdyby jste jí zavolali, vám vrátí všechny
informace ohledně událostí na jeden den. A podobně... Například uživatel chce zobrazit informace o
přednášce, o které víte že má id 5. Zavoláte teda metodu **getEventById**, předáte jí tu 5, a ona
vám vrátí všechno, co vím o přednášce s id 5. Takto funguje model.

**PS: Ty jména metod jsem si vymyslel. Zatím nejsou funkční a v budoucnu se můžou jmenovat jinak.**