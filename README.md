# Pandemia_MD :shipit:
Projecte Pandemia Morel i Daniel

# Esquema Principal del Projecte

# Explicació Decisions de Disseny Preses
 * Primerament vam haver d'analitzar els apartats que conté aquest projecte i entendre el que se'ns demanava.
 * Una vegada fet això podiem prendre decisions sobre com codificar el nostre programa de la manera més òptima i efectiva (l'ordre en que haviem de codificar els apartats, tenir en compte els tipus de variables que hauriem d'utilitzar juntament amb les estructures de control...).
 * Finalment quedava implementar el nostre codi tal i com haviem pensat. Per alguns apartats teniem una idea principal la qual vam haver de rectifiar perquè no teniem els coneixements suficients per dur a terme aquesta idea. **Per exemple**:
 
   - Començant el projecte ens vam adonar que a l'hora de mostrar l'array aquesta hauria de mostrar contingut alfanumèric (0-9, X). Com vam fer una array numèrica vam pensar que haviem de fer una altra alfanumèrica, però a l'hora de codificar aquesta proposta vam veure que no era la més adecuada tenint en compte els nostres coneixements.
   - A mesura que codificavem el nostre projecte ens vam adonar que quan voliem mostrar el nostre taulell per realitzar diferents apartats, hi havia codi redundant. Això ho vam solucionar fent un "Method", que ens serveix per indicar un bloc de codi que volem utilitzar al llarg del programa i que a l'hora d'utilitzar-lo, només hem de fer referència al nom d'aquest "Method" a l'apartat on volem que aquest bloc de codi funcioni, i així evitem redundància.
  
# Explicació Repartiment de Tasques
En el nostre cas nos ens hem atabalat molt, hem fet els apartats intercalats però això no vol dir que els hagim fet per separat o incomunicats. Mitjançant "Discord" hem fet trucades per realitzar cadascun dels apartats del projecte fos el que ens toquès a nosaltres o no, comentant codi, errors, fent proves... A més a més si no ens tocava realitzar un apartat el dia que feiem trucada, procuravem ajudar, buscar informació, intentar solucionar problemes... per tal de possar de la nostra part.  

# Joc de Proves

### Carregar Taulell

#### Cas 1: Creació d'un taullel buit
* Dades introduides a les variables utilitzades
  - **answer:** 1
  - **answerBoard:** 1
  - **x:** 5, **y:** 10
  
En aquest cas el que es fa és crear un taulell buit amb les mesures que et dona l'usuari.
Primerament seleccionem que volem utilitzar la opció "1" del menú.
Una vegada fet això ens demanarà si volem crear un taulell buit o si volem crear un taullel amb mesures random. Introduim "1" perquè l'usuari ens pugui donar les mesures del taulell.
Seguidament se'ns demanen les mesures. En aquest hem fet un taulell de 5 x 10 (5 files, 10 columnes). A més a més, tot taulell es crea amb un interval de 0-3 posicions bloquejades.

#### Cas 2: Creació d'un taulell amb mesures random
* Dades introduides a les variables utilitzades
  - **answer:** 1
  - **answerBoard:** 2
  
En aquest cas el que es fa és crear un taulell amb mesures aleatories.
Primerament seleccionem que volem utilitzar la opció "1" del menú.
Una vegada fet això ens demanarà si volem crear un taulell buit o si volem crear un taullel amb mesures random. Introduim "2" per agafar l'opció del taulell random.
Seguidament se'ns crea el taulell amb mesures aleatories (el programa indica quines són) i també conté posicions bloquejades.
# Proposta de millora
