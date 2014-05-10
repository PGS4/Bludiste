Bludiste
========

Java projekt na procviceni zakladu javy

Changelog
verze 0.0.7
- předělání rzovržení projektu, 
  nyní mají všechny herní objekty nad sebou třídu GameEntity, 
  která převzala veškeré základní funkce a proměnné
- rozdělení classy Board na classy Model a Controller

verze 0.0.7b
- rozdělení class do dvou balíčků, podle toho, jestli jsou to
  entity hry, nebo ovladače na pozadí
  
verze 0.0.8
- přidání nepřítele!!
- optimalizace kódu, arraylisty už neobsahují přebytek informací, které jen berou paměť
  (dříve se konstruktor třídy Model spouštěl pro více tříd a při každym spuštění se vyvolala metoda levels(),
  nyní se spouští metoda levels() puze jednou)

verze 0.0.9
- vylepšení AI nepřátel

verze 0.1.0 - Velký Update!
- Implementace menu
- Implementace FOV pro hráče
- Opravení několika bugů s nepřátelema
- Příprava pro implementaci editoru map
- Menší optimalizace
